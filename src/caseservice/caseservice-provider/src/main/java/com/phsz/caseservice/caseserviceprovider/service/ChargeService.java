package com.phsz.caseservice.caseserviceprovider.service;

import com.phsz.caseservice.caseserviceprovider.pojo.Charge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChargeService {
    // 支持分页查询的方法
    Page<Charge> findAllCharges(Pageable pageable);

    // 根据ID查找收费记录
    Charge findChargeById(Long id);

    // 添加新的收费记录
    Charge addNewCharge(Charge charge);

    // 更新收费记录
    Charge updateCharge(Long id, Charge charge);

    // 根据ID删除收费记录
    String deleteCharge(Long id);

    List<Charge> findChargeByIds(List<Long> ids);
}
