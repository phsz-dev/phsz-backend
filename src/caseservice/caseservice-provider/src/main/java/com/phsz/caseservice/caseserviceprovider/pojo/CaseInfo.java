package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaseInfo {
	private Long caseId;
	private String caseName;
	private List<Illness> illness;
	private String caseDescription;
	private Long submitTime;
	private List<AssayInfo> assays;
	private List<MedicineInfo> medicines;
	private List<VaccineInfo> vaccines;
	private Long chargeId;
	private float chargeValue;
	private String doctorName;

}
