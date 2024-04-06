package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.entity.UserInfo;
import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public Result getAll(){
		return Result.success("Get all users successful", userService.getAll());
	}

	@GetMapping("/me")
	public Result getCurrentUser(@RequestHeader("Username") String username, @RequestHeader("Roles") String[] roles) {
		AppUser appUser = userService.getUserByName(username);
		UserInfo userInfo = new UserInfo(appUser.getId(), appUser.getUsername(), appUser.getEmail(), appUser.isEnabled(), roles);
		return Result.success("Get current user successful", userInfo);
	}

}
