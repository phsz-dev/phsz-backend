package com.phsz.caseservice.caseserviceprovider.service;

import com.phsz.caseservice.caseserviceprovider.pojo.DiseaseResponse;

import java.util.List;

public interface DiseaseService {
    List<DiseaseResponse> getAllDisease();
}
