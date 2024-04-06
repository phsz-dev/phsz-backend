package com.phsz.vaccineservice.vaccineserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.vaccineservice.vaccineserviceprovider.pojo.Vaccine;
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

    // 获取所有疫苗
    @GetMapping
    public Result getAllVaccines(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("get all vaccine OK", vaccineService.findAllVaccines(pageable));
    }

    // 添加新疫苗
    @PostMapping
    public Result addNewVaccine(@RequestBody Vaccine vaccine) {
        Vaccine newVaccine = vaccineService.addNewVaccine(vaccine);
        if (newVaccine == null) {
            return Result.error("add vaccine failed");
        }
        return Result.success("add vaccine successfully", null);
    }

    // 删除疫苗
    @DeleteMapping("/{vaccine_id}")
    public Result deleteVaccineById(@PathVariable Long vaccine_id) {
        String message = vaccineService.deleteVaccine(vaccine_id);
        if (message == null) {
            return Result.error("not found or error deleting");
        }
        return Result.success("delete vaccine successfully", null);
    }

    // 修改疫苗信息
    @PutMapping("/{vaccine_id}")
    public Result updateVaccineById(@PathVariable Long vaccine_id, @RequestBody Vaccine vaccine) {
        Vaccine updatedVaccine = vaccineService.updateVaccine(vaccine_id, vaccine);
        if (updatedVaccine == null) {
            return Result.error("not found or error updating");
        }
        return Result.success("update vaccine successfully", null);
    }

    // 获取单个疫苗信息
    @GetMapping("/{vaccine_id}")
    public Result findVaccineById(@PathVariable Long vaccine_id) {
        Vaccine vaccine = vaccineService.findVaccineById(vaccine_id);
        if (vaccine == null) {
            return Result.error("not found");
        }
        return Result.success("find vaccine successfully", vaccine);
    }

    @GetMapping("/client/{vaccineId}")
    public Vaccine getVaccineByIdClient(@PathVariable("vaccineId") Long vaccineId) {
        return vaccineService.findVaccineById(vaccineId);
    }
}
