package com.phsz.playservice.playserviceprovider.repository;

import com.phsz.playservice.playserviceprovider.pojo.Procedure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Long>, PagingAndSortingRepository<Procedure, Long>{
	Page<Procedure> findAllByResponsibilityId(Long responsibilityId, Pageable pageable);
	Page<Procedure> findAllByProcedureNameLike(String procedureName, Pageable pageable);
	Optional<Procedure> deleteProcedureByProcedureId(Long procedureId);
}
