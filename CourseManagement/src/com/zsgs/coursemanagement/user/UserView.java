package com.zsgs.coursemanagement.user;

import java.time.LocalDate;
import java.util.List;

import com.zsgs.coursemanagement.Base;
import com.zsgs.coursemanagement.dto.Course;
import com.zsgs.coursemanagement.dto.User;

public class UserView extends Base implements UserViewCallBack{
	private UserControllerCallBack userController;
	
	public UserView() {
		userController = new UserController(this);
	}

	public void userMenu(User currUser) {
		System.out.println("\n------>User Menu<------\n");
		boolean repeat = true;
		do {
			System.out.println("1.View Profile \n2.My Courses \n3.View Course List \n4.Logout\n");
			System.out.println("Enter your choice:");
			char choice = getChoice();
			switch(choice) {
				case '1': viewProfile(currUser);
						  break;
				case '2': myCourses(currUser.getCourseList());
						  break;
				case '3': viewCourseList(currUser);
						  break;
				case '4': repeat=false;
						  break;
				default: System.out.println("Please enter a valid choice\n");
			}
			
		}while(repeat);
		
	}
	private void viewProfile(User currUser) {
		System.out.println("\n------>PERSONAL DETAILS<------\n");
		System.out.printf("%-13s : %s\n","User Id",currUser.getUserId());
		System.out.printf("%-13s : %s\n","User Name", currUser.getUserName());
		System.out.printf("%-13s : %s\n","Email Id", currUser.getEmailId());
		System.out.printf("%-13s : %s\n","Phone Number",currUser.getPhoneNumber());
		System.out.println();
	}
	
	private void myCourses(List<String> list) {
		List<Course> enrolledCourse = userController.getEnrolledCourse(list);
		if(enrolledCourse.isEmpty())
			System.out.println("you are not enrolled any course till now!!!\n");
		else {
			System.out.printf("%-13s%-13s%-13s%-10s%-10s\n","Course Code","Course Name","Start Date","End Date","Duration");
			for(Course curr: enrolledCourse) {
				System.out.printf("%-13s%-13s%-13tD%-10tD%d weeks\n",curr.getCourseCode(),curr.getCourseName(),curr.getStartDate(),curr.getEndDate(),curr.getDuration());
			}
			System.out.println();
		}
	}
	
	private void viewCourseList(User currUser) {
		List<Course> courses = userController.getCourses();
		LocalDate date = LocalDate.now();
		System.out.printf("%-13s%-13s%-10s%-17s%s\n","Course Code","Course Name","Duration","Available Seats","Price");
		for(Course course:courses) {
			if(date.isBefore(course.getLastDateToApply().plusDays(1)) && course.getAvailableSeats()>0) {
				System.out.printf("%-13s%-13s%d weeks   %-17d%.2f\n",course.getCourseCode(),course.getCourseName(),course.getDuration(),
						course.getAvailableSeats(),course.getPrice());
			}
		}
		System.out.print("Do you want to see any course details(yes/no):");
		String willing = getName();
		if(willing.equals("yes"))
			courseDetails(currUser,courses);
		else
			System.out.println("\nThank You for visit our courses\n");
	}
	private void courseDetails(User currUser, List<Course> courses) {
		System.out.print("Enter the course Code you want to Enroll:");
		String courseCode = getName();
		for(Course course:courses) {
			if(course.getCourseCode().equals(courseCode)) {
				System.out.printf("%-18s : %s\n","Course Code",course.getCourseCode());
				System.out.printf("%-18s : %s\n","Course Name",course.getCourseName());
				System.out.printf("%-18s : %s\n","Starting Date",course.getStartDate());
				System.out.printf("%-18s : %s\n","End Date",course.getEndDate());
				System.out.printf("%-18s : %s\n","Last Date to Apply",course.getLastDateToApply());
				System.out.printf("%-18s : %s\n","Duration",course.getDuration());
				System.out.printf("%-18s : %s\n","Price",course.getPrice());
				System.out.printf("\n------>Course Contents<------\n");
				for(int i=1;i<=course.getDuration();i++)
				{
					System.out.println("Week"+(i-1)+" : "+course.getContents().get(i-1));
				}
				System.out.print("Do you want to buy this Course(yes/no),which cost is:"+course.getPrice());
				String toBuy = getName();
				if(toBuy.equals("yes")) {
					userController.addmyCourse(currUser,course);
				}
				else {
					System.out.println("\n---->Thanks for visit our Course Data<----\n");
				}
				break;
			}
		}
	}

	/************Controller to View**************/
	public void welcomeMessage() {
		System.out.println("\nThank You for visit our courses\n");
	}
	public void paymentSuccess(String message) {
		System.out.println(message+"\n");
	}
}
