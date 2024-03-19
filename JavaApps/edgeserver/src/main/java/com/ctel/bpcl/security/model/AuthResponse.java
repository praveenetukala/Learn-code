package com.ctel.bpcl.security.model;

import java.io.Serializable;

import com.ctel.bpcl.entity.User;

/**
 *
 * @author Gowtham G
 */
public class AuthResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3220389809422750547L;

	private int status = 200;

	private String message = "success";

	private String token;

	private User userDetails;
	
	private String response;

	public AuthResponse(int statusCode, String message, String token, User userDetails) {
		super();
		this.status = statusCode;
		this.message = message;
		this.token = token;
		this.userDetails = userDetails;
	}
	public AuthResponse(String response, String message, String token,int statusCode) {
		super();
		this.status = statusCode;
		this.message = message;
		this.token = token;
		this.response = response;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(User userDetails) {
		this.userDetails = userDetails;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

}
