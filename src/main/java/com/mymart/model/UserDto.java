package com.mymart.model;

public class UserDto {
	private String name;
	private String email;
	private String password;
	private String role;
	private String confirmPassword;
    private String contactNo;
	
	
	

//	public UserDto(String email, String password, String role, String confirmPassword, String contactNo) {
//		super();
//		this.email = email;
//		this.password = password;
//		this.role = role;
//		this.confirmPassword = confirmPassword;
//		this.contactNo = contactNo;
//	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	
	
}