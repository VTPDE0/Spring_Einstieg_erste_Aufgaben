package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private BenutzerRepository benutzerRepository;

    @Bean
    public FilterRegistrationBean<UserIdFilter> authenticationFilter() {
        FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new UserIdFilter(benutzerRepository));
        registrationBean.addUrlPatterns("/todos/*");
        return registrationBean;
    }
}
