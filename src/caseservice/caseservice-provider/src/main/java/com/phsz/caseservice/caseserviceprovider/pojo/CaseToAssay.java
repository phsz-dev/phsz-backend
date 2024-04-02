package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "case_to_assay",schema = "public")
public class CaseToAssay {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CTAId;
	@Column(name = "case_id")
	private Long caseId;
	@Column(name = "assay_id")
	private Long assayId;
}
