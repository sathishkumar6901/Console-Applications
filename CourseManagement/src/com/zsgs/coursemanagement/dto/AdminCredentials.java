package com.zsgs.coursemanagement.dto;

public class AdminCredentials extends Admin{
	private String password;
	
	public AdminCredentials(String adminId, String adminName,String password) {
		super(adminId, adminName);
		this.password =password;
	}

	public String getPassword() {
		return password;
	}
}
