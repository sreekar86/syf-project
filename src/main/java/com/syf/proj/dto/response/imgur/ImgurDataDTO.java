package com.syf.proj.dto.response.imgur;

import java.io.Serializable;

/**
 * 
 * @author sreekar
 *
 */
public class ImgurDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String dateTime;
	private String type;
	private String deleteHash;
	private String link;

	public ImgurDataDTO() {
		super();
	}

	public ImgurDataDTO(String id, String dateTime, String type, String deleteHash, String link) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.type = type;
		this.deleteHash = deleteHash;
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeleteHash() {
		return deleteHash;
	}

	public void setDeleteHash(String deleteHash) {
		this.deleteHash = deleteHash;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ImgurDataDTO [id=" + id + ", dateTime=" + dateTime + ", type=" + type + ", deleteHash=" + deleteHash
				+ ", link=" + link + "]";
	}

}
