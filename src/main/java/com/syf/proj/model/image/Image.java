package com.syf.proj.model.image;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.syf.proj.model.user.UserInfo;

/**
 * 
 * @author sreekar
 *
 */
@Entity
@Table(name = "image")
public class Image implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "image_id")
	private Integer imageId;

	@Column(name = "imgur_id")
	@NotEmpty
	private String imgurId;

	@Column(name = "imgur_url")
	@NotEmpty
	private String imageUrl;

	@Column(name = "image_type")
	private String imageType;

	@Column(name = "inserted_ts")

	private Date insertedTs;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public String getImgurId() {
		return imgurId;
	}

	public void setImgurId(String imgurId) {
		this.imgurId = imgurId;
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

	public Date getInsertedTs() {
		return insertedTs;
	}

	public void setInsertedTs(Date insertedTs) {
		this.insertedTs = insertedTs;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imgurId=" + imgurId + ", imageUrl=" + imageUrl + ", imageType="
				+ imageType + ", insertedTs=" + insertedTs + ", userInfo=" + userInfo + "]";
	}

}
