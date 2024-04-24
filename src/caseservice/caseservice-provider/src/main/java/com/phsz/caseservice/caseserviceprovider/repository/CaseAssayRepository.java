package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.CaseAssay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseAssayRepository extends JpaRepository<CaseAssay, Long>, PagingAndSortingRepository<CaseAssay, Long> {
    Page<CaseAssay> findAllByCaseId(Long CaseId, Pageable pageable);

    Long deleteAllByCaseId(Long assayId);
}
