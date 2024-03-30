package com.phsz.medicineservice.medicineserviceapi.service;

import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    List<Medicine> findAll();
    Optional<Medicine> findById(Long medicineId);
    Optional<Medicine> findByName(String medicineName);
    String save(Medicine medicine);
    String update(Medicine medicine);
    String deleteById(Long medicineId);
}