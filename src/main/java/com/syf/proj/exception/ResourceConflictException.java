package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class ResourceConflictException extends CommonExcpetion {

	private static final long serialVersionUID = 1L;

	private static final String RESOURCE_CONFLICT_KEY = "RESOURCE_CONFLICT";
	private static final String RESOURCE_CONFLICT_DESCRIPTION = "Resource conflict error";

	public ResourceConflictException() {
		super(RESOURCE_CONFLICT_KEY, RESOURCE_CONFLICT_DESCRIPTION, HttpStatus.CONFLICT);
	}

	public ResourceConflictException(String description) {
		super(RESOURCE_CONFLICT_KEY, description, HttpStatus.CONFLICT);
	}

	public ResourceConflictException(String key, String description) {
		super(key, description, HttpStatus.CONFLICT);
	}
}
