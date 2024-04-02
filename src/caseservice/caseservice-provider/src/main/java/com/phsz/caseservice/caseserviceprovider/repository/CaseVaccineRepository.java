package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseVaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseVaccineRepository extends JpaRepository<CaseVaccine, Long>, PagingAndSortingRepository<CaseVaccine, Long>{
	Page<CaseVaccine> findAllByCaseId(Long CaseId, Pageable pageable);
	Long deleteAllByCaseId(Long vaccineId);
}
