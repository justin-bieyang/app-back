package com.justin.app_back.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI testOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Test API")
                        .description("Test API文档")
                        .version("1.0")
                        .contact(new Contact()
                                .name("JackAsher")
                                .email("jackasher36@gmail.com")));
    }
}