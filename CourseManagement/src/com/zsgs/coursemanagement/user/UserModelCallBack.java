package com.zsgs.coursemanagement.user;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.User;

public interface UserModelCallBack {

	List<Course> getEnrolledCourse(List<String> list);

	List<Course> getCourses();

	void addMycourse(User currUser, Course course);

}
