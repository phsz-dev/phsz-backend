package com.phsz.caseservice.caseserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Illness" ,schema = "public")
public class Illness {
	@Id
	@Column(name = "illness_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long illnessId;
	@Column(name = "illness_name")
	private String illnessName;
	@Column(name = "illness_description")
	private String illnessDescription;
	@Column(name = "office_id")
	private Long officeId;
}
