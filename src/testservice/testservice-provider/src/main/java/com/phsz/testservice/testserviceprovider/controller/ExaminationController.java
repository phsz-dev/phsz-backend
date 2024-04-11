package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.common.CodeException;
import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.testservice.testserviceprovider.pojo.AnswerRequest;
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
        System.out.println(examination);
        if (examination == null) {
            return Result.error("not found");
        }
        return Result.success("success", examination);
    }

    @GetMapping
    public Result getAllExaminations(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Examination> examinations = examinationService.getAllExaminations(pageable);
        return Result.success("success", new SimplePage<>(examinations));
    }

    @GetMapping("/name")
    public Result getExaminationsByName(@RequestParam("examinationName") String examinationName,
                                        @RequestParam(value="pageNum", defaultValue = "0") int pageNum,
                                        @RequestParam(value="pageSize", defaultValue = "10") int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Examination> examinations = examinationService.getExaminationsByName(examinationName, pageable);
        return Result.success("success", new SimplePage<>(examinations));
    }

    @PostMapping
    public Result addExamination(@RequestHeader("UserId") String userId, @RequestBody Long paperId) {
        try {
            Examination examination = examinationService.startExamination(Long.parseLong(userId), paperId);
            return Result.success("success", examination);
        } catch (CodeException e) {
            return Result.error(e.getCode(), e.getMessage());
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

    @PostMapping("/answer")
    public Result saveAnswer(@RequestHeader("UserId") String userId, @RequestBody AnswerRequest answerRequest) {
        Boolean b = examinationService.saveAnswer(Long.parseLong(userId), answerRequest.getExaminationId(), answerRequest.getQuestionId(), answerRequest.getAnswer());
        if (!b) {
            return Result.error("not found");
        }
        return Result.success("success", true);
    }

    @GetMapping("/current")
    public Result getCurrentExamination(@RequestHeader("UserId") String userId) {
        Examination examination = examinationService.getCurrentExamination(Long.parseLong(userId));
        if (examination == null) {
            return Result.error("not found");
        }
        return Result.success("success", examination);
    }

    @PutMapping("/end")
    public Result endExamination(@RequestHeader("UserId") String userId, @RequestBody Long examinationId) {
        Boolean b = examinationService.endExamination(Long.parseLong(userId), examinationId);
        if (b == null) {
            return Result.error("not found");
        }
        return Result.success("success", b);
    }
}
