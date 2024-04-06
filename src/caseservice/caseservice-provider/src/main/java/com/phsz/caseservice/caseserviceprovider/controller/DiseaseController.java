package com.phsz.caseservice.caseserviceprovider.controller;

import com.phsz.caseservice.caseserviceprovider.service.Impl.DiseaseServiceImpl;
import com.phsz.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diseases")
public class DiseaseController {

    private final DiseaseServiceImpl diseaseService;

    @Autowired
    public DiseaseController(DiseaseServiceImpl diseaseService) {
        this.diseaseService = diseaseService;
    }

    /**
     * 查找疾病序列
     *
     * @return Result
     */
    @GetMapping
    public Result getAllDisease() {
        return Result.success("查找疾病序列成功", diseaseService.getAllDisease());
    }
}
