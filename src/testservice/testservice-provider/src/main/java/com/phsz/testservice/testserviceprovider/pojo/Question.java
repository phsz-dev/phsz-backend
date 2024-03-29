package com.phsz.testservice.testserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question",schema = "public")
public class Question {
	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long questionId;
	@Column(name = "type")
	private String type;
	@Column(name = "content")
	private String content;
	@Column(name = "answer")
	private String answer;
	@Column(name = "score")
	private float score;
}
