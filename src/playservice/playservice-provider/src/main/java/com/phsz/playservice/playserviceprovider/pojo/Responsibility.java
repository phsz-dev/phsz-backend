package com.phsz.playservice.playserviceprovider.pojo;

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
	private String role;
	@Column
	private String description;
}
