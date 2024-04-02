package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseMedicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CaseMedicineRepository extends JpaRepository<CaseMedicine, Long>, PagingAndSortingRepository<CaseMedicine, Long>{
       Page<CaseMedicine> findAllByCaseId(Long caseId, Pageable pageable);
       Long deleteAllByCaseId(Long medicineId);
}
