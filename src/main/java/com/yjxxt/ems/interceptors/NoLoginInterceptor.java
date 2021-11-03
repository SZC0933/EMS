package com.yjxxt.ems.interceptors;

import com.yjxxt.ems.exceptions.NoLoginException;
import com.yjxxt.ems.service.UserService;
import com.yjxxt.ems.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoLoginInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取userId
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        // 判断是否存在id或者是否存在该id的对象
        if (userId == null || userService.selectByPrimaryKey(userId) == null) {
            // 抛出未登录异常
            throw new NoLoginException();
        }
        // 正常登录放行
        return true;
    }
}
