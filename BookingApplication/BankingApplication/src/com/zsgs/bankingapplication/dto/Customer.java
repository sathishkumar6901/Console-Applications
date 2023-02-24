package com.zsgs.bankingapplication.dto;

public class Customer {
	private String customerId;
	private String customerName;
	private String phoneNumber;
	private String aadharNumber;
	private int age;
	
	public Customer(String customerId,String customerName,String phoneNumber, String aadharNumber, int age)
	{
		this.customerId = customerId;
		this.customerName = customerName;
		this.setPhoneNumber(phoneNumber);
		this.aadharNumber = aadharNumber;
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public int getAge() {
		return age;
	}
}
