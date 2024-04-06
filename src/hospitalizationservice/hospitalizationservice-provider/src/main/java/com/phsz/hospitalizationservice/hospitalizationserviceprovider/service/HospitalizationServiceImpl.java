package com.phsz.hospitalizationservice.hospitalizationserviceprovider.service;

import com.phsz.hospitalizationservice.hospitalizationserviceapi.entity.Hospitalization;
import com.phsz.hospitalizationservice.hospitalizationserviceapi.service.HospitalizationService;
import com.phsz.hospitalizationservice.hospitalizationserviceprovider.repository.HospitalizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalizationServiceImpl implements HospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;

    public HospitalizationServiceImpl(HospitalizationRepository hospitalizationRepository) {
        this.hospitalizationRepository = hospitalizationRepository;
    }

    @Override
    public List<Hospitalization> findAll() {
        return hospitalizationRepository.findAll();
    }

    @Override
    public Optional<Hospitalization> findById(Long hospitalizationId) {
        return hospitalizationRepository.findById(hospitalizationId);
    }

    @Override
    public String save(Hospitalization hospitalization) {
        hospitalizationRepository.save(hospitalization);
        return "success";
    }

    @Override
    public String update(Hospitalization hospitalization) {
        hospitalizationRepository.save(hospitalization);
        return "success";
    }

    @Override
    public String deleteById(Long hospitalizationId) {
        hospitalizationRepository.deleteById(hospitalizationId);
        return "success";
    }
}