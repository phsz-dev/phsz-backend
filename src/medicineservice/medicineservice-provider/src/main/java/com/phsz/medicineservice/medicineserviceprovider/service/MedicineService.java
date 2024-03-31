package com.phsz.medicineservice.medicineserviceprovider.service;

import com.phsz.medicineservice.medicineserviceprovider.pojo.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicineService {
    // 支持分页查询的方法
    Page<Medicine> findAllMedicines(Pageable pageable);

    // 根据ID查找药品
    Medicine findMedicineById(Long id);

    // 添加新的药品信息
    Medicine addNewMedicine(Medicine medicine);

    // 更新药品信息
    Medicine updateMedicine(Long id, Medicine medicine);

    // 根据ID删除药品信息
    String deleteMedicine(Long id);
}