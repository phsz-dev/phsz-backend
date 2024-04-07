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


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public Result getAll(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<AppUser> users = userService.getAll(pageable);
        Page<UserInfo> userInfos = users.map(user -> new UserInfo(user.getId(), user.getUsername(), user.getEmail(), user.isEnabled(), user.getRoles()));
        return Result.success("Get all users successful", userInfos);
    }

    @GetMapping("/me")
    public Result getCurrentUser(@RequestHeader("Username") String username, @RequestHeader("Roles") String[] roles) {
        AppUser appUser = userService.getUserByName(username);
        UserInfo userInfo = new UserInfo(appUser.getId(), appUser.getUsername(), appUser.getEmail(), appUser.isEnabled(), roles);
        return Result.success("Get current user successful", userInfo);
    }

    @PutMapping
    public Result updateUser(@RequestBody UserInfo userInfo) {
        System.out.println(userInfo);
           AppUser appUser = new AppUser();
           appUser.setId(userInfo.getId());
           appUser.setUsername(userInfo.getUsername());
           appUser.setEmail(userInfo.getEmail());
           appUser.setEnabled(userInfo.isEnabled());
           appUser.setRolesBitmap(userInfo.getRolesBitmap(userInfo.getRoles()));
           appUser.setPassword(userService.getUserByName(userInfo.getUsername()).getPassword());
           userService.updateUser(appUser);
           return Result.success("Update user successful", null);
    }
}
