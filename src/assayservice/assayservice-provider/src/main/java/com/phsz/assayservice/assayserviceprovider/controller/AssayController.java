package com.phsz.assayservice.assayserviceprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.phsz.assayservice.assayserviceprovider.pojo.Assay;
import com.phsz.assayservice.assayserviceprovider.pojo.Result;
import com.phsz.assayservice.assayserviceprovider.service.Impl.AssayServiceImpl;

import java.util.Optional;

@RestController
@RequestMapping("/api/assays")
public class AssayController {

    @Autowired
    private AssayServiceImpl assayService;

    @Autowired
    Result result;

    public AssayController(AssayServiceImpl assayService, Result result) {
        this.assayService = assayService;
        this.result = result;
    }

    // 获取所有化验
    @GetMapping
    public Result getAllAssays(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        result.setData(assayService.findAllAssays(pageable));
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 添加新化验
    @PostMapping
    public Result addNewAssay(@RequestBody Assay assay) {
        Assay newAssay = assayService.addNewAssay(assay);
        if (newAssay == null) {
            result.setCode(0);
            result.setMessage("already exists");
            return result;
        }
        result.setData(newAssay);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 删除化验
    @DeleteMapping("/{assay_id}")
    public Result deleteAssayById(@PathVariable Long assay_id) {
        String message = assayService.deleteAssay(assay_id);
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

    // 修改化验信息
    @PutMapping("/{assay_id}")
    public Result updateAssayById(@PathVariable Long assay_id, @RequestBody Assay assay) {
        Assay updatedAssay = assayService.updateAssay(assay_id, assay);
        if (updatedAssay == null) {
            result.setCode(0);
            result.setMessage("not found or error updating");
            return result;
        }
        result.setData(updatedAssay);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 获取单个化验信息
    @GetMapping("/{assay_id}")
    public Result findAssayById(@PathVariable Long assay_id) {
        Optional<Assay> assayById = assayService.findAssayById(assay_id);
        if (assayById.isEmpty()) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(assayById.get());
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }
    @GetMapping("/client/{assayId}")
    public Assay findAssayByIdClient(@PathVariable("assayId") Long assayId){
        Optional<Assay> assayById = assayService.findAssayById(assayId);
        return assayById.get();
    }
}
