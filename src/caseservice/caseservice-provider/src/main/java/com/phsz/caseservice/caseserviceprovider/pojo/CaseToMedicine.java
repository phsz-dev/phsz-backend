package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CaseToMedicine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "case_id")

	private Long caseId;

	@Column(name = "medicine_id")
	private Long medicineId;

	@Column(name = "medicine_dosage")
	private String medicineDosage;
}
