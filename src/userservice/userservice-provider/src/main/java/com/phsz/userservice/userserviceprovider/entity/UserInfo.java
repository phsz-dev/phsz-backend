package com.phsz.userservice.userserviceprovider.entity;

import lombok.Data;

@Data
public class UserInfo {
    final static String[] ROLES = {"USER", "ADMIN", "SUPER_ADMIN"};

    private Long id;
    private String username;
    private String email;
    private String avatar;
    private boolean enabled;
    private String[] roles;

    public UserInfo(Long id, String username, String email, boolean enabled,String avatar, String[] roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.avatar = avatar;
        this.roles = roles;
    }

    public Integer getRolesBitmap(String[] roles) {
        int bitmap = 0;
        for (String role : roles) {
            for (int i = 0; i < ROLES.length; i++) {
                if (role.equals(ROLES[i])) {
                    bitmap |= (1 << i);
                    break;
                }
            }
        }
        return bitmap;
    }
}
