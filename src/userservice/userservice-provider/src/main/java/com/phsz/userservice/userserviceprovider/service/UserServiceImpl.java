package com.phsz.userservice.userserviceprovider.service;

import com.phsz.userservice.userserviceapi.service.UserService;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

		org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(appUser.getPassword());
		builder.roles("USER"); // 假设每个用户都有"USER"角色，根据需要调整
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
		userRepository.save(appUser);
		return true;
	}

	@PreAuthorize("hasRole('USER')")
	public String getCurrentUser() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
