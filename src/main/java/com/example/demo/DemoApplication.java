package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.AuthFilter;
@Configuration
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	 @Bean
	    public FilterRegistrationBean<AuthFilter> loggingFilter(AuthFilter authFilter) {
	        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();

	        registrationBean.setFilter(authFilter);
	        registrationBean.addUrlPatterns("/*"); 

	        return registrationBean;
	    }
}
