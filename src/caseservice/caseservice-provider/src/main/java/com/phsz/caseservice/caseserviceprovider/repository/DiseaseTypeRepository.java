package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.DiseaseType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiseaseTypeRepository extends CrudRepository<DiseaseType, Long> {
    List<DiseaseType> findAll();

}
