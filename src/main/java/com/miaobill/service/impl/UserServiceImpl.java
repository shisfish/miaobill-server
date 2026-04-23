package com.miaobill.service.impl;

import com.miaobill.entity.User;
import com.miaobill.mapper.UserMapper;
import com.miaobill.service.UserService;
import com.miaobill.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUtil jwtUtil;

    @Value("${wechat.appid}")
    private String appid;

    @Value("${wechat.secret}")
    private String secret;

    private final RestTemplate restTemplate = new RestTemplate();

    private String generateDefaultNickName() {
        int phonePart = ThreadLocalRandom.current().nextInt(1000, 9999);
        int randomPart = ThreadLocalRandom.current().nextInt(1000, 9999);
        return "杪记" + phonePart + randomPart;
    }

    @Override
    public Map<String, Object> loginOrRegister(String code, String nickName, String avatarUrl, Integer gender) {
        String url = String.format(
                "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                appid, secret, code);

        String response = restTemplate.getForObject(url, String.class);
        log.info("微信登录响应: {}", response);

        Map<String, Object> result;
        try {
            result = objectMapper.readValue(response, Map.class);
        } catch (Exception e) {
            log.error("解析微信登录响应失败", e);
            throw new RuntimeException("微信登录失败");
        }

        if (result == null || result.get("openid") == null) {
            log.error("微信登录失败: {}", result);
            throw new RuntimeException("微信登录失败");
        }

        String openid = (String) result.get("openid");
        String unionId = result.get("unionid") != null ? (String) result.get("unionid") : null;

        if (!StringUtils.hasText(nickName)) {
            nickName = generateDefaultNickName();
        }

        User user = userMapper.findByOpenid(openid);

        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setUnionId(unionId);
            user.setNickName(nickName);
            user.setAvatarUrl(avatarUrl);
            user.setGender(gender != null ? gender : 0);
            user.setStatus(1);
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.insert(user);
        } else {
            user.setNickName(nickName);
            user.setAvatarUrl(avatarUrl);
            user.setGender(gender != null ? gender : user.getGender());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.update(user);
        }

        String token = jwtUtil.generateToken(user.getId());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("id", user.getId());
        responseMap.put("nickName", user.getNickName());
        responseMap.put("avatarUrl", user.getAvatarUrl());
        responseMap.put("gender", user.getGender());
        responseMap.put("token", token);
        return responseMap;
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    public void updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}
