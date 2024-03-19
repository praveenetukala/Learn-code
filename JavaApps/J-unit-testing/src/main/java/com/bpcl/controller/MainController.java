package com.bpcl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

	@GetMapping("/get/")
	public String getMsg() {
		return "Hello world";
	}
}
