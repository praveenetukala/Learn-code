package com.gtwy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gtwy.model.Response;
import com.gtwy.oauthReqRes.AuthRequest;
import com.gtwy.service.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Checking the health of the application
	 * 
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<?> root() {
		return ResponseEntity.ok(new Response<>(200, "Server is up and running!!!", ""));
	}

	/**
	 * Checking the health of the application
	 * 
	 * @return
	 */
	@GetMapping("/api/private/v1")
	public ResponseEntity<?> getPrivateV1() {
		return ResponseEntity.ok(new Response<>(200, "Successfully get private data!!!", ""));
	}

	/**
	 * Checking the health of the application
	 * 
	 * @return
	 */
	@GetMapping("/api/public/v1")
	public ResponseEntity<?> getPrublicV1() {
		return ResponseEntity.ok(new Response<>(200, "Successfully get public data!!!", ""));
	}

	/**
	 * User login
	 * 
	 * @param authRequest
	 * @return
	 */
	@PostMapping("/v1/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
		System.out.println("UserController.login()");
		return userService.findByUsername(authRequest);
	}
}
