package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class ResourceNotFoundException extends CommonExcpetion {

	private static final long serialVersionUID = 1L;

	private static final String RESOURCE_NOT_FOUND_KEY = "RESOURCE_NOT_FOUND";
	private static final String RESOURCE_NOT_FOUND_DESCRIPTION = "Resource could not be found";

	public ResourceNotFoundException() {
		super(RESOURCE_NOT_FOUND_KEY, RESOURCE_NOT_FOUND_DESCRIPTION, HttpStatus.NOT_FOUND);
	}

	public ResourceNotFoundException(String description) {
		super(RESOURCE_NOT_FOUND_KEY, description, HttpStatus.NOT_FOUND);
	}

	public ResourceNotFoundException(String key, String description) {
		super(key, description, HttpStatus.NOT_FOUND);
	}
}
