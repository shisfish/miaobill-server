package com.miaobill.interceptor;

import com.miaobill.context.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserContextInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(UserContextInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userIdParam = request.getParameter("userId");
        log.info("Request URL: {}, userId param: {}", request.getRequestURI(), userIdParam);
        
        if (userIdParam != null && !userIdParam.trim().isEmpty()) {
            try {
                Long userId = Long.parseLong(userIdParam);
                UserContextHolder.setCurrentUserId(userId);
                log.info("Set userId to context: {}", userId);
            } catch (NumberFormatException e) {
                log.warn("Invalid userId parameter: {}", userIdParam);
            }
        } else {
            log.warn("No userId parameter found in request");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.clear();
    }
}
