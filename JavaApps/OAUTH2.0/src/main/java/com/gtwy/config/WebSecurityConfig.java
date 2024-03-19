package com.gtwy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.gtwy.filter.TokenValidationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// Configuring HTTP security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// Disable CSRF protection
				.csrf().disable()
				// Configure URL-based security
				.authorizeRequests()
				// Allow access without authentication
				.antMatchers("/", "/v1/login").permitAll()
				// Require authentication for OAuth authorization endpoint
				.antMatchers("/oauth/authorize").authenticated()
				// Configure form-based login
				.and().formLogin()
				// Configure additional request patterns that require authentication
				.and().requestMatchers().antMatchers("/", "/v1/login", "/oauth/authorize", "/api/private/v1");
	}

	// Expose AuthenticationManager as a bean
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// Rename the tokenValidationFilter bean in WebSecurityConfig class
	@Bean
	public TokenValidationFilter customTokenValidationFilter() {
		return new TokenValidationFilter();
	}
}
