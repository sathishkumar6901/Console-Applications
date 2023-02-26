package com.zsgs.coursemanagement.admin;

import java.util.ArrayList;
import java.util.List;

import com.zsgs.coursemanagement.Base;
import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public class AdminView extends Base implements AdminViewCallBack{
	private AdminControllerCallBack adminController;
	
	public AdminView() {
		adminController = new AdminController(this);
	}

	public void adminMenu(Admin currAdmin) {
		System.out.println("------>Admin Menu Page<------\n");
		boolean repeat = true;
		do {
			System.out.println("1.Add Admin \n2.Add Course \n3.Add Trainer \n4.View Course List \n5.View Trainer List \n6.Logout\n");
			System.out.print("Enter your choice:");
			char choice = getChoice();
			switch(choice) {
			case '1': addAdmin();
					  break;
			case '2': addCourse();
					  break;
			case '3': addTrainer();
					  break;
			case '4': viewCourseList();
					  break;
			case '5': viewTrainerList();
					  break;
			case '6': repeat=false;
					  break;
			default: System.out.println("Please enter a valid option..\n");
			}
		}while(repeat);
		
	}
	private void viewTrainerList() {
		List<Trainer> trainers = adminController.getTrainersList();
		if(trainers.isEmpty())
			System.out.println("\nThere is no Trainer in our Academy\n");
		else {
			System.out.println("\n------>Trainers List<------\n");
			System.out.printf("%-12s%-14s%-20s%-14s%-2s\n","Trainer Id","Trainer Name","Email Id","Phone Number","Skills");
			for(Trainer trainer:trainers) {
				System.out.printf("%-12s%-14s%-20s%-14s%-2s\n", trainer.getTrainerId(),trainer.getTrainerName(),trainer.getEmailId(),
						trainer.getPhoneNumber(),trainer.getSkillSet().toString());
			}
		}	
	}

	private void viewCourseList() {
		List<Course> courses = adminController.getCourseList();
		if(courses.isEmpty())
			System.out.println("\nThere is no Course in our Academy!!!\n");
		else {
			System.out.println("\n------>Course List<------\n");
			System.out.printf("%-13s%-13s%-12s%-12s%-10s%-10s%-13s%-17s%s\n","Course Code","Course Name","Trainer Id","Start Date",
					"End Date","Duration","Total Seats","Available Seats","Price");
			for(Course course: courses) {
				System.out.printf("%-13s%-13s%-12s%-12tD%-10tD%d weeks   %-13d%-17d%.2f\n",course.getCourseCode(),course.getCourseName(),course.getTrainerId(),
		course.getStartDate(),course.getEndDate(),course.getDuration(),course.getTotalSeats(),course.getAvailableSeats(),course.getPrice());
			}
			System.out.println();
		}
	}

	private void addAdmin() {
		System.out.println("\n------>Add New Admin Page<------\n");
		System.out.print("Enter Admin Name:");
		String adminName = getName();
		System.out.println("Enter your password:");
		String password = getPassword();
		adminController.addNewAdmin(adminName, password);
	}
	private void addCourse() {
		System.out.println("\n------>Add New Course Page<------\n");
		System.out.print("Enter Course Name:");
		String courseName = getName();
		System.out.print("Enter Trainer Id:");
		String trainerId = getId();
		System.out.print("Enter Start Date of the Course:");
		String startDate = getDate();
		System.out.print("Enter End Date of the Course:");
		String endDate = getDate();
		System.out.print("Enter Last date to apply for Course:");
		String lastDate = getDate();
		int weeks = adminController.calculateWeeks(startDate,endDate);
		List<String> courseContent = new ArrayList<>();
		for(int i=1;i<=weeks;i++) {
			System.out.printf("Week %d:",i);
			courseContent.add(getContent());
		}
		System.out.print("Enter number of seats of the Course:");
		int totalSeats = getSeats();
		System.out.print("Enter price of course:");
		float price = getPrice();
		adminController.addCourse(courseName,trainerId,startDate,endDate,lastDate,weeks,courseContent,totalSeats,price);
		
	}
	private void addTrainer() {
		System.out.println("\n------>Add New Trainer Page<------\n");
		System.out.print("Enter Trainer Name:");
		String trainerName = getName(); 
		System.out.print("Enter email Id:");
		String emailId = getEmailId();
		System.out.print("Enter Trainer Password:");
		String password = getPassword();
		System.out.print("Enter your Phone Number");
		String phoneNumber = getPhoneNumber();
		System.out.print("Enter your skills(separated by comma(,)):");
		String skillSets = getSkillSet();
		adminController.addNewTrainer(trainerName, emailId, skillSets,password, phoneNumber);
	}
	
	/*****************Controller to Model**************/
	public void NameError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void emailIdError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void phoneNumberError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addAdminSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addCourseSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addTrainerSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
}
