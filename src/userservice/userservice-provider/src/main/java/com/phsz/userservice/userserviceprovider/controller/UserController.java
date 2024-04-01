package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@GetMapping
	public String getAll(){
		return "getAll";
	}

	@GetMapping("/me")
	public Result getCurrentUser(@RequestHeader("Username") String username, @RequestHeader("Roles") List<String> roles){
		return Result.success("OK", String.join(",", roles));
	}

}
