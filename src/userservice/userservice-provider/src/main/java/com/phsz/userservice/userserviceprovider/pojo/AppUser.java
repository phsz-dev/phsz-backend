package com.phsz.userservice.userserviceprovider.pojo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user",schema = "public")
public class AppUser {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String password;
	private String email;
	private boolean enabled; // 用于指示用户是否被激活

	// 其他属性和方法
}