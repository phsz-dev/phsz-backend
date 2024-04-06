package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.entity.UserInfo;
import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public Result getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AppUser> users = userService.getAll(pageable);
        List<UserInfo> userInfos = users.map(user -> new UserInfo(user.getId(), user.getUsername(), user.getEmail(), user.isEnabled(), user.getRoles())).getContent();
        return Result.success("Get all users successful", userInfos);
    }

    @GetMapping("/me")
    public Result getCurrentUser(@RequestHeader("Username") String username, @RequestHeader("Roles") String[] roles) {
        AppUser appUser = userService.getUserByName(username);
        UserInfo userInfo = new UserInfo(appUser.getId(), appUser.getUsername(), appUser.getEmail(), appUser.isEnabled(), roles);
        return Result.success("Get current user successful", userInfo);
    }

}
