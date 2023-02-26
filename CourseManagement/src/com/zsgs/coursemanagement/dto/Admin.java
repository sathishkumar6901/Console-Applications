package com.zsgs.coursemanagement.dto;

public class Admin {
	private String adminId;
	private String adminName;
	
	public Admin(String adminId, String adminName)
	{
		this.adminId = adminId;
		this.adminName = adminName;
	}

	public String getAdminId() {
		return adminId;
	}

	public String getAdminName() {
		return adminName;
	}
}
