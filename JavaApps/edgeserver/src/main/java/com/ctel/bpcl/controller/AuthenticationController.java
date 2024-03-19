package com.ctel.bpcl.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ctel.bpcl.security.model.AuthRequest;
import com.ctel.bpcl.service.UserService;

import reactor.core.publisher.Mono;

/**
 *
 * @author Gowtham G
 */
@RestController
public class AuthenticationController {

	private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
 
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest ar)   {
		logger.info("im in login controller");
		return userService.findByUsername(ar);
	}

	@PostMapping("/authenticate")
	public Mono<ResponseEntity<?>> authenticate(@RequestBody AuthRequest ar) {
		logger.info(" im in authenticate controller");
		return userService.findByUsernameAndPassword(ar);
	}
}
