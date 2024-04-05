package com.phsz.caseservice.caseserviceprovider.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class AssayInfo {
	private Long id;
	private String name;
	private Date date;
	private String result;
}
