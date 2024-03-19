package com.gtwy.exception;

import org.springframework.http.HttpStatus;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 445594894405356372L;

	public GlobalException(HttpStatus status, String message) {
		super(message);
	}

	public GlobalException(HttpStatus status, String message, Throwable e) {
		super(message, e);
	}
}