package com.phsz.medicineservice.medicineserviceprovider.repository;

import com.phsz.medicineservice.medicineserviceprovider.pojo.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    // 这里可以根据需要添加自定义查询方法
    // 例如根据药品名称查找：List<Medicine> findByName(String name);
    Optional<Medicine> deleteMedicineById(Long medicineId);
}