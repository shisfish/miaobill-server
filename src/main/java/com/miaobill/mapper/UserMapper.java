package com.miaobill.mapper;

import com.miaobill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByOpenid(@Param("openid") String openid);
    void insert(User user);
    void update(User user);
    User findById(@Param("id") Long id);
}
