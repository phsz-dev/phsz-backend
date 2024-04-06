package com.phsz.userservice.userserviceapi.service;

public interface UserService {
	public boolean register(String username, String password);
	public String getCurrentUser();

}
