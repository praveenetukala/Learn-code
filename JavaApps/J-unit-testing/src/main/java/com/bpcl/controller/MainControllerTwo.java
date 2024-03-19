package com.bpcl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainControllerTwo {

	@GetMapping("/greet/")
	public String getMsg() {
		return "Good Morning";
	}
}
