package com.phsz.testservice.testserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.testservice.testserviceprovider.pojo.Paper;
import com.phsz.testservice.testserviceprovider.pojo.PaperInfo;
import com.phsz.testservice.testserviceprovider.service.Impl.PaperServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/paper")
public class PaperController {
    PaperServiceImpl paperService;

    public PaperController(PaperServiceImpl paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/{id}")
    public Result getPaperById(@PathVariable Long id) {
        Paper paperById = paperService.getPaperById(id);
        if (paperById == null) {
            return Result.error("not found");
        }
        return Result.success("success", paperById);
    }

    @GetMapping
    public Result getAllPapers(@RequestParam() int pageSize, @RequestParam int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("success", new SimplePage<>(paperService.getAllPapers(pageable)));
    }

    @PostMapping
    public Result addPaper(@RequestBody Paper paper) {
        Long resId = paperService.addPaper(paper);
        if (resId == null) {
            return Result.error("already exists");
        }
        return Result.success("success", resId);
    }

    @PutMapping
    public Result updatePaper(@RequestBody Paper paper) {
        Long paperId = paper.getId();
        if (paperService.getPaperById(paperId) == null) {
            return Result.error("not found");
        }
        Long resId = paperService.updatePaper(paper);
        if (resId == null) {
            return Result.error("not found");
        }
        return Result.success("success", resId);
    }

    @DeleteMapping("/{id}")
    public Result deletePaper(@PathVariable Long id) {
        Long s = paperService.deletePaper(id);
        if (s == null) {
            return Result.error("not found");
        }
        return Result.success("success", s);
    }

    @GetMapping("/info/{id}")
    public Result getPaperInfoById(@PathVariable Long id) {
        PaperInfo paperInfoById = paperService.getPaperInfoById(id);
        if (paperInfoById == null) {
            return Result.error("not found");
        }
        return Result.success("success", paperInfoById);
    }

    @GetMapping("/info")
    public Result getAllPaperInfo(@RequestParam int pageSize, @RequestParam int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<PaperInfo> allPaperInfo = paperService.getAllPaperInfo(pageable);
        return Result.success("success", new SimplePage<>(allPaperInfo));
    }

    @GetMapping("/name")
    public Result getPaperInfoByName(@RequestParam String name, @RequestParam int pageSize, @RequestParam int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<PaperInfo> papersByName = paperService.getPapersByName(name, pageable);
        return Result.success("success", new SimplePage<>(papersByName));
    }

}
