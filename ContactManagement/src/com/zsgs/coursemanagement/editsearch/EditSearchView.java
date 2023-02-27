package com.zsgs.coursemanagement.editsearch;

import java.util.List;
import java.util.Scanner;

import com.zsgs.coursemanagement.dto.Contacts;

public class EditSearchView implements EditSearchViewCallBack{
	private EditSearchControllerCallBack editSearchController;
	private Scanner scanner = new Scanner(System.in);
	public EditSearchView() {
		editSearchController = new EditSearchController(this);
	}

	public void editContact() {
		System.out.println("\n----->Edit Contact Page<-----\n");
		System.out.print("Enter the name of the contact:");
		String name = scanner.next();
		boolean repeat = true;
		do {
			System.out.println("\n1.Edit Name \n2.Edit Phone Number \n3.Edit EmailId \n4.Edit Date of Birth \n5.exit\n");
			System.out.print("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1: editName(name);
						break;
				case 2: editPhoneNumber(name);
						break;
				case 3: editEmailId(name);
						break;
				case 4: editDOB(name);
						break;
				case 5: repeat = false;
						break;
				default:System.out.println("\nEnter a valid choice\n");
			}
		}while(repeat);	
	}
	public void searchContact() {
		System.out.println("\n----->Search Contact Page<-----\n");
		System.out.println("1.Search by Name \n2.Search by Number \n3.Exit\n");
		System.out.print("Enter your choice:");
		int choice = scanner.nextInt();
		switch(choice) {
			case 1: searchByName();
					break;
			case 2: searchByNumber();
					break; 
		}		
	}
	private void searchByNumber() {
		System.out.print("Enter the Phone Number, you want to search:");
		String phoneNumber = scanner.next();
		
		editSearchController.searchByPhoneNumber(phoneNumber);
	}

	private void searchByName() {
		System.out.print("Enter the name, you want to search:");
		String name = scanner.next();
		
		editSearchController.searchByName(name);
	}

	private void editName(String name) {
		System.out.print("\nEnter The new name, you want to change:");
		String newName = scanner.next();
		editSearchController.editName(name,newName);
	}
	private void editPhoneNumber(String name) {
		System.out.print("Enter the current Phone Number:");
		String phoneNumber = scanner.next();
		System.out.print("\nEnter the Phone Number, you want to change:");
		String newPhoneNumber = scanner.next();
		
		editSearchController.editPhoneNumber(name,phoneNumber,newPhoneNumber);
	}
	private void editEmailId(String name) {
		System.out.print("Enter the EmailId, you want to change:");
		String emailId = scanner.next();
		
		editSearchController.editEmailId(name,emailId);
	}
	private void editDOB(String name) {
		System.out.print("Enter the Date of Birth of the Contact, you want to change(yyyy-mm-dd):");
		String date = scanner.next();
		
		editSearchController.editDOB(name,date);
		
	}	
	/**********Controller to View*************/
	public void editNameSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void phoneNumberError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void editPhoneNumberSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void emailIdError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void editEmailIdSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void editDOBSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void nameNotFoundError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void numberNotFoundError(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void noContactFound(String message) {
		System.err.println("\n"+message+"\n");
	}
	public void printFoundContacts(List<Contacts> contact) {
		System.out.println("\n----->Contact List<-----\n");
		System.out.printf("%-10s%-20s%-13s%s\n","Name","Email Id","DateOfBirth","Numbers");
		for(Contacts currContact:contact) {
			System.out.printf("%-10s%-20s%-13s%s\n",currContact.getName(),currContact.getEmailId(),currContact.getDob(),currContact.getMobileNumber().toString());
		}
		System.out.println();
	}
}
