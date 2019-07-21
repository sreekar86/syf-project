package com.syf.proj.dto.response.image;

import java.io.Serializable;

/**
 * 
 * @author sreekar
 *
 */
public class ImageDeleteResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String success;
	private String status;

	public ImageDeleteResponseDTO() {
		super();
	}

	public ImageDeleteResponseDTO(String success, String status) {
		super();
		this.success = success;
		this.status = status;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ImageDeleteResponseDTO [success=" + success + ", status=" + status + "]";
	}

}
