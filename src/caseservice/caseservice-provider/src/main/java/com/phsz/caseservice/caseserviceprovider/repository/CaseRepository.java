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


    @Query(value = "SELECT c.id, c.name, c.brief, c.submit_time AS submitTime FROM app_case c WHERE c.id IN :ids", nativeQuery = true)
    List<RoughCaseInfoDto> findRoughCaseInfoByIdList(@Param("ids") List<Long> ids);


}
