package com.phsz.userservice.userserviceprovider.service;

import com.phsz.userservice.userserviceprovider.pojo.User;

public interface UserService {
	String login(User user);
	String getPassword(Integer id);
	String findAll();
}
