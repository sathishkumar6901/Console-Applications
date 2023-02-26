package com.zsgs.coursemanagement.user;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.user.UserModel.UserModelControllerCallBack;

public class UserController implements UserControllerCallBack, UserModelControllerCallBack {
	private UserViewCallBack userView;
	private UserModelCallBack userModel;
	
	public UserController(UserViewCallBack userView) {
		this.userView = userView;
		this.userModel = new UserModel(this);
	}
	
	public List<Course> getEnrolledCourse(List<String> list){
		return userModel.getEnrolledCourse(list);
	}
	
	public List<Course> getCourses(){
		return userModel.getCourses();
	}
	public void addmyCourse(User currUser, Course course) {
		userModel.addMycourse(currUser,course);
	}
	/*******************model to controller*****************/
	public void paymentSuccess(String message) {
		userView.paymentSuccess(message);
	}
}
