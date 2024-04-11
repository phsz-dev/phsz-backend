package com.phsz.chargeservice.chargeserviceprovider.controller;

import com.phsz.chargeservice.chargeserviceprovider.pojo.Charge;
import com.phsz.chargeservice.chargeserviceprovider.service.Impl.ChargeServiceImpl;
import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/charges")
public class ChargeController {

    @Autowired
    private ChargeServiceImpl chargeService;


    // 获取所有收费信息
    @GetMapping
    public Result getAllCharges(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return Result.success("get all charge OK", new SimplePage<>(chargeService.findAllCharges(pageable)));
    }

    // 添加新收费记录
    @PostMapping
    public Result addNewCharge(@RequestBody Charge charge) {
        Charge newCharge = chargeService.addNewCharge(charge);
        if (newCharge == null) {
            return Result.error("add charge failed");
        }
        return Result.success("add charge successfully", null);
    }

    // 删除收费记录
    @DeleteMapping("/{charge_id}")
    public Result deleteChargeById(@PathVariable Long charge_id) {
        String message = chargeService.deleteCharge(charge_id);
        if (message == null) {
            return Result.error("not found or error deleting");
        }
        return Result.success("delete charge successfully", null);
    }

    // 修改收费信息
    @PutMapping("/{charge_id}")
    public Result updateChargeById(@PathVariable Long charge_id, @RequestBody Charge charge) {
        Charge updatedCharge = chargeService.updateCharge(charge_id, charge);
        if (updatedCharge == null) {
            return Result.error("not found or error updating");
        }
        return Result.success("update charge successfully", null);
    }

    // 获取单个收费记录信息
    @GetMapping("/{charge_id}")
    public Result findChargeById(@PathVariable Long charge_id) {
        Charge charge = chargeService.findChargeById(charge_id);
        if (charge == null) {
            return Result.error("not found");
        }
        return Result.success("find charge successfully", charge);
    }

    // 批量获取收费记录信息
    @PostMapping("/batch")
    public Result findChargeByIds(@RequestBody List<Long> chargeIds) {
        List<Charge> charges = chargeService.findChargeByIds(chargeIds);
        if (charges == null) {
            return Result.error("not found");
        }
        return Result.success("find charge successfully", charges);
    }
}
