package com.phsz.officeservice.officeserviceapi.service;

import com.phsz.officeservice.officeserviceprovider.entity.Office;

public interface OfficeService {
    String findAll();
    String findOne(Long id);
    String findByName(String name);
    String add(Office office);
    String update(Office office);
    String delete(Long id);
}
