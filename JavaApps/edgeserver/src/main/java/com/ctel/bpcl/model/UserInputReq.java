package com.ctel.bpcl.model;

import java.io.Serializable;

public class UserInputReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5812069692601673997L;

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInputReq [username=" + username + ", password=" + password + "]";
	}

	
}
