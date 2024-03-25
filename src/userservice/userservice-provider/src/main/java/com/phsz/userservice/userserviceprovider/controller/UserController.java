package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.userservice.userserviceprovider.pojo.User;
import com.phsz.userservice.userserviceprovider.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	@Resource
	private final UserServiceImpl userService;
	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@PostMapping
	public String login(@RequestBody User user){
		return userService.login(user);
	}
	@GetMapping
	public String getAll(){
		return userService.findAll();
	}

}
