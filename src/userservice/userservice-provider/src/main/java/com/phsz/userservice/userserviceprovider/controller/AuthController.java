package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.userservice.userserviceprovider.JwtTokenProvider;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    public ResponseEntity<?> login(@RequestBody AppUser user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenProvider.createToken(
                    user.getUsername(),
                    userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
            );

            Map<Object, Object> model = new HashMap<>();
            model.put("username", user.getUsername());
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAccount(@RequestBody AppUser user) {
        try {
            boolean registered = userService.register(user.getUsername(), user.getPassword());
            if (registered) {
                return ResponseEntity.ok("User registered successfully");
            } else {
                return ResponseEntity.badRequest().body("User already exists");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
