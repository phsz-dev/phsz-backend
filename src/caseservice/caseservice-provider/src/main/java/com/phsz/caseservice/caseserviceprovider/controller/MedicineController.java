package com.phsz.caseservice.caseserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.caseservice.caseserviceprovider.pojo.Medicine;
import com.phsz.caseservice.caseserviceprovider.service.Impl.MedicineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineServiceImpl medicineService;

    // 获取所有药品
    @GetMapping
    public Result getAllMedicines(@RequestParam("pageSize") int pageSize,
                                  @RequestParam("pageNum") int pageNum,
                                  @RequestParam(value = "orderColumn",defaultValue = "id") String orderColumn,
                                    @RequestParam(value = "orderType",defaultValue = "ASC") String orderType
    ) {
        Sort sort = orderType.equals("ASC") ? Sort.by(orderColumn).ascending() : Sort.by(orderColumn).descending();
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return Result.success("get all medicine OK", new SimplePage<>(medicineService.findAllMedicines(pageable)));
    }

    // 添加新药品
    @PostMapping
    public Result addNewMedicine(@RequestBody Medicine medicine) {
        Medicine newMedicine = medicineService.addNewMedicine(medicine);
        if (newMedicine == null) {
            return Result.error("add medicine failed");
        }
        return Result.success("add medicine successfully", null);
    }

    // 删除药品
    @DeleteMapping("/{medicine_id}")
    public Result deleteMedicineById(@PathVariable Long medicine_id) {
        String message = medicineService.deleteMedicine(medicine_id);
        if (message == null) {
            return Result.error("not found or error deleting");
        }
        return Result.success("delete medicine successfully", null);
    }

    // 修改药品信息
    @PutMapping("/{medicine_id}")
    public Result updateMedicineById(@PathVariable Long medicine_id, @RequestBody Medicine medicine) {
        Medicine updatedMedicine = medicineService.updateMedicine(medicine_id, medicine);
        if (updatedMedicine == null) {
            return Result.error("not found or error updating");
        }
        return Result.success("update medicine successfully", null);
    }

    // 获取单个药品信息
    @GetMapping("/{medicine_id}")
    public Result findMedicineById(@PathVariable Long medicine_id) {
        Medicine medicine = medicineService.findMedicineById(medicine_id);
        if (medicine == null) {
            return Result.error("not found");
        }
        return Result.success("find medicine successfully", medicine);
    }

    @GetMapping("/client/{medicine_id}")
    public Medicine findMedicineByIdClient(@PathVariable Long medicine_id) {
        Medicine medicineById = medicineService.findMedicineById(medicine_id);
        return medicineById;
    }
}