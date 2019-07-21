package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class InvalidUserException extends CommonExcpetion {

	private static final long serialVersionUID = 1L;

	private static final String INVALID_USER_KEY = "AUTHENTICATION_FAILED";
	private static final String INVALID_USER_DESCRIPTION = "Invalid username/password";

	public InvalidUserException() {
		super(INVALID_USER_KEY, INVALID_USER_DESCRIPTION, HttpStatus.FORBIDDEN);
	}

	public InvalidUserException(String description) {
		super(INVALID_USER_KEY, description, HttpStatus.FORBIDDEN);
	}

	public InvalidUserException(String key, String description) {
		super(key, description, HttpStatus.FORBIDDEN);
	}
}
