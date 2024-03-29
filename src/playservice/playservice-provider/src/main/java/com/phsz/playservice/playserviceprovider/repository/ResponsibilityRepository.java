package com.phsz.playservice.playserviceprovider.repository;

import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long>, PagingAndSortingRepository<Responsibility, Long>{
	Page<Responsibility> findAllByResponsibilityNameLike(String responsibilityName, Pageable pageable);
	Page<Responsibility> findAllByRole(String role, Pageable pageable);
	Optional<Responsibility> deleteResponsibilityByResponsibilityId(Long responsibilityId);
}
