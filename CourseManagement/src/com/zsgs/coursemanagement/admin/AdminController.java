package com.zsgs.coursemanagement.admin;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.coursemanagement.admin.AdminModel.AdminModelControllerCallBack;
import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public class AdminController implements AdminControllerCallBack, AdminModelControllerCallBack {
	private AdminViewCallBack adminView;
	private AdminModelCallBack adminModel;
	private static int aId = 2, cId = 1, tId = 1;
	
	public AdminController(AdminViewCallBack adminView) {
		this.adminView = adminView;
		this.adminModel = new AdminModel(this);
	}
	public void addNewAdmin(String adminName, String password) {
		if(!adminName.matches("[A-Za-z]+"))	
			adminView.NameError("Please enter a valid UserName");
		else {
			String adminId = "SK000"+aId;
			aId++;
			adminModel.addNewAdmin(adminId, adminName, password);
		}
	}
	public int calculateWeeks(String startDate, String endDate) {
		LocalDate sDate = changeStringToDate(startDate);
		LocalDate eDate = changeStringToDate(endDate);
		Period period = Period.between(sDate, eDate);
		long months = Math.abs(period.getMonths());
		long days = Math.abs(period.getDays());
		return (int)((months*30)+days)/7;
	}
	private LocalDate changeStringToDate(String date) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd");
		return LocalDate.parse(date, dateFormat);
	}
	
	public void addCourse(String courseName, String trainerId, String startDate, String endDate,
			String lastDate, int days, List<String> courseContent, int totalSeats,float price) {
		LocalDate sDate = changeStringToDate(startDate);
		LocalDate eDate = changeStringToDate(endDate);
		LocalDate lDate = changeStringToDate(lastDate);
		String courseCode = "CS000"+cId;
		cId++;
		adminModel.addCourse(courseCode,courseName,trainerId,sDate,eDate,lDate,days,courseContent,totalSeats,price);
	}
	public void addNewTrainer(String trainerName, String emailId, String skillSets, String password, String phoneNumber) {
		if(!trainerName.matches("[A-Za-z]+"))
			adminView.NameError("Please enter a valid UserName");
		else if(!emailId.matches("^[A-Za-z0-9]+@(.+)$"))
			adminView.emailIdError("Please enter a valid EmailId");
		else if(!phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			adminView.phoneNumberError("Please enter a valid phone number");
		else
		{
			String[] temp =skillSets.split(","); 
			List<String> skills = new ArrayList<>();
			for(int i=0;i<temp.length;i++) {
				skills.add(temp[i]);
			}
			String trainerId = "TR000"+tId;
			tId++;
			adminModel.addNewTrainer(trainerId,trainerName,emailId,skills,password,phoneNumber);
		}
	}
	public List<Course> getCourseList(){
		return adminModel.getCourseList();
	}
	public List<Trainer> getTrainersList(){
		return adminModel.getTrainersList();
	}
	/**********model to controller***********/
	public void addAdminSuccess(String message) {
		adminView.addAdminSuccess(message);
	}
	public void addCourseSuccess(String message) {
		adminView.addCourseSuccess(message);
	}
	public void addTrainerSuccess(String message) {
		adminView.addTrainerSuccess(message);
	}
}
