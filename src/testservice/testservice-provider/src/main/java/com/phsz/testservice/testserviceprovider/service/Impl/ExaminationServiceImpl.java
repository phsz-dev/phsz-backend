package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.common.CodeException;
import com.phsz.testservice.testserviceprovider.pojo.*;
import com.phsz.testservice.testserviceprovider.repository.ExaminationRepository;
import com.phsz.testservice.testserviceprovider.service.ExaminationService;
import com.phsz.testservice.testserviceprovider.service.PaperService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminationServiceImpl implements ExaminationService {
    @Resource
    private final ExaminationRepository examinationRepository;
    @Resource
    private PaperService paperService;

    public ExaminationServiceImpl(ExaminationRepository examinationRepository) {
        this.examinationRepository = examinationRepository;
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
            endExamination(userId, examination.getId());
            return null;
        }
        return examination;
    }

    @Override
    public Boolean endExamination(Long userId, Long examinationId) {
        Examination examination = getCurrentExamination(userId);
        if (examination == null || !examination.getId().equals(examinationId)) {
            return false;
        }
        examination.setStatus("end");
        // 计算分数
        int score = 0;
        // 创建一个HashMap来存储Question ID和ExamQuestion的对应关系
        Map<Long, ExamQuestion> examQuestionMap = new HashMap<>();
        for (ExamQuestion examQuestion : examination.getQuestions()) {
            examQuestionMap.put(examQuestion.getQuestion().getId(), examQuestion);
        }
        // 遍历所有的PaperQuestion
        List<PaperQuestion> questions = examination.getPaper().getQuestions();
        for (PaperQuestion paperQuestion : questions) {
            Question question = paperQuestion.getQuestion();
            // 直接使用Question ID从Map中获取ExamQuestion
            ExamQuestion examQuestion = examQuestionMap.get(question.getId());
            if (examQuestion != null && examQuestion.getAnswer().equals(question.getAnswer())) {
                score += paperQuestion.getScore();
            }
        }
        examination.setScore(score);
        examinationRepository.save(examination);
        return true;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Boolean saveAnswer(Long userId, Long examinationId, Long questionId, String answer) {
        Examination examination = getCurrentExamination(userId);
        // exam中不存在这个questionId
        if (examination == null || !examination.getId().equals(examinationId)) {
            return false;
        }
        ExamQuestion examQuestion = entityManager.createQuery("SELECT eq FROM ExamQuestion eq WHERE eq.examination.id = :examinationId AND eq.question.id = :questionId", ExamQuestion.class)
                .setParameter("examinationId", examinationId)
                .setParameter("questionId", questionId)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (examQuestion == null) {
            // 没有找到，创建一个新的ExamQuestion实例
            ExamQuestion newExamQuestion = new ExamQuestion();
            newExamQuestion.setExamination(examination);
            Question question = entityManager.find(Question.class, questionId);
            newExamQuestion.setQuestion(question);
            newExamQuestion.setAnswer(answer);
            entityManager.persist(newExamQuestion);
        } else {
            // 找到了，更新它
            examQuestion.setAnswer(answer);
            entityManager.merge(examQuestion);
        }
        return true;
    }

}
