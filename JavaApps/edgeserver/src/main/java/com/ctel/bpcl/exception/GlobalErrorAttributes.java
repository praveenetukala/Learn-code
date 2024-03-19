package com.ctel.bpcl.exception;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
		Map<String, Object> map = super.getErrorAttributes(request, options);

		int httpStatusCode = (int) map.get("status");
		if (httpStatusCode != 401) {
			map.put("status", httpStatusCode);
			map.put("message", map.get("message"));
			map.put("trace", "");
		}else {
			map.put("status", httpStatusCode);
			map.put("message", "Invalid Token");
			map.put("trace", "");
		}
		return map;
	}

}