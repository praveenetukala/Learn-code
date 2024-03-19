package com.pay.util;

import java.io.Serializable;
import java.sql.Timestamp;

public class Response<T> implements Serializable {

	private static final long serialVersionUID = 4459104759870490398L;

	private int status = 200;
	private String message = "success";
	private Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	private T payload;

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

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(T payload) {
		this.payload = payload;
	}

	public Response(int status, String message, T payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}
}
