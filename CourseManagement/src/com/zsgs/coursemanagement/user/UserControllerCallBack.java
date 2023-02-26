package com.zsgs.coursemanagement.user;

import java.util.List;

import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.User;

public interface UserControllerCallBack {

	List<Course> getEnrolledCourse(List<String> list);

	List<Course> getCourses();

	void addmyCourse(User currUser, Course course);

}
