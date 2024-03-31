package com.phsz.hospitalizationservice.hospitalizationserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.hospitalizationservice.hospitalizationserviceapi.entity.Hospitalization;
import com.phsz.hospitalizationservice.hospitalizationserviceprovider.service.HospitalizationServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitalizations")
public class HospitalizationController {
    @Resource
    private final HospitalizationServiceImpl hospitalizationService;
    public HospitalizationController(HospitalizationServiceImpl hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }

    @GetMapping("/hospitalizations")
    public Result findAll() {
        return Result.success("success", hospitalizationService.findAll());
    }

    @GetMapping("/hospitalizations/{hospitalizationId}")
    public Result findById(Long hospitalizationId) {
        return Result.success("success", hospitalizationService.findById(hospitalizationId));
    }

    @PostMapping("/hospitalizations")
    public Result save(Hospitalization hospitalization) {
        return Result.success("success", hospitalizationService.save(hospitalization));
    }

    @PutMapping("/hospitalizations")
    public Result update(Hospitalization hospitalization) {
        return Result.success("success", hospitalizationService.update(hospitalization));
    }

    @DeleteMapping("/hospitalizations/{hospitalizationId}")
    public Result deleteById(Long hospitalizationId) {
        return Result.success("success", hospitalizationService.deleteById(hospitalizationId));
    }
}
