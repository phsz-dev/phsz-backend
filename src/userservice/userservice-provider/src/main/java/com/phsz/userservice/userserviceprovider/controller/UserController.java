package com.phsz.userservice.userserviceprovider.controller;

import com.phsz.common.Result;
import com.phsz.common.SimplePage;
import com.phsz.userservice.userserviceprovider.entity.AppUser;
import com.phsz.userservice.userserviceprovider.entity.UserInfo;
import com.phsz.userservice.userserviceprovider.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping
    public Result getAll(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                         @RequestParam(value = "orderColumn", defaultValue = "id") String orderColumn,
                         @RequestParam(value = "orderType", defaultValue = "ASC") String orderType
    ) {
        if (orderColumn.equals("roles")) {
            // 改成roles_bitmap
            orderColumn = "rolesBitmap";
        }
        Sort sort = orderType.equals("ASC") ? Sort.by(orderColumn).ascending() : Sort.by(orderColumn).descending();
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        Page<AppUser> users = userService.getAll(pageable);
        Page<UserInfo> userInfos = users.map(user -> new UserInfo(user.getId(), user.getUsername(), user.getEmail(), user.isEnabled(),user.getAvatar(), user.getRoles()));
        return Result.success("Get all users successful", new SimplePage<>(userInfos));
    }

    @GetMapping("/me")
    public Result getCurrentUser(@RequestHeader("Username") String username, @RequestHeader("Roles") String[] roles) {
        AppUser appUser = userService.getUserByName(username);
        UserInfo userInfo = new UserInfo(appUser.getId(), appUser.getUsername(), appUser.getEmail(), appUser.isEnabled(), appUser.getAvatar(), roles);
        return Result.success("Get current user successful", userInfo);
    }

    @PutMapping("/update/normal")
    public Result updateUserNormal(@RequestBody UserInfo userInfo, @RequestHeader("UserId") String userId) {
        AppUser appUser = new AppUser();
        appUser.setId(Long.parseLong(userId));
        appUser.setEmail(userInfo.getEmail());
        int num = userService.updateUserNormal(appUser);
        if(num == 0) {
            return Result.error("Update user failed");
        }
        return Result.success("Revise user successful", null);
    }

    @PutMapping("/update/avatar")
    public Result updateUserAvatar(@RequestParam("avatar") String fileUrl, @RequestHeader("UserId") String userId) {
        AppUser appUser = new AppUser();
        int num = userService.updateUserAvatar(fileUrl, userId);
        if(num == 0) {
            return Result.error("Update user failed");
        }
        return Result.success("Revise user successful", null);
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
