package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Medicine;
import com.phsz.caseservice.caseserviceprovider.repository.MedicineRepository;
import com.phsz.caseservice.caseserviceprovider.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public Page<Medicine> findAllMedicines(Pageable pageable) {
        return medicineRepository.findAll(pageable);
    }

    @Override
    public Medicine findMedicineById(Long id) {
        Optional<Medicine> byId = medicineRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public Medicine addNewMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found for this id :: " + id));
        return medicineRepository.save(medicineDetails);
    }

    @Override
    public String deleteMedicine(Long id) {
        Optional<Medicine> byId = medicineRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        // 注意: 这里假设您有一个方法在您的repository里来删除一个药品通过药品ID。
        // 这是基于原代码，但实际上您可能需要调整这个逻辑以匹配您的repository的实际功能。
        Optional<Medicine> medicine = medicineRepository.deleteMedicineById(id); // 注意: 这里需要实现这个方法在您的repository中
        return medicine.get().getId().toString(); // 修改为 getMedicineId()
    }
}