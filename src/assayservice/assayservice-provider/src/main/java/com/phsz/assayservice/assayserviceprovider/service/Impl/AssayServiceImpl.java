package com.phsz.assayservice.assayserviceprovider.service.Impl;

import com.phsz.assayservice.assayserviceprovider.pojo.Assay;
import com.phsz.assayservice.assayserviceprovider.repository.AssayRepository;
import com.phsz.assayservice.assayserviceprovider.service.AssayService;
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
    public Optional<Assay> findAssayById(Long id) {
        return assayRepository.findById(id);
    }

    @Override
    public Assay addNewAssay(Assay assay) {
        return assayRepository.save(assay);
    }

    @Override
    public Assay updateAssay(Long id, Assay assayDetails) {
        Optional<Assay> assay = assayRepository.findById(id);
        if(assay.isEmpty()){
            return null;
        }
        return assayRepository.save(assayDetails);
    }

    @Override
    public String  deleteAssay(Long id) {
        Optional<Assay> byId = assayRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        Optional<Assay> assay = assayRepository.deleteAssayById(id);
        return assay.get().getId().toString();

    }
}
