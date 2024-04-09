package com.phsz.testservice.testserviceprovider.repository;

import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.PaperInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper, Long>, PagingAndSortingRepository<Paper, Long> {
    Page<Paper> findAllByNameLike(String name, Pageable pageable);

    Optional<Paper> deletePaperById(Long paperId);

    @Query("SELECT p.id as id, p.name as name, p.content as content, p.totalScore as totalScore, p.durationSeconds as durationSeconds FROM Paper p WHERE p.id = ?1")
    PaperInfo findPaperInfoById(Long paperId);

    @Query("SELECT p.id as id, p.name as name, p.content as content, p.totalScore as totalScore, p.durationSeconds as durationSeconds FROM Paper p")
    Page<PaperInfo> findAllInfoBy(Pageable pageable);

    @Query("SELECT p.id as id, p.name as name, p.content as content, p.totalScore as totalScore, p.durationSeconds as durationSeconds FROM Paper p WHERE p.name like %?1%")
    Page<PaperInfo> findAllInfoByNameLike(String name, Pageable pageable);
}
