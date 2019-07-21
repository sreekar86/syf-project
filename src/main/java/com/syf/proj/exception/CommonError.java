package com.syf.proj.exception;

import java.io.Serializable;

/**
 * 
 * @author sreekar
 *
 */
public class CommonError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String key;

	private String description;

	public CommonError(String key, String description) {
		super();
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CommonError [key=" + key + ", description=" + description + "]";
	}

}
