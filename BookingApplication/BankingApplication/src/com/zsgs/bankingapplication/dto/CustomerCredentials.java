package com.zsgs.bankingapplication.dto;

public class CustomerCredentials extends Customer {
	private String password;

	public CustomerCredentials(String customerId, String customerName,String password, int age,String phoneNumber, String aadharNumber) {
		super(customerId, customerName, phoneNumber, aadharNumber, age);
		this.setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
