package com.phsz.hospitalizationservice.hospitalizationserviceprovider.repository;

import com.phsz.hospitalizationservice.hospitalizationserviceapi.entity.Hospitalization;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalizationRepository extends CrudRepository<Hospitalization, Integer> {
    List<Hospitalization> findAll();

    Optional<Hospitalization> findById(Long hospitalizationId);

    Hospitalization save(Hospitalization hospitalization);

    void deleteById(Long hospitalizationId);
}
