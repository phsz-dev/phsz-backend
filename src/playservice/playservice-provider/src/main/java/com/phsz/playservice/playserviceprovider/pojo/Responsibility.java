package com.phsz.playservice.playserviceprovider.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Responsibility {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private Role role;
	@Column(columnDefinition = "jsonb")
	private String content;
}
