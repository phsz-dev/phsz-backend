package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "examination",schema = "public")
public class Examination {
	@Id
	@Column(name = "examination_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long examinationId;
	@Column(name = "examination_name")
	private String examinationName;
	@Column(name = "examination_Status")
	private String examinationStatus;
	@Column(name = "paper_id")
	private Long paperId;
	@Column(name = "start_time")
	private String startTime;
	@Column(name = "examination_user")
	private String examinationUser;
}
