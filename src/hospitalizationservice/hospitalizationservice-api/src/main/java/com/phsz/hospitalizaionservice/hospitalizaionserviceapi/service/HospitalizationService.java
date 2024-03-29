package com.phsz.hospitalizaionservice.hospitalizaionserviceapi.service;


import com.phsz.hospitalizationservice.hospitalizationserviceprovider.entity.Hospitalization;

import java.util.List;
import java.util.Optional;

public interface HospitalizationService {
    List<Hospitalization> findAll();
    Optional<Hospitalization> findById(Long hospitalizationId);
    String save(Hospitalization hospitalization);
    String update(Hospitalization hospitalization);
    String deleteById(Long hospitalizationId);
}
