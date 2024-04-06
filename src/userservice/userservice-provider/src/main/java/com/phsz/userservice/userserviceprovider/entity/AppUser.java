package com.phsz.userservice.userserviceprovider.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "app_user", schema = "public")
public class AppUser {

    final static String[] ROLES = {"USER", "ADMIN", "SUPER_ADMIN"};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "enabled")
    private boolean enabled; // 用于指示用户是否被激活
    @Column(name = "roles_bitmap")
    private int rolesBitmap; // 使用位图存储角色信息

    public String[] getRoles() {
        List<String> roles = new ArrayList<>();
        for (int i = 0; i < ROLES.length; i++) {
            if ((rolesBitmap & (1 << i)) != 0) {
                roles.add(ROLES[i]);
            }
        }
        return roles.toArray(new String[0]);
    }
}