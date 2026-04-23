package com.miaobill.interceptor;

import com.miaobill.context.UserContextHolder;
import com.miaobill.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("Request URL: {}, Authorization header: {}", request.getRequestURI(), token != null ? "present" : "missing");
        
        if (token != null && !token.trim().isEmpty()) {
            Long userId = jwtUtil.parseUserId(token);
            if (userId != null) {
                UserContextHolder.setCurrentUserId(userId);
                log.info("Set userId to context from token: {}", userId);
                return true;
            } else {
                log.warn("Invalid token");
                response.setStatus(401);
                response.getWriter().write("{\"code\":401,\"message\":\"未授权\"}");
                return false;
            }
        } else {
            log.warn("No Authorization header found in request");
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"message\":\"未授权\"}");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.clear();
    }
}
