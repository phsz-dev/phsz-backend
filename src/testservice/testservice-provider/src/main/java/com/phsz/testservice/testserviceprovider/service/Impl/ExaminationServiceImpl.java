package com.phsz.testservice.testserviceprovider.service.Impl;

import com.phsz.common.CodeException;
import com.phsz.testservice.testserviceprovider.pojo.Examination;
import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.repository.ExaminationRepository;
import com.phsz.testservice.testserviceprovider.service.ExaminationService;
import com.phsz.testservice.testserviceprovider.service.PaperService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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
    public String deleteExamination(Long examinationId) {
        Optional<Examination> examination = examinationRepository.deleteExaminationById(examinationId);
        return examination.map(value -> value.getId().toString()).orElse(null);
    }

    @Override
    public Examination startExamination(Long userId, Long paperId) {
        Paper paper = paperService.getPaperById(paperId);
        if (paper == null) {
            throw new CodeException(1, "not found");
        }
        // 检查是否有正在进行的考试
        examinationRepository.updateExaminationStatus(userId);
        Examination running = examinationRepository.findExaminationByUserIdAndStatus(userId, "start");
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
}
