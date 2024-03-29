package com.phsz.testservice.testserviceprovider.repository;

import com.phsz.testservice.testserviceprovider.pojo.Examination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ExaminationRepository extends JpaRepository<Examination, Long>, PagingAndSortingRepository<Examination, Long>{
	Page<Examination> findAllByExaminationNameLike(String examinationName, Pageable pageable);
	Optional<Examination> deleteExaminationByExaminationId(Long examinationId);

}
