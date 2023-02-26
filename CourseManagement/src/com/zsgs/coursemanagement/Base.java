package com.zsgs.coursemanagement;

import java.util.Scanner;

public abstract class Base {
	private Scanner scanner = new Scanner(System.in);
	
	protected char getChoice(){
		return scanner.next().charAt(0);
	}
	protected String getId() {
		return scanner.next();
	}
	protected String getPassword() {
		return scanner.next();
	}
	protected String getName() {
		return scanner.next();
	}
	protected String getEmailId() {
		return scanner.next();
	}
	protected String getPhoneNumber() {
		return scanner.next();
	}
	protected String getDate() {
		return scanner.next();
	}
	protected String getContent() {
		return scanner.next();
	}
	protected int getSeats() {
		return scanner.nextInt();
	}
	protected String getSkillSet() {
		return scanner.next();
	}
	protected float getPrice() {
		return scanner.nextFloat();
	}
}
