package com.phsz.playservice.playserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "responsibility",schema = "public")
public class Responsibility {
	@Id
	@Column(name = "responsibility_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long responsibilityId;
	@Column(name = "responsibility_name")
	private String responsibilityName;
	@Column(name = "role")
	private String role;
	@Column(name = "description")
	private String description;
}
