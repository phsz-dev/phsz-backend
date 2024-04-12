package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.common.CodeException;
import com.phsz.testservice.testserviceprovider.pojo.*;
import com.phsz.testservice.testserviceprovider.repository.ExamQuestionRepository;
import com.phsz.testservice.testserviceprovider.repository.ExaminationRepository;
import com.phsz.testservice.testserviceprovider.service.ExaminationService;
import com.phsz.testservice.testserviceprovider.service.PaperService;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationServiceImpl implements ExaminationService {

    private final ExaminationRepository examinationRepository;
    private final ExamQuestionRepository examQuestionRepository;
    @Resource
    private PaperService paperService;

    public ExaminationServiceImpl(ExaminationRepository examinationRepository, ExamQuestionRepository examQuestionRepository) {
        this.examinationRepository = examinationRepository;
        this.examQuestionRepository = examQuestionRepository;
    }

    @Override
    public Examination getExaminationById(Long examinationId) {
        return examinationRepository.findById(examinationId).orElse(null);
    }

    @Override
    public Page<Examination> getAllExaminations(Pageable pageable) {
        return examinationRepository.findAll(pageable);
    }

    @Override
    public Page<Examination> getExaminationsByName(String examinationName, Pageable pageable) {
        return examinationRepository.findAllByNameLike(examinationName, pageable);
    }

    @Override
    public String addExamination(Examination examination) {
        Long examinationId = examination.getId();
        if (examinationRepository.findById(examinationId).isPresent()) {
            return null;
        }
        Examination save = examinationRepository.save(examination);
        return save.getId().toString();

    }

    @Override
    public String updateExamination(Examination examination) {
        Long examinationId = examination.getId();
        if (examinationRepository.findById(examinationId).isEmpty()) {
            return null;
        }
        Examination save = examinationRepository.save(examination);
        return save.getId().toString();
    }

    @Override
    @Transactional
    public String deleteExamination(Long examinationId) {
        Integer result = examinationRepository.deleteExaminationById(examinationId);
        System.out.println("result = " + result);
        return result > 0 ? "success" : null;
    }

    @Override
    public Examination startExamination(Long userId, Long paperId) {
        Paper paper = paperService.getPaperById(paperId);
        if (paper == null) {
            throw new CodeException(1, "not found");
        }
        // 检查是否有正在进行的考试
        Examination running = getCurrentExamination(userId);
        if (running != null) {
            throw new CodeException(2, "already has a running examination");
        }
        Examination examination = new Examination();
        examination.setName(paper.getName());
        examination.setStatus("start");
        examination.setUserId(userId);
        examination.setPaper(paper);
        Date date = new Date();
        examination.setStartTime(date);
        examination.setEndTime(new Date(date.getTime() + paper.getDurationSeconds() * 1000));
        return examinationRepository.save(examination);
    }

    @Override
    public Examination getCurrentExamination(Long userId) {
        Examination examination = examinationRepository.findExaminationByUserIdAndStatus(userId, "start");
        if (examination != null && examination.getEndTime().before(new Date())) {
            examination.setStatus("end");
            calculateScore(examination);
            return null;
        }
        return examination;
    }

//    结算分数
    public Integer calculateScore(Examination examination) {
        int score = 0;
        Map<Long, ExamQuestion> examQuestionMap = new HashMap<>();
        for (ExamQuestion examQuestion : examination.getQuestions()) {
            examQuestionMap.put(examQuestion.getId().getQuestion(), examQuestion);
        }
        List<PaperQuestion> questions = examination.getPaper().getQuestions();
        for (PaperQuestion paperQuestion : questions) {
            Question question = paperQuestion.getQuestion();
            ExamQuestion examQuestion = examQuestionMap.get(question.getId());
            if (examQuestion != null && examQuestion.getAnswer().equals(question.getAnswer())) {
                score += paperQuestion.getScore();
            }
        }
        examination.setScore(score);
        examinationRepository.save(examination);
        return score;
    }

    @Override
    public Integer endExamination(Long userId, Long examinationId) throws CodeException {
        Examination examination = getCurrentExamination(userId);
        if (examination == null) {
            throw new CodeException(1, "no running examination");
        }
        if (!examination.getId().equals(examinationId)) {
            throw new CodeException(2, "examination not match");
        }
        examination.setStatus("end");
        return calculateScore(examination);
    }

    @Override
    @Transactional
    public Boolean saveAnswer(Long userId, Long examinationId, Long questionId, String answer) {
        Examination examination = getCurrentExamination(userId);
        // exam中不存在这个questionId
        if (examination == null || !examination.getId().equals(examinationId)) {
            return false;
        }

        // 验证questionId是否在paper中
        Paper paper = examination.getPaper();
        List<PaperQuestion> questions = paper.getQuestions();
        boolean exist = questions.stream()
                .map(PaperQuestion::getQuestion)
                .map(Question::getId)
                .anyMatch(id -> id.equals(questionId));
        if (!exist) {
            return false;
        }

        ExamQuestion examQuestion = new ExamQuestion();
        examQuestion.setId(new ExamQuestionPK(examinationId, questionId));
        examQuestion.setAnswer(answer);
        examQuestionRepository.save(examQuestion);

        return true;
    }

    public Page<Examination> getHistoryExaminations(long l, Pageable pageable) {
        return examinationRepository.findAllByUserId(l, pageable);
    }
}
