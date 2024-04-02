package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToIllness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseToIllnessRepository extends JpaRepository<CaseToIllness,Long>, PagingAndSortingRepository<CaseToIllness,Long> {
	Page<CaseToIllness> findAllByCaseId(Long caseId, Pageable pageable);
	Long deleteAllByCaseId(Long illnessId);
}
