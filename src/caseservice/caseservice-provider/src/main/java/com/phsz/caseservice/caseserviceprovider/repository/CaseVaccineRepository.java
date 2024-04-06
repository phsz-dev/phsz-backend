package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseVaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CaseVaccineRepository extends JpaRepository<CaseVaccine, Long>, PagingAndSortingRepository<CaseVaccine, Long> {
    Page<CaseVaccine> findByCaseId(Long CaseId, Pageable pageable);

    List<CaseVaccine> findByCaseId(Long caseId);

    Long deleteAllByCaseId(Long vaccineId);
}
