package com.zsgs.coursemanagement.admin;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;

public interface AdminControllerCallBack {

	void addNewAdmin(String adminName, String password);

	int calculateWeeks(String startDate, String endDate);

	void addNewTrainer(String trainerName, String emailId, String skillSets,String password, String phoneNumber);

	void addCourse(String courseName, String trainerId, String startDate, String endDate, String lastDate, int weeks,
			List<String> courseContent, int totalSeats, float price);

	List<Course> getCourseList();

	List<Trainer> getTrainersList();

}
