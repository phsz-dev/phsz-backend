package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CaseToDisease;
import com.phsz.caseservice.caseserviceprovider.pojo.CaseToMedicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CaseToDiseaseRepository extends CrudRepository<CaseToDisease, Long> {
    List<CaseToDisease> findAllByCaseId(Long CaseId);

    Long deleteAllByCaseId(Long id);

}
