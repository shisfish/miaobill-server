package com.miaobill.service;

import com.miaobill.entity.User;
import java.util.Map;

public interface UserService {
    Map<String, Object> loginOrRegister(String code, String nickName, String avatarUrl, Integer gender);
    User getUserById(Long id);
    void updateUser(User user);
}
