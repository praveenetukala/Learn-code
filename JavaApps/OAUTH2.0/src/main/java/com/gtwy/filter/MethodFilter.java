package com.gtwy.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MethodFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("Method --- >>> : " + request.getMethod());
		System.out.println("URL ---  >>> : " + request.getRequestURI());

		if (request.getMethod().equals("OPTIONS")) {
			System.out.println("-------- Entered into Options ---------");
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
