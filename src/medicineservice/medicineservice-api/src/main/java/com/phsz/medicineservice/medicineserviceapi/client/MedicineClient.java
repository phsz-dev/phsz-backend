package com.phsz.medicineservice.medicineserviceapi.client;

import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "medicineservice-provider")
public interface MedicineClient {

//    @GetMapping("/api/medicines")
//    Result getAllMedicines(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/api/medicines/client/{medicine_id}")
    Medicine getMedicineById(@PathVariable("medicine_id") Long medicine_id);
//
//    @PostMapping("/api/medicine")
//    Result addMedicine(@RequestBody Medicine medicine);
//
//    @PutMapping("/api/medicine/{medicine_id}")
//    Result updateMedicine(@PathVariable("medicine_id") Long medicineId, @RequestBody Medicine medicine);
//
//    @DeleteMapping("/api/medicine/{medicine_id}")
//    Result deleteMedicine(@PathVariable("medicine_id") Long medicineId);
}
