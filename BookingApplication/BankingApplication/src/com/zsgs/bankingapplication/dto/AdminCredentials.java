package com.zsgs.bankingapplication.dto;

public class AdminCredentials extends Admin{
	private String password;
	public AdminCredentials(String id,String name,String password)
	{
		super(id,name);
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

}
