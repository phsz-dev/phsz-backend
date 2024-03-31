package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "case_to_assay",schema = "public")
public class CaseToAssay {
	@Id
	@Column
	private Long CTAId;
	@Column
	private Long caseId;
	@Column
	private Long assayId;
}
