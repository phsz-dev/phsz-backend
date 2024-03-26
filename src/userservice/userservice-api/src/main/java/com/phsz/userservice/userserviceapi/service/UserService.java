package com.phsz.userservice.userserviceapi.service;

public interface UserService {
	String login(Long user_id, String password);
	String findAll();
}
