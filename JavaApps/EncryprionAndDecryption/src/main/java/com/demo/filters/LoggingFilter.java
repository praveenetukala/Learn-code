package com.demo.filters;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
@Order(1)
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("LoggingFilter is invoked");
		// Filter logic goes here
		chain.doFilter(request, response); // Continue the request/response chain
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("LoggingFilter.init()");

	}

	@Override
	public void destroy() {
		System.out.println("LoggingFilter.destroy()");
	}
}
