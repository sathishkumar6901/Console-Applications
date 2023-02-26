package com.zsgs.coursemanagement.dto;

import java.util.List;

public class Trainer {

	private String trainerId;
	private String trainerName;
	private String emailId;
	private List<String> skillSet;
	private String phoneNumber;
	
	public Trainer(String trainerId, String trainerName, String emailId, List<String> skillSet, String phoneNumber) {
		this.trainerId = trainerId;
		this.trainerName =trainerName;
		this.emailId = emailId;
		this.skillSet = skillSet;
		this.phoneNumber = phoneNumber;
	}
	public String getTrainerId() {
		return trainerId;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public String getEmailId() {
		return emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public List<String> getSkillSet() {
		return skillSet;
	}
	public void addSkillSet(String skill) {
		skillSet.add(skill);
	}
}
