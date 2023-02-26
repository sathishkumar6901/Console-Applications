package com.zsgs.coursemanagement.dto;

import java.util.List;

public class TrainerCredentials extends Trainer {

	private String password;
	
	public TrainerCredentials(String trainerId,String trainerName,String emailId,List<String> skills,String password,String phoneNumber)
	{
		super(trainerId,trainerName,emailId,skills,phoneNumber);
		this.password = password; 
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
