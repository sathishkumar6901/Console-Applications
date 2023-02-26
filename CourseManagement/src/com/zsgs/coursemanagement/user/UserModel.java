package com.zsgs.coursemanagement.user;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.repository.CourseRepository;

public class UserModel implements UserModelCallBack {
	private UserModelControllerCallBack userController;
	
	public UserModel(UserModelControllerCallBack userController) {
		this.userController = userController;
	}
	
	public List<Course> getEnrolledCourse(List<String> list){
		return CourseRepository.getInstance().getUserEnrolledCourse(list);
	}
	
	public List<Course> getCourses(){
		return CourseRepository.getInstance().getCourses();
	}
	
	public void addMycourse(User currUser, Course course) {
		CourseRepository.getInstance().addMycourse(currUser,course.getCourseCode());
		CourseRepository.getInstance().setBookedTickets(course);
		userController.paymentSuccess("Your payment "+course.getPrice()+" successfully completed!!!");
	}
	public interface UserModelControllerCallBack{

		void paymentSuccess(String message);
		
	}
}
