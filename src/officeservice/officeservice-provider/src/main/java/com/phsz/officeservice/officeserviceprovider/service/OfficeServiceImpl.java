package com.phsz.officeservice.officeserviceprovider.service;

import com.phsz.officeservice.officeserviceapi.service.OfficeService;
import com.phsz.officeservice.officeserviceprovider.repository.OfficeRepository;
import jakarta.annotation.Resource;
import com.phsz.officeservice.officeserviceprovider.entity.Office;

public class OfficeServiceImpl implements OfficeService {
    @Resource
    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public String findAll() {
        return officeRepository.findAll().toString();
    }

    @Override
    public String findOne(Long id) {
        return officeRepository.findOne(id).toString();
    }

    @Override
    public String findByName(String name) {
        return officeRepository.findByName(name).toString();
    }

    @Override
    public String add(Office office) {
        officeRepository.add(office);
        return "success";
    }

    @Override
    public String update(Office office) {
        officeRepository.update(office);
        return "success";
    }

    @Override
    public String delete(Long id) {
        officeRepository.delete(id);
        return "success";
    }
}
