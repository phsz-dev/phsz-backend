package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToMedicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseToMedicineRepository extends JpaRepository<CaseToMedicine, Long>, PagingAndSortingRepository<CaseToMedicine, Long>{
       Page<CaseToMedicine> findAllByCaseId(Long CaseId, Pageable pageable);
       Long deleteAllByCaseId(Long medicineId);
}
