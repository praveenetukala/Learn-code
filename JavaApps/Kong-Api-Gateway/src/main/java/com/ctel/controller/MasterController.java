package com.ctel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {

	@RequestMapping("/test")
	public ResponseEntity<?> test() {
		System.out.println("test method called");
		return ResponseEntity.ok("Data fecthed successfully from test");
	}

	@RequestMapping("/test1")
	public ResponseEntity<?> test1() {
		System.out.println("test1 method called");
		return ResponseEntity.ok("Data fecthed successfully test1");
	}
	
	@RequestMapping("/")
	public ResponseEntity<?> get() {
		System.out.println("get{}");
		return ResponseEntity.ok("Data fecthed successfully  ");
	}

}
