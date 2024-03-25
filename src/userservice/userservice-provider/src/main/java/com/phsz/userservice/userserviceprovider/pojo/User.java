package com.phsz.userservice.userserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user" ,schema = "public")
public class User {
	@Id
	@Column(name = "user_id")
	public Integer user_id;
	@Column(name = "password")
	public String password;
}
