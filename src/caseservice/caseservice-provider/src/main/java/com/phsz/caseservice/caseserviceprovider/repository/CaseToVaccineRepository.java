package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToVaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseToVaccineRepository extends JpaRepository<CaseToVaccine, Long>, PagingAndSortingRepository<CaseToVaccine, Long>{
	Page<CaseToVaccine> findAllByCaseId(Long CaseId, Pageable pageable);
	Long deleteAllByCaseId(Long vaccineId);
}
