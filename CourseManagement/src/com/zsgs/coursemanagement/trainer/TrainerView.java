package com.zsgs.coursemanagement.trainer;

import java.util.List;

import com.zsgs.coursemanagement.Base;
import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public class TrainerView extends Base implements TrainerViewCallBack{
	private TrainerControllerCallBack trainerController;
	
	public TrainerView() {
		trainerController = new TrainerController(this);
	}
	public void trainerMenu(Trainer currTrainer) {
		boolean repeat =true;
		System.out.println("\n------>Trainer Menu<------\n");
		do {
			System.out.println("1.View Profile \n2.My Courses \n3.Add Skills \n4.Logout\n");
			System.out.print("Enter your Choice:");
			char choice = getChoice();
			switch(choice) {
				case '1': viewProfile(currTrainer);
						  break;
				case '2': myCourses(currTrainer.getTrainerId());
						  break;
				case '3': addSkills(currTrainer);
						  break;
				case '4': repeat = false;
						  break;
				default: System.out.println("Enter a Valid Choice\n");
			}
		}while(repeat);
	}
	
	private void viewProfile(Trainer currTrainer) {
		System.out.println("\n------>PERSONAL DETAILS<------\n");
		System.out.printf("%-14s : %s\n","Trainer Id",currTrainer.getTrainerId());
		System.out.printf("%-14s : %s\n","Trainer Name",currTrainer.getTrainerName());
		System.out.printf("%-14s : %s\n","Email Id",currTrainer.getEmailId());
		System.out.printf("%-14s : %s\n","Phone Number",currTrainer.getPhoneNumber());
		System.out.printf("%-14s : %s\n","Skills",currTrainer.getSkillSet().toString());
		System.out.println();
	}
	private void myCourses(String trainerId) {
		List<Course> courseList = trainerController.myCourseList(trainerId);
		if(courseList.isEmpty())
			System.out.println("You are not assigned to any course\n");
		else {
			System.out.printf("%-13s%-13s%-13s%-10s%-10s\n","Course Code","Course Name","Start Date","End Date","Duration");
			for(Course curr: courseList) {
				System.out.printf("%-13s%-13s%-13tD%-10tD%d weeks\n",curr.getCourseCode(),curr.getCourseName(),curr.getStartDate(),curr.getEndDate(),curr.getDuration());
			}
			System.out.println();
		}
	}
	private void addSkills(Trainer currTrainer) {
		System.out.print("Enter the skill you want to update(separated by comma(,)):");
		String skill = getSkillSet();
		trainerController.addSkill(currTrainer,skill);
	}
	
	/*****************Controller to view***********/
	public void addSkillSuccess(String message) {
		System.out.println(message+"\n");
	}
	
}
