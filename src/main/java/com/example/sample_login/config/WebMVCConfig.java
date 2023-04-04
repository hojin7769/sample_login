package com.example.sample_login.config;

import com.example.sample_login.config.interceptor.WebLoggingInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMVCConfig implements WebMvcConfigurer {

    static final String RESOURCE_PATTEN = "/**";
    static final String RESOURCE_LOCATION = "classpath:/static/";
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new WebLoggingInterceptor() );
    }
}
