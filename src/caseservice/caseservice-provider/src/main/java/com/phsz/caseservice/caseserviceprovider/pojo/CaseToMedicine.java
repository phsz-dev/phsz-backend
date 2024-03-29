package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "case_to_medicine")
public class CaseToMedicine {
	@Id
	@Column(name = "case_id")

	private Long caseId;

	@Column(name = "medicine_id")
	private Long medicineId;

	@Column(name = "medicine_dosage")
	private String medicineDosage;
}
