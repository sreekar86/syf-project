package com.syf.proj.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.syf.proj.model.image.Image;

/**
 * 
 * @author sreekar
 *
 */
@Entity
@Table(name = "user_info")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name", unique = true)
	@NotEmpty
	private String userName;

	@Column(name = "password")
	@NotEmpty
	private String password;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	private String gender;

	@Email
	private String email;

	@NotEmpty
	private String phoneNumber;

	@Column(name = "inserted_ts")
	private Date insertedTs;

	@Column(name = "modified_ts")
	private Date modifiedTs;

	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getInsertedTs() {
		return insertedTs;
	}

	public void setInsertedTs(Date insertedTs) {
		this.insertedTs = insertedTs;
	}

	public Date getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", insertedTs=" + insertedTs + ", modifiedTs=" + modifiedTs + "]";
	}
}
