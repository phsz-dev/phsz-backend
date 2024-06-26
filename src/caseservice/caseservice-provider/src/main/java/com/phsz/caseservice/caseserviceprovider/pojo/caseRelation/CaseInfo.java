package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import com.phsz.caseservice.caseserviceprovider.pojo.*;
import lombok.Data;

import java.util.List;

@Data
public class CaseInfo {
    private Long id;
    private String name;
    private Disease diseaseList;
    private String description;
    private Long submitTime;
    private List<AssayInfo> assays;
    private List<MedicineInfo> medicines;
    private List<VaccineInfo> vaccines;
    private Charge charge;
    private String brief;
    private String doctorName;

}
