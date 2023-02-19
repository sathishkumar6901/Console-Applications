package com.zsgs.flightticketbooking.dto;

public class AdminCredentials extends Admin{
	private String password;
	public AdminCredentials(int adminId,String adminName,String password)
	{
		super.setAdminName(adminName);
		super.setAdminId(adminId);
		this.password=password;
	}
	
	public String getAdminPassword()
	{
		return password;
	}
}
