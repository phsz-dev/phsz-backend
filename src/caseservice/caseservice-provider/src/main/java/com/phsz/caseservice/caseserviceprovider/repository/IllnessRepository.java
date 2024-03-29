package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Illness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IllnessRepository extends JpaRepository<Illness, Long>, PagingAndSortingRepository<Illness, Long>{
}
