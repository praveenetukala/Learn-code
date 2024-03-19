package com.pay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pay.service.SMPTService;
import com.pay.util.Response;

@RestController
public class MasterController {

	@Autowired
	private SMPTService smptService;

	@GetMapping(value = "/", produces = { "application/json" })
	public ResponseEntity<?> checkHealth() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Service is up and Running...!", ""));
	}

	@GetMapping(value = "/sendmail")
	public void sendMail() {
		smptService.sendMail("sbgoud63@gmail.com", "Nothing", "U Got the Mail");
	}
}
