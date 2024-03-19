package com.ctel.bpcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.bpcl.model.Response;

import reactor.core.publisher.Mono;

@RestController
public class FallBackMethodController {

	private Logger logger = LoggerFactory.getLogger(FallBackMethodController.class);

	
	@GetMapping("/fallback")
	public Mono<?> fallback() {

		logger.error("API is taking too long to respond or is down. Please try again later");

		return Mono
				.just(new Response<>(HttpStatus.OK.value(), "API is taking too long to respond or is down. Please try again later", null));
	}

	@GetMapping("health")
	public Mono<?> status() {
		logger.info("edgeserver is up & running");

		return Mono.just(new Response<>(HttpStatus.OK.value(), "UP", null));
	}

	@GetMapping
	public Mono<?> home() {
		return Mono.just(new Response<>(HttpStatus.OK.value(), "Hello World!!!", null));

	}

}