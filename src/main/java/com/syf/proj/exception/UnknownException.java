package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class UnknownException extends CommonExcpetion {

	private static final long serialVersionUID = 1L;

	private static final String UNKNOWN_EXCEPTION_KEY = "UNKNOWN_EXCEPTION";
	private static final String INVALID_USER__DESCRIPTION = "An unexpected error has occured";

	public UnknownException() {
		super(UNKNOWN_EXCEPTION_KEY, INVALID_USER__DESCRIPTION, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public UnknownException(String description) {
		super(UNKNOWN_EXCEPTION_KEY, description, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public UnknownException(String key, String description) {
		super(key, description, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
