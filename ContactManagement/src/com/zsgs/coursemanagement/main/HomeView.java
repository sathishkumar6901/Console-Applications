package com.zsgs.coursemanagement.main;

import java.util.List;
import java.util.Scanner;

import com.zsgs.coursemanagement.addremove.AddRemoveView;
import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.editsearch.EditSearchView;

public class HomeView implements HomeViewCallBack{
	private HomeControllerCallBack homeController;
	private Scanner  scanner = new Scanner(System.in);
	public HomeView() {
		homeController = new HomeController(this);
	}
	
	public static void main(String[] args) {
		HomeView homeView = new HomeView();
		homeView.mainMenu();
	}

	private void mainMenu() {
		System.out.println("------>Contact Management<------\n");
		boolean repeat = true;
		do {
			System.out.println("1.Add Contact \n2.Remove Contact \n3.Edit Contact \n4.Search Contact \n5.View Contact List \n6.Exit\n");
			System.out.print("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice) {
				case 1: {
						AddRemoveView addRemoveView = new AddRemoveView();
						addRemoveView.addContact();
						break;
						}		
				case 2: {
						AddRemoveView addRemoveView = new AddRemoveView();
						addRemoveView.deleteContact();
						break;
						}
				case 3: {
						EditSearchView editSearchView = new EditSearchView();
						editSearchView.editContact();
						break;
						}
				case 4: {
						EditSearchView editSearchView = new EditSearchView();
						editSearchView.searchContact();
						break;
						}
				case 5: viewAllContacts();
						break;
				case 6: repeat =false;
						break;
			}
		}while(repeat);
		System.out.println("\n----->Thank You <-----\n");
	}

	private void viewAllContacts() {
		homeController.viewAllContacts();
	}
	
	/**************Controller to View*******************/
	public void noContacts(String message) {
		System.err.println("\n"+message+"\n");
	}
	
	public void printContacts(List<Contacts> contacts) {
		System.out.println("\n----->Contact List<-----\n");
		System.out.printf("%-10s%-20s%-13s%s\n","Name","Email Id","DateOfBirth","Numbers");
		for(Contacts currContact:contacts) {
			System.out.printf("%-10s%-20s%-13s%s\n",currContact.getName(),currContact.getEmailId(),currContact.getDob(),currContact.getMobileNumber().toString());
		}
		System.out.println();
	}
}
