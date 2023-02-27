package com.zsgs.coursemanagement.addremove;

import java.util.Scanner;

public class AddRemoveView implements AddRemoveViewCallBack{
	private AddRemoveControllerCallBack addRemoveController;
	private Scanner scanner = new Scanner(System.in);
	public AddRemoveView() {
		addRemoveController = new AddRemoveController(this);
	}

	public void addContact() {
		System.out.println("\n----->Add Contact Page<-----\n");
		System.out.print("Enter the Phone Number:");
		String phoneNumber = scanner.next();
		System.out.println("\n1.Add New Contact \n2.Add Existing Contact \n");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1: addNewContact(phoneNumber);
					break;
			case 2: addExistingContact(phoneNumber);
					break;
			default: System.out.println("\nPlease enter a Valid choice");
		}
	}

	public void deleteContact() {
		System.out.println("\n----->Delete Contact Page<-----\n");
		System.out.print("Enter the name of the contact:");
		String name = scanner.next();
		System.out.print("Enter the Number you want to delete:");
		String phoneNumber = scanner.next();
		
		addRemoveController.deleteContact(name,phoneNumber);
	}
	private void addNewContact(String phoneNumber) {
		System.out.print("Enter the Name of the Contact:");
		String name = scanner.next();
		System.out.print("Enter the Email Id of tha Contact:");
		String emailId = scanner.next();
		System.out.print("Enter the Date of Birth of the Contact(yyyy-mm-dd):");
		String date = scanner.next();
		
		addRemoveController.addNewContact(name,emailId,phoneNumber,date);
	}
	private void addExistingContact(String phoneNumber) {
		System.out.print("Enter name of the Contact:");
		String name = scanner.next();
		
		addRemoveController.addExistingContact(phoneNumber,name);
	}
	
	/************Controller to View**************/
	public void emailIdError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void phoneNumberError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void addNewContactSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addNewContactFailure(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void addExistingContactSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addExistingContactFailue(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void deleteContactSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void deleteContactFailure(String message) {
		System.err.println("\n"+message+"\n");
	}
}
