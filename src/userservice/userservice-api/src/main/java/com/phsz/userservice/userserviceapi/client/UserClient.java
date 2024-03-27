package com.phsz.userservice.userserviceapi.client;

import com.phsz.userservice.userserviceapi.pojo.AppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "userservice-provider")

public interface UserClient {
	@PostMapping("/users/login")
	String login(@RequestBody AppUser user);
}
