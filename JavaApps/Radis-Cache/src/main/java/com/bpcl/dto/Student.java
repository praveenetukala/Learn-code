package com.bpcl.dto;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 4910043247539427076L;

	private String id;
	private String name;
	private String mail;

	public Student(String id, String name, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	// Getters And Setters
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
		return "Student [id=" + id + ", name=" + name + ", mail=" + mail + "]";
	}
}
