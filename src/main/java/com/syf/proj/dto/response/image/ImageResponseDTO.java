package com.syf.proj.dto.response.image;

import java.io.Serializable;

/**
 * 
 * @author sreekar
 *
 */
public class ImageResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer imageId;
	private String imageUrl;
	private String imageType;
	private String insertedTs;

	public ImageResponseDTO() {
		super();
	}

	public ImageResponseDTO(Integer imageId, String imageUrl, String imageType, String insertedTs) {
		super();
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.imageType = imageType;
		this.insertedTs = insertedTs;
	}

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getInsertedTs() {
		return insertedTs;
	}

	public void setInsertedTs(String insertedTs) {
		this.insertedTs = insertedTs;
	}

	@Override
	public String toString() {
		return "ImageResponseDTO [imageId=" + imageId + ", imageUrl=" + imageUrl + ", imageType=" + imageType
				+ ", insertedTs=" + insertedTs + "]";
	}

}
