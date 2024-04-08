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


     @Query(value = "SELECT cc.id as id, c.name as name, c.brief as brief, c.submitTime as submitTime FROM CollectedCase cc " +
             "LEFT JOIN Case c ON cc.caseId = c.id WHERE cc.userId = :userId",
                countQuery = "SELECT count(*) FROM CollectedCase WHERE userId = :userId")
     Page<RoughCaseInfoDto> findRoughCaseInfoByUserId(@Param("userId") Long userId, Pageable pageable);


}
