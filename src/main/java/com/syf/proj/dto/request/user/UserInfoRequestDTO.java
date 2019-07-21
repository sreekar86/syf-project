package com.syf.proj.dto.request.user;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author sreekar
 *
 */
public class UserInfoRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = 5, max = 10)
	@Pattern(regexp = "^[A-Za-z0-9]*$")
	private String userName;

	@NotEmpty
	private String password;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	private String gender;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String phoneNumber;

	public UserInfoRequestDTO(@NotEmpty @Size(min = 5, max = 10) @Pattern(regexp = "^[A-Za-z0-9]*$") String userName,
			@NotEmpty String password, @NotEmpty String firstName, @NotEmpty String lastName, String gender,
			@Email @NotEmpty String email, @NotEmpty String phoneNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.phoneNumber = phoneNumber;
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

	@Override
	public String toString() {
		return "UserInfoRequest [userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ "]";
	}

}
