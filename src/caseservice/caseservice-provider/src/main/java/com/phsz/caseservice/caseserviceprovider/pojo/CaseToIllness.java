package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "case_to_illness" ,schema = "public")
public class CaseToIllness {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CTIId;

	@Column(name = "case_id")
	private Long caseId;
	@Column(name = "illness_id")
	private Long illnessId;
}
