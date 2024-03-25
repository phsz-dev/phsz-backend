package com.phsz.userservice.userserviceprovider.service.impl;

import com.phsz.userservice.userserviceprovider.pojo.User;
import com.phsz.userservice.userserviceprovider.repository.UserRepository;
import com.phsz.userservice.userserviceprovider.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public String login(User user) {
		if(userRepository.findById(user.user_id).get().getPassword().equals(user.password)){
			return "ture";
		}
		else return "false";
	}

	@Override
	public String getPassword(Integer id) {
		return userRepository.findById(id).get().getPassword();
	}

	@Override
	public String findAll() {

		return userRepository.findAll().toString();
	}
}
