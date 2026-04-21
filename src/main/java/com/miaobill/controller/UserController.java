package com.miaobill.controller;

import com.miaobill.entity.User;
import com.miaobill.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> params) {
        String code = (String) params.get("code");
        String nickName = (String) params.get("nickName");
        String avatarUrl = (String) params.get("avatarUrl");
        Integer gender = params.get("gender") != null ? (Integer) params.get("gender") : 0;

        User user = userService.loginOrRegister(code, nickName, avatarUrl, gender);

        Map<String, Object> result = new HashMap<>();
        result.put("id", user.getId());
        result.put("nickName", user.getNickName());
        result.put("avatarUrl", user.getAvatarUrl());
        result.put("gender", user.getGender());
        return result;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }
}
