package com.phsz.vaccineservice.vaccineserviceapi.client;

import com.phsz.vaccineservice.vaccineserviceapi.pojo.Result;
import com.phsz.vaccineservice.vaccineserviceapi.pojo.Vaccine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "vaccineservice-provider")
public interface VaccineClient {

    @GetMapping("/vaccines")
    Result getAllVaccines(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/vaccine/{vaccine_id}")
    Result getVaccineById(@PathVariable("vaccine_id") String vaccineId);

    @PostMapping("/vaccine")
    Result addVaccine(@RequestBody Vaccine vaccine);

    @PutMapping("/vaccine/{assay_id}")
    Result updateVaccine(@PathVariable("assay_id") String assayId, @RequestBody Vaccine vaccine);

    @DeleteMapping("/vaccine/{assay_id}")
    Result deleteVaccine(@PathVariable("assay_id") String assayId);
}