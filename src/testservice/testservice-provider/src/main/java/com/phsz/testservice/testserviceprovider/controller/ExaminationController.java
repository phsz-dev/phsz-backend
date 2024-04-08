package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.testservice.testserviceprovider.pojo.Examination;
import com.phsz.testservice.testserviceprovider.service.Impl.ExaminationServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/exam")
public class ExaminationController {
    @Resource
    ExaminationServiceImpl examinationService;

    public ExaminationController(ExaminationServiceImpl examinationService) {
        this.examinationService = examinationService;
    }

    @GetMapping("/{examinationId}")
    public Result getExaminationById(@PathVariable Long examinationId) {
        Examination examination = examinationService.getExaminationById(examinationId);
        if (examination == null) {
            return Result.error("not found");
        }
        return Result.success("success", examination);
    }

    @GetMapping
    public Result getAllExaminations(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Examination> examinations = examinationService.getAllExaminations(pageable);
        return Result.success("success", new SimplePage<>(examinations));
    }

    @GetMapping("/name")
    public Result getExaminationsByName(@RequestParam("examinationName") String examinationName, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Examination> examinations = examinationService.getExaminationsByName(examinationName, pageable);
        return Result.success("success", new SimplePage<>(examinations));
    }

    @PostMapping
    public Result addExamination(@RequestHeader("Username") String username, @RequestBody Long paperId) {
        try {
            Examination examination = examinationService.startExamination(username, paperId);
            return Result.success("success", examination);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result updateExamination(@RequestBody Examination examination) {
        String s = examinationService.updateExamination(examination);
        if (s == null) {
            return Result.error("not found");
        }
        return Result.success("success", s);
    }

    @DeleteMapping("/{examinationId}")
    public Result deleteExamination(@PathVariable Long examinationId) {
        String s = examinationService.deleteExamination(examinationId);
        if (s == null) {
            return Result.error("not found");
        }
        return Result.success("success", s);
    }
}
