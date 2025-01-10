package com.example.dncompany.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.pet-img}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourceUrlPath="/upload/pet/**";
        String resourceLocation="file:"+uploadPath+"/";
        registry.addResourceHandler(resourceUrlPath).addResourceLocations(resourceLocation);
    }
}
