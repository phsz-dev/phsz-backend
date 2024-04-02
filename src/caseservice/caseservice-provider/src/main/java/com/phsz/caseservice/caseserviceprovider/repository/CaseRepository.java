package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CaseRepository extends JpaRepository<Case,Long>, PagingAndSortingRepository<Case,Long> {
	Page<Case> findAllByNameLike(String name, Pageable pageable);

	Optional<Case> deleteCaseById(Long caseId);


}
