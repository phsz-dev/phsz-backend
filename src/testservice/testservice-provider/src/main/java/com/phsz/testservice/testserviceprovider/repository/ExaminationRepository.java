package com.phsz.testservice.testserviceprovider.repository;

import com.phsz.testservice.testserviceprovider.pojo.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ExaminationRepository extends JpaRepository<Examination, Long>, PagingAndSortingRepository<Examination, Long> {
    Page<Examination> findAllByNameLike(String name, Pageable pageable);

    Integer deleteExaminationById(Long examinationId);

    Examination findExaminationByUserIdAndStatus(Long userId, String start);

//    // 保存答案到exam_question表
//    @Modifying
//    @Transactional
//    @Query("update ExamQuestion eq set eq.answer = ?3 where eq.examination.id = ?1 and eq.question.id = ?2")
//    Integer saveAnswer(Long examinationId, Long questionId, String answer);
}
