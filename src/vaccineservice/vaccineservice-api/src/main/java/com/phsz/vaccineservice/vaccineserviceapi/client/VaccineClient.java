package com.phsz.vaccineservice.vaccineserviceapi.client;

import com.phsz.vaccineservice.vaccineserviceapi.pojo.Result;
import com.phsz.vaccineservice.vaccineserviceapi.pojo.Vaccine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "vaccineservice-provider")
public interface VaccineClient {

    @GetMapping("/api/vaccines")
    Result getAllVaccines(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/api/vaccines/client/{vaccine_id}")
    Vaccine getVaccineById(@PathVariable("vaccine_id") Long vaccineId);

    @PostMapping("/api/vaccine")
    Result addVaccine(@RequestBody Vaccine vaccine);

    @PutMapping("/api/vaccine/{assay_id}")
    Result updateVaccine(@PathVariable("assay_id") Long assayId, @RequestBody Vaccine vaccine);

    @DeleteMapping("/api/vaccine/{assay_id}")
    Result deleteVaccine(@PathVariable("assay_id") Long assayId);
}