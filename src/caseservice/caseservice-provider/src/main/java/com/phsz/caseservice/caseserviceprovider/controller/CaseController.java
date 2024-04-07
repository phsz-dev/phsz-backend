package com.phsz.caseservice.caseserviceprovider.controller;

import com.phsz.caseservice.caseserviceprovider.pojo.Case;
import com.phsz.caseservice.caseserviceprovider.pojo.CaseInfo;
import com.phsz.caseservice.caseserviceprovider.pojo.CollectedCase;
import com.phsz.caseservice.caseserviceprovider.pojo.RoughCaseInfoDto;
import com.phsz.caseservice.caseserviceprovider.service.Impl.CaseServiceImpl;
import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseServiceImpl caseService;

    @Autowired
    public CaseController(CaseServiceImpl caseService) {
        this.caseService = caseService;
    }

    //查找所有病例
    @GetMapping
    public Result getAllCase(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        ArrayList<Case> allCase = caseService.findAllCase(pageable);
        if (allCase == null) {
            return Result.error("not found");
        }
        return Result.success("find all cases successfully", allCase);
    }

    //添加病例
    @PostMapping
    public Result addNewCase(@RequestBody CaseInfo case1) {
        String s = caseService.addNewCase(case1);
        if (s == null) {
            return Result.error("add case failed");
        }
        return Result.success("add case successfully", null);
    }

    //删除病例
    @DeleteMapping("/{id}")
    public Result deleteCaseById(@PathVariable Long id) {
        String s = caseService.deleteCase(id);
        if (s == null) {
            return Result.error("not found or error deleting");
        }
        return Result.success("delete case successfully", null);
    }

    //修改病例
    @PutMapping
    public Result updateCaseById(@RequestBody CaseInfo case1) {
        String s = caseService.updateCase(case1);
        if (s == null) {
            return Result.error("not found or error updating");
        }
        return Result.success("update case successfully", null);
    }

    //查找病例
    @GetMapping("/{id}")
    public Result findCaseById(@PathVariable Long id) {
        CaseInfo aCase = caseService.findCase(id);
        if (aCase == null) {
            return Result.error("not found");
        }
        return Result.success("find case successfully", aCase);
    }

    //根据名称查找病例
    @GetMapping("/name")
    public Result findCaseByName(@RequestParam("caseName") String caseName, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("find case by name successfully", caseService.findAllByCaseNameLike(caseName, pageable));
    }

    //根据疾病名称查询病例
    @GetMapping("/disease")
    public Result findCaseByDiseaseId(@RequestParam("diseaseId") Long diseaseId, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("find case by diseaseId successfully", caseService.findRoughCaseListByDiseaseId(diseaseId, pageable));
    }

    @GetMapping("/collect/{id}")
    public Result collectCaseById(@PathVariable("id") Long caseId, @RequestHeader("UserId") String userId) {
        Long userID = Long.parseLong(userId);
        CollectedCase ccas = caseService.addNewCollectCase(caseId, userID);
        if (ccas.getId() > 0) {
            return Result.success();
        } else {
            return Result.error("收藏失败");
        }
    }

    @GetMapping("/collect/mine")
    public Result getMyCollectCases(@RequestHeader("UserId") String userId, @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Long userID = Long.parseLong(userId);
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        SimplePage<RoughCaseInfoDto> page = caseService.getMyCollectedCases(userID, pageable);
        if (page == null) {
            return Result.error("请求我的收藏病例失败");
        } else {
            return Result.success("请求我的收藏病例成功", page);
        }
    }


}
