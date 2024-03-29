package com.phsz.officeservice.officeserviceprovider.repository;

import com.phsz.officeservice.officeserviceapi.entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends CrudRepository<Office,Integer> {
    List<Office> findAll();
    Optional<Office> findById(Long officeId);
    Optional<Office> findByName(String officeName);
    Office save(Office office);
    void deleteById(Long officeId);
}
