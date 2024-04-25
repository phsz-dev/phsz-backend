package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.alibaba.nacos.shaded.com.google.gson.JsonObject;
import com.phsz.caseservice.caseserviceprovider.pojo.Assay;
import com.phsz.caseservice.caseserviceprovider.repository.AssayRepository;
import com.phsz.caseservice.caseserviceprovider.service.AssayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssayServiceImpl implements AssayService {

    private final AssayRepository assayRepository;

    @Autowired
    public AssayServiceImpl(AssayRepository assayRepository) {
        this.assayRepository = assayRepository;
    }

    @Override
    public Page<Assay> findAllAssays(Pageable pageable) {
        return assayRepository.findAll(pageable);
    }

    @Override
    public Assay findAssayById(Long id) {
        return assayRepository.findById(id).orElse(null);
    }

    @Override
    public Assay addNewAssay(Assay assay) {
        return assayRepository.save(assay);
    }

    @Override
    public Assay updateAssay(Long id, Assay assayDetails) {
        Optional<Assay> assay = assayRepository.findById(id);
        if (assay.isEmpty()) {
            return null;
        }
        return assayRepository.save(assayDetails);
    }

    @Override
    public String deleteAssay(Long id) {
        Optional<Assay> byId = assayRepository.findById(id);
        if (byId.isEmpty()) {
            return null;
        }
        Optional<Assay> assay = assayRepository.deleteAssayById(id);
        return assay.get().getId().toString();

    }

    @Override
    public Page<Assay> searchAssay(String key, String head, Pageable pageable) {
        String[] searchHeader = head.split(",");
        if(searchHeader.length == 0) {
            return assayRepository.searchAllAssay(key, pageable);
        }
        return assayRepository.searchAssay(key, searchHeader, pageable);
    }
}
