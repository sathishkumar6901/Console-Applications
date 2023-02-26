package com.zsgs.coursemanagement.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.AdminCredentials;
import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.dto.TrainerCredentials;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.dto.UserCredentials;

public class CourseRepository {

		private static CourseRepository courseDbInstance;
		
		private List<AdminCredentials> adminCredentials = new ArrayList<>();
		private List<TrainerCredentials> trainerCredentials = new ArrayList<>();
		private List<UserCredentials> userCredentials = new ArrayList<>();
		private List<Course> courses = new ArrayList<>();
		
		public static CourseRepository getInstance()
		{
			if(courseDbInstance==null)
			{
				courseDbInstance = new CourseRepository();
				courseDbInstance.addAdmin();
			}
			return courseDbInstance;
		}

		private void addAdmin() {
			adminCredentials.add(new AdminCredentials("SK0001","SathishKrishna","zsgs"));
		}

		public Admin getValidAdmin(String adminId, String password) {
			for(AdminCredentials admin:adminCredentials)
				if(admin.getAdminId().equals(adminId) && admin.getPassword().equals(password))
					return admin;
			return null;
		}

		public Trainer getValidTrainer(String trainerId, String password) {
			for(TrainerCredentials trainer: trainerCredentials)
				if(trainer.getTrainerId().equals(trainerId) && trainer.getPassword().equals(password))
					return trainer;
			return null;
		}

		public User getValidUser(String userId, String password) {
			for(UserCredentials user: userCredentials)
				if(user.getUserId().equals(userId) && user.getPassword().equals(password))
					return user;
			return null;
		}

		public User addNewUser(String userId, String userName, String emailId, String password, String phoneNumber) {
			UserCredentials currUser = new UserCredentials(userId,userName,password,emailId,phoneNumber);
			userCredentials.add(currUser);
			return currUser;
		}

		public User isNewUser(String emailId) {
			for(UserCredentials user: userCredentials)
				if(user.getEmailId().equals(emailId))
					return user;
			return null;
		}

		public void addNewAdmin(String adminId, String adminName, String password) {
			adminCredentials.add(new AdminCredentials(adminId, adminName, password));
		}

		public void addNewCourse(String courseCode, String courseName, String trainerId,LocalDate startDate, 
				LocalDate endDate, LocalDate lastDate, int days, List<String> courseContent,int totalSeats,float price) {
			courses.add(new Course(courseCode,courseName,trainerId,startDate,endDate,lastDate,days,courseContent,totalSeats,price));
		}

		public void addNewTrainer(String trainerId, String trainerName, String emailId, List<String> skills, String password, String phoneNumber) {
			trainerCredentials.add(new TrainerCredentials(trainerId,trainerName,emailId,skills,password,phoneNumber));
		}

		public List<Course> getTrainerCourse(String trainerId) {
			List<Course> currCourse = new ArrayList<>();
			for(Course course:courses)
				if(course.getTrainerId().equals(trainerId))
					currCourse.add(course);
			return currCourse;
		}

		public void addSkills(Trainer currTrainer, String[] skills) {
			for(String skill:skills){
				currTrainer.addSkillSet(skill);
			}
			
		}

		public List<Course> getUserEnrolledCourse(List<String> list) {
			List<Course> courseList =new ArrayList<>();
			for(Course course:courses)
				if(!list.isEmpty() && list.contains(course.getCourseCode()))
					courseList.add(course);
			return courseList;
		}

		public List<Course> getCourses() {
			return courses;
		}

		public User setUserPassword(String otp, String emailId) {
			for(UserCredentials currUser:userCredentials)
			{
				if(currUser.getEmailId().equals(emailId))
				{
					currUser.setPassword(otp);
					return currUser;
				}
			}
			return null;
		}

		public Trainer setTrainerPassword(String otp, String emailId) {
			for(TrainerCredentials currTrainer:trainerCredentials)
			{
				if(currTrainer.getEmailId().equals(emailId))
				{
					currTrainer.setPassword(otp);
					return currTrainer;
				}
			}
			return null;
		}

		public User setNewUserPassword(String emailId, String otp, String password) {
			for(UserCredentials currUser:userCredentials)
			{
				if(currUser.getEmailId().equals(emailId) && currUser.getPassword().equals(otp))
				{
					currUser.setPassword(password);
					return currUser;
				}
			}
			return null;
		}

		public Trainer setNewTrainerPassword(String emailId, String otp, String password) {
			for(TrainerCredentials currTrainer:trainerCredentials)
			{
				if(currTrainer.getEmailId().equals(emailId) && currTrainer.getPassword().equals(otp))
				{
					currTrainer.setPassword(password);
					return currTrainer;
				}
			}
			return null;
		}

		public List<Course> getCourseList() {
			return courses;
		}

		public List<Trainer> getTrainersList() {
			List<Trainer> trainer = new ArrayList<>();
			for(TrainerCredentials currTrainer: trainerCredentials) {
				trainer.add(currTrainer);
			}
			return trainer;
		}

		public void addMycourse(User currUser, String courseCode) {
			currUser.addCourseCode(courseCode);
		}

		public void setBookedTickets(Course course) {
			course.setBookedSeats(1);
		}
		
}
