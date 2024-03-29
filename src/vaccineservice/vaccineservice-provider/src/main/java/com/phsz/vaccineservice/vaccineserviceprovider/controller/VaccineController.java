package com.phsz.vaccineservice.vaccineserviceprovider.controller;

import jakarta.annotation.Resource;


import com.phsz.vaccineservice.vaccineserviceprovider.pojo.Vaccine;
import com.phsz.vaccineservice.vaccineserviceprovider.pojo.Result;
import com.phsz.vaccineservice.vaccineserviceprovider.service.Impl.VaccineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccines")
public class VaccineController {

    @Autowired
    private VaccineServiceImpl vaccineService;

    @Autowired
    Result result;

    // 获取所有疫苗
    @GetMapping
    public Result getAllVaccines(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        result.setData(vaccineService.findAllVaccines(pageable));
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 添加新疫苗
    @PostMapping
    public Result addNewVaccine(@RequestBody Vaccine vaccine) {
        Vaccine newVaccine = vaccineService.addNewVaccine(vaccine);
        if (newVaccine == null) {
            result.setCode(0);
            result.setMessage("already exists");
            return result;
        }
        result.setData(newVaccine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 删除疫苗
    @DeleteMapping("/{vaccine_id}")
    public Result deleteVaccineById(@PathVariable Long vaccine_id) {
        String message = vaccineService.deleteVaccine(vaccine_id);
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

    // 修改疫苗信息
    @PutMapping("/{vaccine_id}")
    public Result updateVaccineById(@PathVariable Long vaccine_id, @RequestBody Vaccine vaccine) {
        Vaccine updatedVaccine = vaccineService.updateVaccine(vaccine_id, vaccine);
        if (updatedVaccine == null) {
            result.setCode(0);
            result.setMessage("not found or error updating");
            return result;
        }
        result.setData(updatedVaccine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 获取单个疫苗信息
    @GetMapping("/{vaccine_id}")
    public Result findVaccineById(@PathVariable Long vaccine_id) {
        Vaccine vaccine = vaccineService.findVaccineById(vaccine_id);
        if (vaccine == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(vaccine);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }
}
