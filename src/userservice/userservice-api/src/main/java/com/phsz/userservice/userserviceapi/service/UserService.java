package com.phsz.userservice.userserviceapi.service;

import com.phsz.userservice.userserviceapi.entity.AppUser;

public interface UserService {
    public boolean register(String username, String password);

    public String getCurrentUser();

}
