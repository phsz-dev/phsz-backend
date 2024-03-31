package com.phsz.clerkservice.clerkserviceprovider.controller;

import com.phsz.clerkservice.clerkserviceapi.entity.Clerk;
import com.phsz.clerkservice.clerkserviceprovider.service.ClerkServiceImpl;
import com.phsz.common.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clerks")
public class ClerkController {
    @Resource
    private final ClerkServiceImpl clerkService;

    public ClerkController(ClerkServiceImpl clerkService) {
        this.clerkService = clerkService;
    }

    @GetMapping("/clerks")
    public Result findAll() {
        return Result.success("success", clerkService.findAll());
    }

    @GetMapping("/clerks/{clerkId}")
    public Result findById(Long clerkId) {
        return Result.success("success", clerkService.findById(clerkId));
    }

    @GetMapping("/clerks/{clerkName}")
    public Result findByName(String clerkName) {
        return Result.success("success", clerkService.findByName(clerkName));
    }

    @PostMapping("/clerks")
    public Result save(Clerk clerk) {
        return Result.success("success", clerkService.save(clerk));
    }

    @PutMapping("/clerks")
    public Result update(Clerk clerk) {
        return Result.success("success", clerkService.update(clerk));
    }

    @DeleteMapping("/clerks/{clerkId}")
    public Result deleteById(Long clerkId) {
        return Result.success("success", clerkService.deleteById(clerkId));
    }
}
