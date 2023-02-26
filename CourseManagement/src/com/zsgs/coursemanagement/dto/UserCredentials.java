package com.zsgs.coursemanagement.dto;

public class UserCredentials extends User {
	private String password;
	
	public UserCredentials(String userId,String userName,String password,String emailId,String phoneNumber)
	{
		super(userId,userName,password,emailId,phoneNumber);
		this.setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
