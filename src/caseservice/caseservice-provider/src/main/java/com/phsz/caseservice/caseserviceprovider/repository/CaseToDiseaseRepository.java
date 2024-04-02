package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToDisease;
import com.phsz.caseservice.caseserviceprovider.pojo.CaseToMedicine;
import com.phsz.caseservice.caseserviceprovider.pojo.RoughCaseInfo;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseToDiseaseRepository extends CrudRepository<CaseToDisease, Long> {
    List<CaseToDisease> findAllByCaseId(Long CaseId);

    Long deleteAllByCaseId(Long id);

    @Query(value = "SELECT id, name, description, submitTime FROM `case` WHERE id IN (SELECT case_id FROM case_to_disease WHERE diseaseId = :diseaseId)", nativeQuery = true)
    Page<RoughCaseInfo> findRoughCaseInfoByDiseaseId(@Param("diseaseId") Long diseaseId, Pageable pageable);

}
