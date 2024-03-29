package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paper",schema = "public")
public class Paper {
	@Id
	@Column(name = "paper_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paperId;
	@Column(name = "paper_name")
	private String paperName;
	@Column(name = "content")
	private String content;
	@Column(name = "total_score")
	private float totalScore;
	@Column(name = "time_limit")
	private String timeLimit;
}
