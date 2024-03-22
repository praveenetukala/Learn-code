package com.hello;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterController {

	@RequestMapping("/health")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok(new Health("200", "Survice is up and Running"));
	}
}

class Health {
	private String status;
	private String message;

	public Health(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
