package com.phsz.officeservice.officeserviceprovider.repository;

import com.phsz.officeservice.officeserviceprovider.entity.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends CrudRepository<Office,Integer> {
    List<Office> findAll();
    Optional<Office> findOne(Long id);
    void add(Office office);
    void update(Office office);
    void delete(Long id);
    Optional<Office> findByName(String name);
}
