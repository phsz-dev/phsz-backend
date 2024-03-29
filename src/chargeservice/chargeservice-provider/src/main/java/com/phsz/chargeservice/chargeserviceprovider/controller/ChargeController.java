package com.phsz.chargeservice.chargeserviceprovider.controller;

import jakarta.annotation.Resource;

import com.phsz.chargeservice.chargeserviceprovider.pojo.Charge;
import com.phsz.chargeservice.chargeserviceprovider.pojo.Result;
import com.phsz.chargeservice.chargeserviceprovider.service.Impl.ChargeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/charges")
public class ChargeController {

    @Autowired
    private ChargeServiceImpl chargeService;

    @Autowired
    Result result;

    // 获取所有收费信息
    @GetMapping
    public Result getAllCharges(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        result.setData(chargeService.findAllCharges(pageable));
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 添加新收费记录
    @PostMapping
    public Result addNewCharge(@RequestBody Charge charge) {
        Charge newCharge = chargeService.addNewCharge(charge);
        if (newCharge == null) {
            result.setCode(0);
            result.setMessage("already exists");
            return result;
        }
        result.setData(newCharge);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 删除收费记录
    @DeleteMapping("/{charge_id}")
    public Result deleteChargeById(@PathVariable Long charge_id) {
        String message = chargeService.deleteCharge(charge_id);
        if (message == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(message);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 修改收费信息
    @PutMapping("/{charge_id}")
    public Result updateChargeById(@PathVariable Long charge_id, @RequestBody Charge charge) {
        Charge updatedCharge = chargeService.updateCharge(charge_id, charge);
        if (updatedCharge == null) {
            result.setCode(0);
            result.setMessage("not found or error updating");
            return result;
        }
        result.setData(updatedCharge);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }

    // 获取单个收费记录信息
    @GetMapping("/{charge_id}")
    public Result findChargeById(@PathVariable Long charge_id) {
        Charge charge = chargeService.findChargeById(charge_id);
        if (charge == null) {
            result.setCode(0);
            result.setMessage("not found");
            return result;
        }
        result.setData(charge);
        result.setCode(1);
        result.setMessage("successfully");
        return result;
    }
}
