package com.phsz.caseservice.caseserviceprovider.controller;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import com.phsz.caseservice.caseserviceprovider.service.Impl.AssayServiceImpl;
import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/assays")
public class AssayController {

    @Autowired
    private AssayServiceImpl assayService;


    public AssayController(AssayServiceImpl assayService) {
        this.assayService = assayService;
    }

    // 获取所有化验
    @GetMapping
    public Result getAllAssays(@RequestParam("pageSize") int pageSize,
                               @RequestParam("pageNum") int pageNum,
                               @RequestParam(value = "orderColumn",defaultValue = "id") String orderColumn,
                               @RequestParam(value = "orderType",defaultValue = "ASC") String orderType) {
        Sort sort = orderType.equals("ASC") ? Sort.by(orderColumn).ascending() : Sort.by(orderColumn).descending();
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return Result.success("get all assay OK", new SimplePage<>(assayService.findAllAssays(pageable)));
    }

    // 添加新化验
    @PostMapping
    public Result addNewAssay(@RequestBody Assay assay) {
        Assay newAssay = assayService.addNewAssay(assay);
        if (newAssay == null) {
            return Result.error("add assay failed");
        }
        return Result.success("add assay successfully", null);
    }

    // 删除化验
    @DeleteMapping("/{assay_id}")
    public Result deleteAssayById(@PathVariable Long assay_id) {
        String message = assayService.deleteAssay(assay_id);
        if (message == null) {
            return Result.error("not found or error deleting");
        }
        return Result.success("delete assay successfully", null);
    }

    // 修改化验信息
    @PutMapping("/{assay_id}")
    public Result updateAssayById(@PathVariable Long assay_id, @RequestBody Assay assay) {
        Assay updatedAssay = assayService.updateAssay(assay_id, assay);
        if (updatedAssay == null) {
            return Result.error("not found or error updating");
        }
        return Result.success("update assay successfully", null);
    }

    // 获取单个化验信息
    @GetMapping("/{assay_id}")
    public Result findAssayById(@PathVariable Long assay_id) {
        Assay assayById = assayService.findAssayById(assay_id);
        if (assayById==null) {
            return Result.error("not found");
        }
        return Result.success("get assay successfully", assayById);
    }

    @GetMapping("/client/{assayId}")
    public Assay findAssayByIdClient(@PathVariable("assayId") Long assayId) {
        return assayService.findAssayById(assayId);
    }

    @GetMapping("/search")
    public Result searchAssay(@RequestParam("key") String key,
                              @RequestParam("head") String head,
                              @RequestParam("pageSize") int pageSize,
                              @RequestParam("pageNum") int pageNum,
                              @RequestParam(value = "orderColumn",defaultValue = "id") String orderColumn,
                              @RequestParam(value = "orderType",defaultValue = "ASC") String orderType) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(orderType.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, orderColumn));
        return Result.success("search assay OK", new SimplePage<>(assayService.searchAssay(key,head, pageable)));
    }
}
