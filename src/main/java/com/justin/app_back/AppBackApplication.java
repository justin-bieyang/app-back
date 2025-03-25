package com.justin.app_back;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.justin.app_back.mapper")
@EnableKnife4j
public class AppBackApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(AppBackApplication.class, args);
    }

}
