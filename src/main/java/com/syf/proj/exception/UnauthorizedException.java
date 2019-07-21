package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class UnauthorizedException extends CommonExcpetion {

	private static final long serialVersionUID = 1L;

	private static final String UNAUTHORIZED_KEY = "UNAUTHORIZED";
	private static final String UNAUTHORIZED_DESCRIPTION = "You are not authorized to view the resource";

	public UnauthorizedException() {
		super(UNAUTHORIZED_KEY, UNAUTHORIZED_DESCRIPTION, HttpStatus.UNAUTHORIZED);
	}

	public UnauthorizedException(String description) {
		super(UNAUTHORIZED_KEY, description, HttpStatus.UNAUTHORIZED);
	}

	public UnauthorizedException(String key, String description) {
		super(key, description, HttpStatus.UNAUTHORIZED);
	}
}
