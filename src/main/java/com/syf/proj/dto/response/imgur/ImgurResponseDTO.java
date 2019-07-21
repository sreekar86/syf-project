package com.syf.proj.dto.response.imgur;

import java.io.Serializable;

/**
 * 
 * @author sreekar
 *
 */
public class ImgurResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private ImgurDataDTO data;
	private String success;
	private String status;

	public ImgurResponseDTO() {
		super();
	}

	public ImgurResponseDTO(ImgurDataDTO data, String success, String status) {
		super();
		this.data = data;
		this.success = success;
		this.status = status;
	}

	public ImgurDataDTO getData() {
		return data;
	}

	public void setData(ImgurDataDTO data) {
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
		return "ImgurResponseDTO [data=" + data + ", success=" + success + ", status=" + status + "]";
	}

}
