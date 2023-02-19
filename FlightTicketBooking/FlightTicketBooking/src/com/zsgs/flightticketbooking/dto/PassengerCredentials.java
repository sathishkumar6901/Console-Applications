package com.zsgs.flightticketbooking.dto;

public class PassengerCredentials extends Passenger{
	private String password;
	public PassengerCredentials(String passengerName,String password,int age,String emailId,long phoneNumber)
	{
		super.setPassengerName(passengerName);
		super.setEmailId(emailId);
		super.setAge(age);
		super.setPhoneNumber(phoneNumber);
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
}
