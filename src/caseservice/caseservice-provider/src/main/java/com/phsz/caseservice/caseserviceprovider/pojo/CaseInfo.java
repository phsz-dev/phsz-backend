package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaseInfo {
	private Long id;
	private String name;
	private List<Disease> diseaseList;
	private String description;
	private String submitTime;
	private List<AssayInfo> assays;
	private List<MedicineInfo> medicines;
	private List<VaccineInfo> vaccines;
	private Long chargeId;
	private float chargeValue;
	private String doctorName;

}
