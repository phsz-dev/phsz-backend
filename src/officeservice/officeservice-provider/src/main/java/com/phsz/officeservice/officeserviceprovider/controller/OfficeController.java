package com.phsz.officeservice.officeserviceprovider.controller;

import com.phsz.officeservice.officeserviceapi.entity.Office;
import com.phsz.officeservice.officeserviceprovider.service.OfficeServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import com.phsz.common.Result;

@RestController
@RequestMapping("/offices")
public class OfficeController {
    @Resource
    private final OfficeServiceImpl officeService;
    public OfficeController(OfficeServiceImpl officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/offices")
    public Result findAll() {
        return Result.success("success", officeService.findAll());
    }

    @GetMapping("/offices/{officeId}")
    public Result findById(Long officeId) {
        return Result.success("success", officeService.findById(officeId));
    }

    @GetMapping("/offices/{officeName}")
    public Result findByName(String officeName) {
        return Result.success("success", officeService.findByName(officeName));
    }

    @PostMapping("/offices")
    public Result save(Office office) {
        return Result.success("success", officeService.save(office));
    }

    @PutMapping("/offices")
    public Result update(Office office) {
        return Result.success("success", officeService.update(office));
    }

    @DeleteMapping("/offices/{officeId}")
    public Result deleteById(Long officeId) {
        return Result.success("success", officeService.deleteById(officeId));
    }
}
