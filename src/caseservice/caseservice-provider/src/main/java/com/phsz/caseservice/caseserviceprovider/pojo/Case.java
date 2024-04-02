package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Case" ,schema = "public")
public class Case {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "submit_time")
	private String submitTime;
	@Column(name = "charge_id")
	private Long chargeId;
	@Column(name = "doctor_name")
	private String doctorName;

	public Case CaseCons(CaseInfo caseInfo) {
		this.name=caseInfo.getName();
		this.description=caseInfo.getDescription();
		this.submitTime=caseInfo.getSubmitTime();
		this.doctorName=caseInfo.getDoctorName();
		return this;
	}
}
