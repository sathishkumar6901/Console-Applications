package com.zsgs.coursemanagement.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String userName;
	private String emailId;
	private String phoneNumber;
	private List<String> courseList = new ArrayList<>();
	
	public User(String userId, String userName, String password, String emailId, String phoneNumber) {
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	public void addCourseCode(String courseCode) {
		courseList.add(courseCode);
	}
}
