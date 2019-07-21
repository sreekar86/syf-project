package com.syf.proj.dto.response.imgur;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author sreekar
 *
 */
public class ImgurDeleteResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	private String data;
	private String success;
	private String status;

	
	public ImgurDeleteResponseDTO() {
		super();
	}

	public ImgurDeleteResponseDTO(String data, String success, String status) {
		super();
		this.data = data;
		this.success = success;
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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
		return "ImgurDeleteResponseDTO [data=" + data + ", success=" + success + ", status=" + status + "]";
	}

}
