package com.three.utils;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//拦截器进行登录验证(经典加一层) 真正进行拦截
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.判断是否需要拦截(ThreadLocal中是否有用户)
        if (UserHolder.getUser() == null) {
            //没有 需要拦截 设置状态码
            response.setStatus(401);
            //拦截
            return false;
        }
        //有用户 就放行
        return true;
    }

}

