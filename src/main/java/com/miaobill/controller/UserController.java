package com.miaobill.controller;

import com.miaobill.common.ApiResponse;
import com.miaobill.entity.User;
import com.miaobill.service.UserService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody Map<String, Object> params) {
        String code = (String) params.get("code");
        String nickName = (String) params.get("nickName");
        String avatarUrl = (String) params.get("avatarUrl");
        Integer gender = params.get("gender") != null ? (Integer) params.get("gender") : 0;

        Map<String, Object> result = userService.loginOrRegister(code, nickName, avatarUrl, gender);
        return ApiResponse.success(result);
    }

    @GetMapping("/{id}")
    public ApiResponse<User> getUser(@PathVariable Long id) {
        return ApiResponse.success(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return ApiResponse.success(null);
    }
}
