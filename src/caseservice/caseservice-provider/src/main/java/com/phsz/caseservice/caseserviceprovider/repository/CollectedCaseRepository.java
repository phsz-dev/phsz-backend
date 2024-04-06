package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.CollectedCase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollectedCaseRepository extends CrudRepository<CollectedCase,Long>{
    List<CollectedCase> findCollectedCasesByUserId(Long userId);

    Page<CollectedCase> findCollectedCaseByUserId(Long userId, Pageable pageable);



}
