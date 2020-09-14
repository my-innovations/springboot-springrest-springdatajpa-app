package com.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6554519612346519126L;

	public UserNotFoundException(String exceMsg) {
		super(exceMsg);
	}
	

	/*public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	 */
}
