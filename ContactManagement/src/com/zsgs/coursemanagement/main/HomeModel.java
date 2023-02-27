package com.zsgs.coursemanagement.main;

import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.repository.ContactRepository;

public class HomeModel implements HomeModelCallBack {
	private HomeModelControllerCallBack homeController;
	
	public HomeModel(HomeModelControllerCallBack homeController) {
		this.homeController = homeController;
	}
	public void viewAllContacts() {
		List<Contacts> contacts = ContactRepository.getInstance().getAllContacts();
		if(contacts.isEmpty())
			homeController.noContacts("There is no contacts in your Phone Book");
		else {
			contacts.sort((c1,c2)->c1.getName().compareTo(c2.getName()));
			homeController.printContacts(contacts);
		}
	}
	
	public interface HomeModelControllerCallBack{

		void noContacts(String message);

		void printContacts(List<Contacts> contacts);
		
	}
}
