package com.springboot.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 4917215964909216279L;

	public EmailAlreadyExistsException(String exceMsg) {
		super(exceMsg);
	}

	public EmailAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
