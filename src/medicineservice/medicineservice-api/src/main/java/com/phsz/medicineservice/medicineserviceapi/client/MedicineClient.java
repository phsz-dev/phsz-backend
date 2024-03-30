package com.phsz.medicineservice.medicineserviceapi.client;

import com.phsz.medicineservice.medicineserviceapi.pojo.Result;
import com.phsz.medicineservice.medicineserviceapi.pojo.Medicine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "medicineservice-provider")
public interface MedicineClient {

    @GetMapping("/medicines")
    Result getAllMedicines(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/medicine/{medicine_id}")
    Result getMedicineById(@PathVariable("medicine_id") String medicineId);

    @PostMapping("/medicine")
    Result addMedicine(@RequestBody Medicine medicine);

    @PutMapping("/medicine/{medicine_id}")
    Result updateMedicine(@PathVariable("medicine_id") String medicineId, @RequestBody Medicine medicine);

    @DeleteMapping("/medicine/{medicine_id}")
    Result deleteMedicine(@PathVariable("medicine_id") String medicineId);
}
