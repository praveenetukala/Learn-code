package com.demo.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean<SecurityFilter> filterRegistrationBean() {
		FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<SecurityFilter>();

		registrationBean.setFilter(new SecurityFilter());
		registrationBean.setOrder(2);
		return registrationBean;
	}
}
