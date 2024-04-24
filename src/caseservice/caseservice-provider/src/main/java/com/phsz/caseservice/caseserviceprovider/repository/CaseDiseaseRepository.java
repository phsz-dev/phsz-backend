package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.CaseDisease;
import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.RoughCaseInfoDto;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseDiseaseRepository extends CrudRepository<CaseDisease, Long> {
    List<CaseDisease> findAllByCaseId(Long CaseId);

    Long deleteAllByCaseId(Long id);

    @Query(value = "SELECT id, name, brief, submit_time as submitTime FROM app_case WHERE id IN (SELECT case_id FROM case_disease WHERE disease_id = :diseaseId)", nativeQuery = true)
    Page<RoughCaseInfoDto> findRoughCaseInfoByDiseaseId(@Param("diseaseId") Long diseaseId, Pageable pageable);


}
