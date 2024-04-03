package com.phsz.userservice.userserviceprovider.service;

import com.phsz.userservice.userserviceapi.service.UserService;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	@Resource
	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));

		User.UserBuilder builder = User.withUsername(username);
		builder.password(appUser.getPassword());
		builder.roles(appUser.getRoles());
		// 输出roles
		 System.out.println(Arrays.toString(appUser.getRoles()));
		builder.disabled(!appUser.isEnabled());
		return builder.build();
	}

	public boolean register(String username, String password) {
		if (userRepository.findByUsername(username).isPresent()) {
			return false;
		}
		AppUser appUser = new AppUser();
		appUser.setUsername(username);
		appUser.setPassword(passwordEncoder.encode(password));
		appUser.setEnabled(true);
		appUser.setRolesBitmap(1);
		userRepository.save(appUser);
		return true;
	}

	@Override
	public String getCurrentUser() {
		return null;
	}

	public AppUser getUserByName(String username) {
        return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
	}
}
