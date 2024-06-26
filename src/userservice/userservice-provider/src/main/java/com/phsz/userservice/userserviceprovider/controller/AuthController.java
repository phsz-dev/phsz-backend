package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.userservice.userserviceprovider.JwtTokenProvider;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public Result login(@RequestBody AppUser user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            AppUser appUser = userService.getUserByUsername(userDetails.getUsername());
            String token = jwtTokenProvider.createToken(
                    appUser.getId(),
                    user.getUsername(),
                    userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
            );

            Map<Object, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            model.put("token", token);
            return Result.success("Login successful", model);
        } catch (AuthenticationException e) {
            return Result.error("Invalid username/password supplied");
        }
    }

    @PostMapping("/register")
    public Result registerUserAccount(@RequestBody AppUser user) {
        try {
            boolean registered = userService.register(user.getUsername(), user.getPassword());
            if (registered) {
                return Result.success("User registered successfully");
            } else {
                return Result.error("User already exists");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
