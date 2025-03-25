package com.justin.app_back;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.justin.app_back.intercept.LoginIntercept;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.justin.app_back.mapper")
@EnableKnife4j
public class AppBackApplication implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginIntercept())
//                .addPathPatterns("/**") // 拦截路径
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/register")
//                .excludePathPatterns("/**/*.jpg")
//                .excludePathPatterns("/**/*.png")
//                .excludePathPatterns("/**/*.gif")
//                .excludePathPatterns("/**/*.jpeg")
//                .excludePathPatterns("/**/*.apk");
//    }

    public static void main(String[] args) {
        SpringApplication.run(AppBackApplication.class, args);
    }

}
