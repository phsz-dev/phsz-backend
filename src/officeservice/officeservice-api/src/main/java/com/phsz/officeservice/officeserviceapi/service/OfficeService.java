package com.phsz.officeservice.officeserviceapi.service;

import com.phsz.officeservice.officeserviceapi.entity.Office;

import java.util.List;
import java.util.Optional;

public interface OfficeService {
    List<Office> findAll();

    Optional<Office> findById(Long officeId);

    Optional<Office> findByName(String officeName);

    String save(Office office);

    String update(Office office);

    String deleteById(Long officeId);
}
