package com.zsgs.coursemanagement.addremove;

import java.time.LocalDate;

import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.repository.ContactRepository;

public class AddRemoveModel implements AddRemoveModelCallBack {
	private AddRemoveModelControllerCallBack addRemoveController;
	
	public AddRemoveModel(AddRemoveModelControllerCallBack addRemoveController) {
		this.addRemoveController = addRemoveController;
	}
	
	public void addNewContact(String name, String emailId, String phoneNumber, LocalDate dob) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact==null) {
			ContactRepository.getInstance().addNewContact(name,emailId,phoneNumber,dob);
			addRemoveController.addNewContactSuccess("New Contact added successfully!!!");
		}
		else
			addRemoveController.addNewContactFailure("The given name is already exist!!!");
		
	}
	
	public void addExistingContact(String phoneNumber, String name) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null){
			ContactRepository.getInstance().addExistingContact(contact,phoneNumber);
			addRemoveController.addExistingContactSuccess("The Contact added successfully with the existing Name!!!");
		}
		else
			addRemoveController.addExistingContactFailue("The given conatct name not found!!!");
	}
	
	public void deleteContact(String phoneNumber, String name) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null) {
			boolean delete = ContactRepository.getInstance().deleteContact(contact,phoneNumber);
			if(delete)
				addRemoveController.deleteContactSuccess("The given phone Number deleted from your contacts!!!");
			else
				addRemoveController.deleteContactSuccess("The given phone number not found!!!");
		}
		else
			addRemoveController.deleteContactFailure("The given contact Name not found!!!");
	}
	public interface AddRemoveModelControllerCallBack{

		void addNewContactSuccess(String message);

		void addNewContactFailure(String message);

		void addExistingContactSuccess(String message);
		
		void addExistingContactFailue(String message);
		
		void deleteContactSuccess(String message);
		
		void deleteContactFailure(String message);

		

	}
}
