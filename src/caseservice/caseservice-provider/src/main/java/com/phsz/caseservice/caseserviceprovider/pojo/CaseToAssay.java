package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CaseToAssay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "case_id")
	private Long caseId;
	@Column(name = "assay_id")
	private Long assayId;
}
