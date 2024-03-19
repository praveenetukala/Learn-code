package com.gtwy.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreFilter extends ZuulFilter {

	// Indicates whether the filter should be executed
	@Override
	public boolean shouldFilter() {
		return true; // Always execute this filter
	}

	// Logic of the filter
	@Override
	public Object run() throws ZuulException {
		System.out.println("PreFilter.run()");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String url = request.getRequestURI();
		System.out.println("Request URL: " + url);

		String accessToken = request.getHeader("Authorization");
		System.out.println("Access Token: " + accessToken);

		// Check if access token is present
		if (!StringUtils.isEmpty(accessToken)) {
			System.out.println("Zuul if: Access token present");
			// Allow the request to proceed
			ctx.setSendZuulResponse(true);
			ctx.setResponseStatusCode(200);
			ctx.set("isSuccess", true); // Set additional value
			return null;
		} else {
			System.out.println("Zuul else: Access token not present");
			// Reject the request with a 401 unauthorized response
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"result\":\"accessToken is not correct!\"}");
			ctx.set("isSuccess", false); // Set additional value
			return null;
		}
	}

	// Specifies the type of the filter (pre-filter in this case)
	@Override
	public String filterType() {
		return "pre";
	}

	// Specifies the order of the filter execution
	@Override
	public int filterOrder() {
		return 1; // Execute this filter first
	}
}
