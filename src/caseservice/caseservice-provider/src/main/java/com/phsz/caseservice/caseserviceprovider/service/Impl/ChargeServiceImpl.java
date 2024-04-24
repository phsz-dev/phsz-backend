package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Charge;
import com.phsz.caseservice.caseserviceprovider.repository.ChargeRepository;
import com.phsz.caseservice.caseserviceprovider.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChargeServiceImpl implements ChargeService {

    private final ChargeRepository chargeRepository;

    @Autowired
    public ChargeServiceImpl(ChargeRepository chargeRepository) {
        this.chargeRepository = chargeRepository;
    }

    @Override
    public Page<Charge> findAllCharges(Pageable pageable) {
        return chargeRepository.findAll(pageable);
    }

    @Override
    public Charge findChargeById(Long id) {
        Optional<Charge> byId = chargeRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        return byId.get();
    }

    @Override
    public Charge addNewCharge(Charge charge) {
        return chargeRepository.save(charge);
    }

    @Override
    public Charge updateCharge(Long id, Charge chargeDetails) {
        Charge charge = chargeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Charge not found for this id :: " + id));

        // 更多属性更新...
        return chargeRepository.save(charge);
    }

    @Override
    public String deleteCharge(Long id) {
        Optional<Charge> byId = chargeRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        chargeRepository.deleteById(id); // 假设有对应的方法deleteById
        return id.toString(); // 假设ID是要返回的正确类型
    }

    @Override
    public List<Charge> findChargeByIds(List<Long> ids) {
        return chargeRepository.findChargeByIdIn(ids);
    }
}