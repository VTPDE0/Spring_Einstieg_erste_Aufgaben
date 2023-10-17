package com.example.demo;

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
//Filter UserIdFilter hier schlaten wir ein
		@Bean
		public FilterRegistrationBean<UserIdFilter> authenticationFilter() {
			FilterRegistrationBean<UserIdFilter> registrationBean = new FilterRegistrationBean<>();
			registrationBean.setFilter(new UserIdFilter());
			registrationBean.addUrlPatterns("/todos/*"); // Der Filter wird nur für URLs unter "/todos" angewendet
			return registrationBean;
		}
	}

}
