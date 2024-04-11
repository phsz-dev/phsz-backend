package com.phsz.testservice.testserviceprovider.service;

import com.phsz.testservice.testserviceprovider.pojo.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExaminationService {
    Examination getExaminationById(Long examinationId);

    Page<Examination> getAllExaminations(Pageable pageable);

    Page<Examination> getExaminationsByName(String examinationName, Pageable pageable);

    String addExamination(Examination examination);

    String updateExamination(Examination examination);

    String deleteExamination(Long examinationId);

    Examination startExamination(Long userId, Long paperId);

    Examination getCurrentExamination(Long userId);

    Boolean endExamination(Long userId, Long examinationId);

    Boolean saveAnswer(Long userId, Long examinationId, Long questionId, String answer);
}
