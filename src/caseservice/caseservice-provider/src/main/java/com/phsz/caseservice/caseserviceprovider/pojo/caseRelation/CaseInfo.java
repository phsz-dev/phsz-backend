package com.phsz.caseservice.caseserviceprovider.pojo.caseRelation;

import com.phsz.caseservice.caseserviceprovider.pojo.AssayInfo;
import com.phsz.caseservice.caseserviceprovider.pojo.Disease;
import com.phsz.caseservice.caseserviceprovider.pojo.MedicineInfo;
import com.phsz.caseservice.caseserviceprovider.pojo.VaccineInfo;
import lombok.Data;

import java.util.List;

@Data
public class CaseInfo {
    private Long id;
    private String name;
    private List<Disease> diseaseList;
    private String description;
    private Long submitTime;
    private List<AssayInfo> assays;
    private List<MedicineInfo> medicines;
    private List<VaccineInfo> vaccines;
    private Long chargeId;
    private String brief;
    private String doctorName;

}
