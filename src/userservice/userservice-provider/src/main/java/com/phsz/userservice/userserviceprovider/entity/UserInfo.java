package com.phsz.userservice.userserviceprovider.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Long id;
    private String username;
    private String email;
    private boolean enabled;
    private String[] roles;

    public UserInfo(Long id, String username, String email, boolean enabled, String[] roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }
}
