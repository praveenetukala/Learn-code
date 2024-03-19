package com.ctel.bpcl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.ctel.bpcl.model.Response;

import reactor.netty.http.server.HttpServerResponse;

@RestController
public class CustomErrorController implements ErrorController {

	private static final String PATH = "error";

	@Autowired
	private ErrorAttributes errorAttributes;

	@RequestMapping(PATH)
	@ResponseBody
	public Response<Map<String, Object>> error(WebRequest request, HttpServerResponse response) {
		return new Response<>(response.status().code(), getErrorAttributes(request));
	}

	public void setErrorAttributes(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

	@SuppressWarnings("deprecation")
	private Map<String, Object> getErrorAttributes(WebRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.putAll(this.errorAttributes.getErrorAttributes((ServerRequest) request, true));
		return map;
	}
}