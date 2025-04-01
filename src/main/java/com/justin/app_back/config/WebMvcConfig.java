package com.justin.app_back.config;

import com.justin.app_back.intercept.LoginIntercept;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置类
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginIntercept())
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/login") // 排除登录路径
                .excludePathPatterns("/register/*") // 排除注册路径
                .excludePathPatterns("/swagger-resources/**") // 排除Swagger资源
                .excludePathPatterns("/webjars/**") // 排除Swagger资源
                .excludePathPatterns("/v2/**") // 排除Swagger资源
                .excludePathPatterns("/doc.html") // 排除Knife4j资源
                .excludePathPatterns("/**/*.jpg") // 排除静态资源
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.gif")
                .excludePathPatterns("/**/*.jpeg")
                .excludePathPatterns("/**/*.apk");
    }
} 