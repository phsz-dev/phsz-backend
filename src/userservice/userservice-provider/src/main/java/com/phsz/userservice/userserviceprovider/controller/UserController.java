package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	@Resource
	private final UserServiceImpl userService;
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping
	public String getAll(){
		return "getAll";
	}

	@GetMapping("/me")
	public ResponseEntity<?> getCurrentUser() {
		return ResponseEntity.ok(userService.getCurrentUser());
	}

}
