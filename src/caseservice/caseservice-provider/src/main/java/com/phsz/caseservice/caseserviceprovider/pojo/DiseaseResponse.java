package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseResponse {
    private DiseaseType diseaseType;
    private List<Disease> diseaseList;
}
