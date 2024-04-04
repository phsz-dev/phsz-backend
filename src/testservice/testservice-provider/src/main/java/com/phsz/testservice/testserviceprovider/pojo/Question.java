package com.phsz.testservice.testserviceprovider.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String type;
	@Column
	private String content;
	@Column
	private String answer;
	@Column
	private float score;
	@ManyToMany(mappedBy = "questions")
	@JsonIgnore
	private List<Paper> papers;
}
