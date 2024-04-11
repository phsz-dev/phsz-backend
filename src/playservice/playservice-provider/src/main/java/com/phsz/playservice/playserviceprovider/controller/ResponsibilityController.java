package com.phsz.playservice.playserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.playservice.playserviceprovider.pojo.LearnedSubResponsibility;
import com.phsz.playservice.playserviceprovider.pojo.Responsibility;
import com.phsz.playservice.playserviceprovider.pojo.Role;
import com.phsz.playservice.playserviceprovider.service.LearnedSubResponsibilityServiceImpl;
import com.phsz.playservice.playserviceprovider.service.ResponsibilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plays/responsibilities")
public class ResponsibilityController {
    ResponsibilityServiceImpl responsibilityService;

    LearnedSubResponsibilityServiceImpl learnedSubResponsibilityService;

    @Autowired
    public ResponsibilityController(ResponsibilityServiceImpl responsibilityService, LearnedSubResponsibilityServiceImpl learnedSubResponsibilityService){
        this.responsibilityService = responsibilityService;
        this.learnedSubResponsibilityService = learnedSubResponsibilityService;
    }

    @GetMapping("/{responsibilityId}")
    public Result getResponsibilityById(@PathVariable Long responsibilityId) {
        Responsibility responsibilityById = responsibilityService.getResponsibilityById(responsibilityId);
        if (responsibilityById == null) {
            return Result.error("not found");
        } else {
            return Result.success("getResponsibilityById successfully", responsibilityById);
        }
    }

    @GetMapping("/full/{role}")
    public Result getFullResponsibilityById(@PathVariable int role) {
        Role inRole = Role.values()[role];
        List<Responsibility> fullResponsibilityById = responsibilityService.getFullResponsibilityByRole(inRole);
        if (fullResponsibilityById == null) {
            return Result.error("not found");
        } else {
            return Result.success("getResponsibilityById successfully", fullResponsibilityById);
        }
    }

    @GetMapping("/role")
    public Result getResponsibilityByRole(@RequestParam Role role, @RequestParam int pageNum, @RequestParam int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Responsibility> responsibilityByRole = responsibilityService.getResponsibilityByRole(role, pageable);
        if (responsibilityByRole == null) {
            return Result.error("not found");
        } else {
            return Result.success("getResponsibilityByRole successfully", new SimplePage<>(responsibilityByRole));
        }
    }

    @PostMapping
    public Result addResponsibility(@RequestBody Responsibility responsibility) {
        String s = responsibilityService.addResponsibility(responsibility);
        if (s == null) {
            return Result.error("already exists");
        } else {
            return Result.success("addResponsibility successfully", null);
        }
    }

    @DeleteMapping("/{responsibilityId}")
    public Result deleteResponsibility(@PathVariable Long responsibilityId) {
        String s = responsibilityService.deleteResponsibility(responsibilityId);
        if (s == null) {
            return Result.error("not found");
        } else {
            return Result.success("deleteResponsibility successfully", null);
        }
    }

    @PutMapping
    public Result updateResponsibility(@RequestBody Responsibility responsibility) {
        String s = responsibilityService.updateResponsibility(responsibility);
        if (s == null) {
            return Result.error("not found");
        } else {
            return Result.success("updateResponsibility successfully", null);
        }
    }

    @GetMapping("/learned/{subResponsibility_id}")
    public Result learnedResponsibility(@PathVariable("subResponsibility_id") String subId,@RequestHeader("UserId") String userId){
        LearnedSubResponsibility lb = learnedSubResponsibilityService.addLearned(Long.parseLong(subId),Long.parseLong(userId));
        if (lb == null) {
            return Result.error("not found");
        } else {
            return Result.success("learnedResponsibility successfully");
        }
    }

    @GetMapping("/getLearned/{role}")
    public Result getLearnedResponsibility(@RequestHeader("UserId") String userId,@PathVariable("role") int role){
        Role inRole = Role.values()[role];
        List<Long> lb = learnedSubResponsibilityService.getLearned(Long.parseLong(userId),inRole);
        List<Long> rb = responsibilityService.getLearned(Long.parseLong(userId),inRole);
        Map<String, List<Long>> map = Map.of("learned",lb,"responsibility",rb);
        return Result.success("getLearnedResponsibility successfully", map);
    }



}
