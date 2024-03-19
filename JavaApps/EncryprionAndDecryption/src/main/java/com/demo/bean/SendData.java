package com.demo.bean;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SendData implements Serializable, Cloneable {

	private static final long serialVersionUID = -7803481470575280185L;

	private String id;
	private String name;
	private String mail;

	public SendData() {
		super();
	}

	public SendData(String id, String name, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		String respose = null;
		try {
			respose = new ObjectMapper().writer().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respose;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
