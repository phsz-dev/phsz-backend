package com.phsz.caseservice.caseserviceprovider.service.Impl;

import com.phsz.caseservice.caseserviceprovider.pojo.Disease;
import com.phsz.caseservice.caseserviceprovider.pojo.DiseaseResponse;
import com.phsz.caseservice.caseserviceprovider.pojo.DiseaseType;
import com.phsz.caseservice.caseserviceprovider.repository.DiseaseRepository;
import com.phsz.caseservice.caseserviceprovider.repository.DiseaseTypeRepository;
import com.phsz.caseservice.caseserviceprovider.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    private final DiseaseRepository diseaseRepository;

    private final DiseaseTypeRepository diseaseTypeRepository;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository, DiseaseTypeRepository diseaseTypeRepository) {
        this.diseaseRepository = diseaseRepository;
        this.diseaseTypeRepository = diseaseTypeRepository;
    }


    @Override
    public List<DiseaseResponse> getAllDisease() {
        List<DiseaseResponse> diseaseResponses = null;
        List<DiseaseType> diseaseTypes = diseaseTypeRepository.findAll();
        for (DiseaseType diseaseType : diseaseTypes) {
            List<Disease> diseases = diseaseRepository.findByTypeId(diseaseType.getId());
            DiseaseResponse diseaseResponse = new DiseaseResponse();
            diseaseResponse.setDiseaseType(diseaseType);
            diseaseResponse.setDiseaseList(diseases);
            diseaseResponses.add(diseaseResponse);
        }
        return diseaseResponses;
    }
}
