package com.phsz.assayservice.assayserviceapi.service;

import com.phsz.assayservice.assayserviceapi.pojo.Assay;

import java.util.List;
import java.util.Optional;

public interface AssayService {
    List<Assay> findAll();

    Optional<Assay> findById(Long assayId);

    Optional<Assay> findByName(String assayName);

    String save(Assay assay);

    String update(Assay assay);

    String deleteById(Long assayId);
}