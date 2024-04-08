package com.phsz.testservice.testserviceprovider.repository;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, Long>, PagingAndSortingRepository<Paper, Long> {
    Page<Paper> findAllByNameLike(String name, Pageable pageable);

    Optional<Paper> deletePaperById(Long paperId);
}
