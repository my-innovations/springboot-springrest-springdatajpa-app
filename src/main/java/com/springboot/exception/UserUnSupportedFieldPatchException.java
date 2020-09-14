package com.springboot.exception;

import java.util.Set;

public class UserUnSupportedFieldPatchException extends RuntimeException {

	private static final long serialVersionUID = 6202272737149416366L;

	public UserUnSupportedFieldPatchException(Set<String> keys) {
		super("for Field " + keys.toString() + " update is not allowed.");
	}
}

