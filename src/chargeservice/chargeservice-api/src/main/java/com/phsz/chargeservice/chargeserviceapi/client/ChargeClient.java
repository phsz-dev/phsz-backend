package com.phsz.chargeservice.chargeserviceapi.client;

import com.phsz.chargeservice.chargeserviceapi.pojo.Result;
import com.phsz.chargeservice.chargeserviceapi.pojo.Charge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "chargeservice-provider")
public interface ChargeClient {

    @GetMapping("/api/charges")
    Result getAllCharges(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    @GetMapping("/api/charge/{charge_id}")
    Result getChargeById(@PathVariable("charge_id") Long chargeId);

    @PostMapping("/api/charge")
    Result addCharge(@RequestBody Charge charge);

    @PutMapping("/api/charge/{charge_id}")
    Result updateCharge(@PathVariable("charge_id") Long chargeId, @RequestBody Charge charge);

    @DeleteMapping("/api/charge/{charge_id}")
    Result deleteCharge(@PathVariable("charge_id") Long chargeId);
}
