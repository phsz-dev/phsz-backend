package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.List;

@Data
public class CaseInfo {
	private Long caseId;
	private String caseName;
	private Illness illness;
	private String caseDescription;
	private String submitTime;
	private List<Assay> assays;
	private List<MedicineInfo> medicines;
	private List<Vaccine> vaccines;
	private float chargeValue;
	private Long doctorId;
	private String doctorName;
}
