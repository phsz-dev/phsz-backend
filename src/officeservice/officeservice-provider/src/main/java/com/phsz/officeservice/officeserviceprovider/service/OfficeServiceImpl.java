package com.phsz.officeservice.officeserviceprovider.service;

import com.phsz.officeservice.officeserviceapi.service.OfficeService;
import com.phsz.officeservice.officeserviceprovider.repository.OfficeRepository;
import jakarta.annotation.Resource;
import com.phsz.officeservice.officeserviceprovider.entity.Office;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeServiceImpl implements OfficeService {
    @Resource
    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    @Override
    public List<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public Optional<Office> findById(Long officeId) {
        return officeRepository.findById(officeId);
    }

    @Override
    public Optional<Office> findByName(String officeName) {
        return officeRepository.findByName(officeName);
    }

    @Override
    public String save(Office office) {
        officeRepository.save(office);
        return "success";
    }

    @Override
    public String update(Office office) {
        officeRepository.update(office);
        return "success";
    }

    @Override
    public String deleteById(Long officeId) {
        officeRepository.deleteById(officeId);
        return "success";
    }
}
