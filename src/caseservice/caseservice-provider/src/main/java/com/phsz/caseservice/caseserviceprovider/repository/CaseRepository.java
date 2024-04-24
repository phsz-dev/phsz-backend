package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.AdminCaseInfo;
import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.caseRelation.RoughCaseInfoDto;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case, Long>, PagingAndSortingRepository<Case, Long> {
    Page<Case> findAllByNameLike(String name, Pageable pageable);

    Optional<Case> deleteCaseById(Long caseId);


     @Query(value = "SELECT cc.id as id, c.name as name, c.brief as brief, c.submitTime as submitTime FROM CollectedCase cc " +
             "LEFT JOIN Case c ON cc.caseId = c.id WHERE cc.userId = :userId",
                countQuery = "SELECT count(*) FROM CollectedCase WHERE userId = :userId")
     Page<RoughCaseInfoDto> findRoughCaseInfoByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query(value = "SELECT c.id AS id, c.name AS name, c.description AS description, c.submit_time AS submitTime, c.brief AS brief, c.charge_id AS chargeId, c.doctor_name AS doctorName, (SELECT STRING_AGG(CAST(ca.assay_id AS TEXT), ',') FROM case_assay ca WHERE ca.case_id = c.id) AS assays, (SELECT STRING_AGG(CAST(cd.disease_id AS TEXT), ',') FROM case_disease cd WHERE cd.case_id = c.id) AS diseases, (SELECT STRING_AGG(CAST(cm.medicine_id AS TEXT), ',') FROM case_medicine cm WHERE cm.case_id = c.id) AS medicines, (SELECT STRING_AGG(CAST(cv.vaccine_id AS TEXT), ',') FROM case_vaccine cv WHERE cv.case_id = c.id) AS vaccines FROM app_case c",
            countQuery = "SELECT count(*) FROM app_case",
            nativeQuery = true)
    Page<AdminCaseInfo> findAllAdminCaseInfo(Pageable pageable);

}
