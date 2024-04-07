package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.RoughCaseInfoDto;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case, Long>, PagingAndSortingRepository<Case, Long> {
    Page<Case> findAllByNameLike(String name, Pageable pageable);

    Optional<Case> deleteCaseById(Long caseId);


    @Query(value = "SELECT c.id, c.name, c.brief, c.submit_time AS submitTime FROM collected_case cc " +
            "LEFT JOIN `case` c ON cc.case_id = c.id WHERE cc.user_id = :userId",
            countQuery = "SELECT count(*) FROM collected_case cc WHERE cc.user_id = :userId",
            nativeQuery = true)
    Page<RoughCaseInfoDto> findRoughCaseInfoByUserId(@Param("userId") Long userId, Pageable pageable);


}
