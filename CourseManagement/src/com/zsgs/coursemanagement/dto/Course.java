package com.zsgs.coursemanagement.dto;

import java.time.LocalDate;
import java.util.List;

public class Course {
	
	private String courseCode;
	private String courseName;
	private String trainerId;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate lastDateToApply;
	private int duration;
	private List<String> contents;
	private int totalSeats;
	private float price;
	private int bookedSeats;
	
	public Course(String courseCode, String courseName, String trainerId, LocalDate startDate,LocalDate endDate, LocalDate lastDateToApply,
			int duration, List<String> contents, int totalSeats,float price) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.trainerId = trainerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lastDateToApply = lastDateToApply;
		this.duration = duration;
		this.contents = contents;
		this.totalSeats = totalSeats;
		this.price = price;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(String trainerId) {
		this.trainerId = trainerId;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}	
	public LocalDate getLastDateToApply() {
		return lastDateToApply;
	}
	public void setLastDateToApply(LocalDate lastDateToApply) {
		this.lastDateToApply = lastDateToApply;
	}
	public List<String> getContents() {
		return contents;
	}
	public int getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(int bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public int getAvailableSeats()
	{
		return totalSeats - getBookedSeats();
	}
	public int getDuration() {
		return duration;
	}
	public float getPrice() {
		return price;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
}
