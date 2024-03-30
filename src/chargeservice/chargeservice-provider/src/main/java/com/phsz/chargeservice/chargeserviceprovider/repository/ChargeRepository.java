package com.phsz.chargeservice.chargeserviceprovider.repository;

import com.phsz.chargeservice.chargeserviceprovider.pojo.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据疫苗名称查找：List<Vaccine> findByName(String name);
    Optional<Charge> deleteVaccineByVaccineId(Long ChargeId);
}