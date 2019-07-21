package com.syf.proj.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author sreekar
 *
 */
public class CommonExcpetion extends Exception {

	private static final long serialVersionUID = 1L;

	private CommonError error;
	private HttpStatus stausCode;

	public CommonExcpetion(String message) {
		super(message);
	}

	public CommonExcpetion(CommonError error, HttpStatus stausCode) {
		this.error = error;
		this.stausCode = stausCode;
	}

	public CommonExcpetion(String key, String description, HttpStatus stausCode) {
		this(new CommonError(key, description), stausCode);
	}

	public CommonError getError() {
		return error;
	}

	public void setError(CommonError error) {
		this.error = error;
	}

	public HttpStatus getStausCode() {
		return stausCode;
	}

	public void setStausCode(HttpStatus stausCode) {
		this.stausCode = stausCode;
	}

	@Override
	public String toString() {
		return "CommonExcpetion [error=" + error + ", stausCode=" + stausCode + "]";
	}

}
