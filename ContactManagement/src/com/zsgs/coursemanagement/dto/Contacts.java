package com.zsgs.coursemanagement.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Contacts {
	private String name;
	private List<String> mobileNumber = new ArrayList<>();
	private String emailId;
	private LocalDate dob;
	
	public Contacts(String name, String emailId, String phoneNumber, LocalDate dob) {
		this.name = name;
		this.emailId = emailId;
		this.dob = dob;
		addMobileNumber(phoneNumber);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<String> getMobileNumber() {
		return mobileNumber;
	}

	public LocalDate getDob() {
		return dob;
	}
	
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public void addMobileNumber(String number) {
		mobileNumber.add(number);
	}
	public void removeNumber(String number) {
		mobileNumber.remove(number);
	}
}
