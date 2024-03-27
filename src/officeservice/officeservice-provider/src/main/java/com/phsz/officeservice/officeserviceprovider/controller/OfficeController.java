package com.phsz.officeservice.officeserviceprovider.controller;

import com.phsz.officeservice.officeserviceprovider.entity.Office;
import com.phsz.officeservice.officeserviceprovider.service.OfficeServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offices")
public class OfficeController {
    @Resource
    private final OfficeServiceImpl officeService;
    public OfficeController(OfficeServiceImpl officeService) {
        this.officeService = officeService;
    }

    @GetMapping("/offices")
    public String findAll(){
        return officeService.findAll();
    }

    @GetMapping("/offices/{id}")
    public String findOne(Long id) {
        return officeService.findOne(id);
    }

    @GetMapping("/offices/{name}")
    public String findByName(String name) {
        return officeService.findByName(name);
    }

    @PostMapping("/offices")
    public String add(Office office) {
        return officeService.add(office);
    }

    @PutMapping("/offices")
    public String update(Office office) {
        return officeService.update(office);
    }

    @DeleteMapping("/offices/{id}")
    public String delete(Long id) {
        return officeService.delete(id);
    }
}
