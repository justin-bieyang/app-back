package com.justin.app_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 小杜
 * @version 1.0
 * @since 1.0
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许的域名，如果是具体的域名，请替换为实际的前端域名
        config.addAllowedOrigin("http://localhost:8080");  // 前端本地开发地址
        config.addAllowedOrigin("http://localhost:3000");  // 其他可能的前端开发地址
        config.addAllowedOrigin("http://localhost:3001");  // 其他可能的前端开发地址
        // 如果有实际的生产环境域名，也可以添加
        // config.addAllowedOrigin("https://yourdomain.com");
        
        // 允许携带cookie等凭证信息
        config.setAllowCredentials(true);
        // 允许的请求方法
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");
        // 允许的请求头
        config.addAllowedHeader("*");
        // 预检请求的有效期
        config.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
