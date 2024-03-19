package com.gtwy.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserInputReq implements Serializable {

	private static final long serialVersionUID = -5812069692601673997L;

	private String username;
	private String password;
	private Integer loginId;
	private String dataKey;

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

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getDataKey() {
		return dataKey;
	}

	public void setDataKey(String dataKey) {
		this.dataKey = dataKey;
	}

	@Override
	public String toString() {
		String respose = null;
		try {
			respose = new ObjectMapper().writer().writeValueAsString(this);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
		return respose;
	}
}
