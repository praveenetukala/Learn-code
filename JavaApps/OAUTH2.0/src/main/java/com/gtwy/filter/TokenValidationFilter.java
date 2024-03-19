package com.gtwy.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.net.HttpHeaders;

//@Component
public class TokenValidationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String method = request.getMethod();
		String path = request.getRequestURI();
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		System.out.println("Request Method: " + method + ", Path: " + path);
		System.out.println("Token: " + token);

		if ("/v1/login".equals(path)) {
			filterChain.doFilter(request, response);
			return;
		}

		if (token == null) {
			this.sendError(response, "No token provided", HttpStatus.UNAUTHORIZED);
			return;
		}

		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			sendError(response, e.getMessage(), HttpStatus.BAD_REQUEST);
			return;
		}
	}

	private void sendError(HttpServletResponse response, String message, HttpStatus status) throws IOException {
		System.out.println("TokenValidationFilter.sendError()");
		response.setStatus(status.value());
		response.getWriter().write(message);
	}

}
