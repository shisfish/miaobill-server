package com.miaobill.service;

import com.miaobill.entity.User;

public interface UserService {
    User loginOrRegister(String code, String nickName, String avatarUrl, Integer gender);
    User getUserById(Long id);
    void updateUser(User user);
}
