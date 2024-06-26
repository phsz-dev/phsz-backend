package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Vaccine;
import com.phsz.caseservice.caseserviceprovider.repository.VaccineRepository;
import com.phsz.caseservice.caseserviceprovider.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VaccineServiceImpl implements VaccineService {

    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    @Override
    public Page<Vaccine> findAllVaccines(Pageable pageable) {
        return vaccineRepository.findAll(pageable);
    }

    @Override
    public Vaccine findVaccineById(Long id) {
        Optional<Vaccine> byId = vaccineRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public Vaccine addNewVaccine(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine updateVaccine(Long id, Vaccine vaccineDetails) {
        Vaccine vaccine = vaccineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vaccine not found for this id :: " + id));
        vaccine.setName(vaccineDetails.getName());
        vaccine.setManufacturer(vaccineDetails.getManufacturer());
        // 更多属性更新...
        return vaccineRepository.save(vaccine);
    }

    @Override
    public String deleteVaccine(Long id) {
        Optional<Vaccine> byId = vaccineRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        Optional<Vaccine> vaccine = vaccineRepository.deleteVaccineById(id);
        return vaccine.get().getId().toString();
    }
}