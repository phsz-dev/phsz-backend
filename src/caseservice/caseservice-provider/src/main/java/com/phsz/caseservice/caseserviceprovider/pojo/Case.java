package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Case" ,schema = "public")
public class Case {
	@Id
	@Column(name = "case_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseId;
	@Column(name = "case_name")
	private String caseName;
	@Column(name = "illness_id")
	private Long illnessId;
	@Column(name = "case_description")
	private String caseDescription;
	@Column(name = "submit_time")
	private String submitTime;
	@Column(name = "assay_id")
	private Long assayId;
	@Column(name = "medicines_id")
	private String medicinesId;
	@Column(name = "vaccine_id")
	private Long vaccineId;
	@Column(name = "charge_id")
	private Long chargeId;
	@Column(name = "doctor_id")
	private Long doctorId;

}
