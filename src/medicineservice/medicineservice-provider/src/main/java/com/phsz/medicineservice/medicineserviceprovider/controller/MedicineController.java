package com.phsz.medicineservice.medicineserviceprovider.controller;

import com.phsz.medicineservice.medicineserviceprovider.pojo.Medicine;
import com.phsz.medicineservice.medicineserviceprovider.pojo.Result;
import com.phsz.medicineservice.medicineserviceprovider.service.Impl.MedicineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineServiceImpl medicineService;

    @Autowired
    Result result;

    // 获取所有药品
    @GetMapping
    public Result getAllMedicines(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        result.setData(medicineService.findAllMedicines(pageable));
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 添加新药品
    @PostMapping
    public Result addNewMedicine(@RequestBody Medicine medicine) {
        Medicine newMedicine = medicineService.addNewMedicine(medicine);
        if (newMedicine == null) {
            result.setCode(0);
            result.setMessage("already exists");
            return result;
        }
        result.setData(newMedicine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 删除药品
    @DeleteMapping("/{medicine_id}")
    public Result deleteMedicineById(@PathVariable Long medicine_id) {
        String message = medicineService.deleteMedicine(medicine_id);
        if (message == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(message);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 修改药品信息
    @PutMapping("/{medicine_id}")
    public Result updateMedicineById(@PathVariable Long medicine_id, @RequestBody Medicine medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(medicine_id, medicine);
        if (updatedMedicine == null) {
            result.setCode(0);
            result.setMessage("not found or error updating");
            return result;
        }
        result.setData(updatedMedicine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 获取单个药品信息
    @GetMapping("/{medicine_id}")
    public Result findMedicineById(@PathVariable Long medicine_id) {
        Medicine medicine = medicineService.findMedicineById(medicine_id);
        if (medicine == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(medicine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    @GetMapping("/client/{medicine_id}")
    public Medicine findMedicineByIdClient(@PathVariable Long medicine_id) {
        Medicine medicineById = medicineService.findMedicineById(medicine_id);
        return medicineById;
    }
}