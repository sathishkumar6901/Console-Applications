package com.zsgs.coursemanagement.admin;

import java.time.LocalDate;
import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public interface AdminModelCallBack {

	void addNewAdmin(String adminId, String adminName, String password);

	void addNewTrainer(String trainerId, String trainerName, String emailId, List<String> skills, String password, String phoneNumber);

	void addCourse(String courseCode, String courseName, String trainerId, LocalDate sDate, LocalDate eDate,
			LocalDate lDate, int days, List<String> courseContent, int totalSeats, float price);

	List<Course> getCourseList();

	List<Trainer> getTrainersList();	
}
