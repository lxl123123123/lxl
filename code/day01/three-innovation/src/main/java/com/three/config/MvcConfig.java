package com.three.config;

import com.three.utils.LoginInterceptor;
import com.three.utils.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //token刷新拦截器(拦截所有)
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).addPathPatterns("/**");
        //登录拦截器(真正进行拦截操作的 拦截部分)
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(
                        "/user/code",
//                        "/user/save",
//                        "/user/select",
                        "/user/login"
                );
    }
}
