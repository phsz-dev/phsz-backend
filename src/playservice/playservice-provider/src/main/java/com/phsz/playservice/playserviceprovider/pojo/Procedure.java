package com.phsz.playservice.playserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "procedure",schema = "public")
public class Procedure {
	@Id
	@Column(name = "procedure_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long procedureId;
	@Column(name = "procedure_name")
	private String procedureName;
	@Column(name = "responsibility_id")
	private Long responsibilityId;
	@Column(name = "content")
	private String content;
}
