package com.phsz.caseservice.caseserviceprovider.repository;

import com.phsz.caseservice.caseserviceprovider.pojo.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据疫苗名称查找：List<Vaccine> findByName(String name);
    Optional<Vaccine> deleteVaccineById(Long VaccineId);
}