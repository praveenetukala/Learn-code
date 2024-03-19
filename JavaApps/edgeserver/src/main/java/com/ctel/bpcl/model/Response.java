package com.ctel.bpcl.model;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 
 * @author Gowtham G
 * Global response entity
 */
public class Response<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4459104759870490398L;

	private int status = 200;

	private String message = "success";

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",shape = Shape.STRING)
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

	public Response(int status, T payload) {
		super();
		this.status = status;
 		this.payload = payload;
	}

}
