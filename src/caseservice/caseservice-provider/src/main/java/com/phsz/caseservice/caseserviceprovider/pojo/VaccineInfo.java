package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class VaccineInfo {
	private Long id;
	private String name;
	private String manufacturer;
	private Date expiryDate;
}
