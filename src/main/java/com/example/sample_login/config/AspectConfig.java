package com.example.sample_login.config;

import com.example.sample_login.config.aspect.APILoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AspectConfig {

    @Bean
    public APILoggingAspect apiLoggingAspect(){
        return new APILoggingAspect();
    }
}
