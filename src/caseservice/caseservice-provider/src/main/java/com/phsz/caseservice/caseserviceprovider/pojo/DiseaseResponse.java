package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseResponse {
    private DiseaseType diseaseType;
    private List<Disease> diseaseList;
}
