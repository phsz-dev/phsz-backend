package com.phsz.playservice.playserviceprovider.repository;

import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long>, PagingAndSortingRepository<Responsibility, Long>{
	Page<Responsibility> findAllByNameLike(String responsibilityName, Pageable pageable);
	Page<Responsibility> findAllByRole(Role role, Pageable pageable);

	List<Responsibility> findAllByRole(Role role);
	Optional<Responsibility> deleteResponsibilityById(Long responsibilityId);
}
