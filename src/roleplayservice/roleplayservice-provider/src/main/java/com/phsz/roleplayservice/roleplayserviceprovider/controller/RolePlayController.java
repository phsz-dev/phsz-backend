package com.phsz.roleplayservice.roleplayserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.roleplayservice.roleplayserviceapi.entity.Role;
import com.phsz.roleplayservice.roleplayserviceprovider.service.RolePlayServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RolePlayController {
    @Resource
    private final RolePlayServiceImpl rolePlayService;
    public RolePlayController(RolePlayServiceImpl rolePlayService) {
        this.rolePlayService = rolePlayService;
    }

    @GetMapping("/roles")
    public Result findAll() {
        return Result.success("success", rolePlayService.findAll());
    }

    @GetMapping("/roles/{roleId}")
    public Result findById(Long roleId) {
        return Result.success("success", rolePlayService.findById(roleId));
    }

    @PostMapping("/roles")
    public Result save(Role role) {
        return Result.success("success", rolePlayService.save(role));
    }

    @PutMapping("/roles")
    public Result update(Role role) {
        return Result.success("success", rolePlayService.update(role));
    }

    @DeleteMapping("/roles/{roleId}")
    public Result deleteById(Long roleId) {
        return Result.success("success", rolePlayService.deleteById(roleId));
    }
}