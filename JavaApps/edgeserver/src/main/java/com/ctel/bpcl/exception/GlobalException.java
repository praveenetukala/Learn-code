package com.ctel.bpcl.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author Gowtham
 * Global Exception for throw dynamic exception
 */
public class GlobalException extends RuntimeException {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 445594894405356372L;

	public GlobalException(HttpStatus status, String message) {
        super(message);
    }

    public GlobalException(HttpStatus status, String message, Throwable e) {
        super(message, e);
    }
}