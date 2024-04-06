package com.phsz.chargeservice.chargeserviceapi.service;

import com.phsz.chargeservice.chargeserviceapi.pojo.Charge;

import java.util.List;
import java.util.Optional;

public interface ChargeService {
    List<Charge> findAll();

    Optional<Charge> findById(Long chargeId);

    Optional<Charge> findByName(String chargeName);

    String save(Charge charge);

    String update(Charge charge);

    String deleteById(Long chargeId);
}
