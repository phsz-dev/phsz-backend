package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToAssay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseToAssayRepository extends JpaRepository<CaseToAssay, Long>, PagingAndSortingRepository<CaseToAssay, Long>{
	Page<CaseToAssay> findAllByCaseId(Long CaseId, Pageable pageable);
	Long deleteAllByCaseId(Long assayId);
}
