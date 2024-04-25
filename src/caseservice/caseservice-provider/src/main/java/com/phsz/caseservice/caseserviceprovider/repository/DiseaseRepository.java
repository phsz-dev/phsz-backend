package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long>, PagingAndSortingRepository<Disease, Long> {
    List<Disease> findByTypeId(Long diseaseTypeId);


}
