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

    Optional<Examination> deleteExaminationById(Long examinationId);

    Examination findExaminationByUserIdAndStatus(Long userId, String start);

    // 把某username所有end_time小于当前时间的考试状态改为end
    @Transactional
    @Modifying
    @Query("update Examination e set e.status = 'end' where e.userId = ?1 and e.endTime < current_timestamp")
    int updateExaminationStatus(Long userId);
}
