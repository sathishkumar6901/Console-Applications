package com.zsgs.coursemanagement.editsearch;

import java.time.LocalDate;
import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.repository.ContactRepository;

public class EditSearchModel implements EditSearchModelCallBack {
	private EditSearchModelControllerCallBack editSearchController;
	
	public EditSearchModel(EditSearchModelControllerCallBack editSearchController) {
		this.editSearchController = editSearchController;
	}	
	
	public void editName(String name, String newName) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null) {
			ContactRepository.getInstance().editName(contact,name,newName);
			editSearchController.editNameSuccess("The Contact Name changed successfully!!!");
		}
		else
			editSearchController.nameNotFoundError("The given Contact Name not exist...");
	}
	public void editPhoneNumber(String name, String phoneNumber, String newPhoneNumber) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null) {
			boolean changePhone = ContactRepository.getInstance().changePhoneNumber(contact,phoneNumber,newPhoneNumber);
			if(changePhone)
				editSearchController.editPhoneNumberSuccess("The Phone Number changed successfully!!!");
			else
				editSearchController.numberNotFoundError("The Phone Number not exist with the given name!!!");
		}
		else
			editSearchController.nameNotFoundError("The given Contact Name not exist...");
	}
	public void editEmailId(String name, String emailId) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null) {
			ContactRepository.getInstance().editEmailId(contact,emailId);
			editSearchController.editEmailIdSuccess("The EmailId changed successfully!!!");
		}
		else
			editSearchController.nameNotFoundError("The given Contact Name not exist...");
	}
	public void editDOB(String name, LocalDate dob) {
		Contacts contact = ContactRepository.getInstance().getContact(name);
		if(contact!=null) {
			ContactRepository.getInstance().editDOB(contact,dob);
			editSearchController.editDOBSuccess("The DateOfBirth changed successfully!!!");
		}
		else
			editSearchController.nameNotFoundError("The given Contact Name not exist...");
	}
	public void searchByName(String name) {
		List<Contacts> contact = ContactRepository.getInstance().searchByName(name);
		editSearchController.printFoundContacts(contact);
	}
	public void searchByPhoneNumber(String phoneNumber) {
		List<Contacts> contact = ContactRepository.getInstance().searchByPhoneNumber(phoneNumber);
		editSearchController.printFoundContacts(contact);
	}
	public interface EditSearchModelControllerCallBack{

		void nameNotFoundError(String message);

		void numberNotFoundError(String message);

		void editNameSuccess(String message);

		void editDOBSuccess(String message);
		
		void editPhoneNumberSuccess(String message);
		
		void editEmailIdSuccess(String message);
		
		void printFoundContacts(List<Contacts> contact);
		
	}
}
