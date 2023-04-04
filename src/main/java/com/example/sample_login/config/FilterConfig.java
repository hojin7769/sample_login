package com.example.sample_login.config;

import com.example.sample_login.config.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    /**
     * LogFilter Bean
     * 모든 URL에 적용
     */
    @Bean
    public FilterRegistrationBean<LogFilter> logFilter() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns( "/*" );
        registrationBean.setOrder( 1 );
        registrationBean.setName( "LogFilter" );
        return registrationBean;
    }
}
