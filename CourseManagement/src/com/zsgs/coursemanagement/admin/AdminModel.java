package com.zsgs.coursemanagement.admin;

import java.time.LocalDate;
import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.repository.CourseRepository;

public class AdminModel implements AdminModelCallBack {
	private AdminModelControllerCallBack adminController;
	
	public AdminModel(AdminModelControllerCallBack adminController) {
		this.adminController = adminController;
	}
	public void addNewAdmin(String adminId, String adminName, String password) {
		CourseRepository.getInstance().addNewAdmin(adminId, adminName, password);
		adminController.addAdminSuccess("Admin "+adminName+" added successfully.\n\nAdmin Id is: "+adminId);
	}
	public void addCourse(String courseCode, String courseName, String trainerId, LocalDate startDate,
			LocalDate endDate, LocalDate lastDate, int days, List<String> courseContent, int totalSeats,float price) {
		CourseRepository.getInstance().addNewCourse(courseCode,courseName,trainerId,startDate,endDate,lastDate,days,courseContent,totalSeats,price);
		adminController.addCourseSuccess("New Course added successfully!!!");
	}
	public void addNewTrainer(String trainerId, String trainerName, String emailId, List<String> skills, String password, String phoneNumber) {
		CourseRepository.getInstance().addNewTrainer(trainerId, trainerName, emailId, skills, password, phoneNumber);
		adminController.addTrainerSuccess("Trainer "+trainerName+" added successfully.\n\nThe Trainer Id is:"+trainerId);
	}
	public List<Course> getCourseList(){
		return CourseRepository.getInstance().getCourseList();
	}
	public List<Trainer> getTrainersList(){
		return CourseRepository.getInstance().getTrainersList();
	}
	public interface AdminModelControllerCallBack{

		void addAdminSuccess(String message);

		void addTrainerSuccess(String message);

		void addCourseSuccess(String message);
		
	}
}
