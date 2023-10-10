package com.example.demo;
//change für commit

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Configuration
	public class FilterConfig {

		@Bean
		public FilterRegistrationBean<SimpleAuthenticationFilter> authenticationFilter() {
			FilterRegistrationBean<SimpleAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
			registrationBean.setFilter(new SimpleAuthenticationFilter());
			registrationBean.addUrlPatterns("/todos/*"); // Der Filter wird nur für URLs unter "/todos" angewendet
			return registrationBean;
		}
	}

}
