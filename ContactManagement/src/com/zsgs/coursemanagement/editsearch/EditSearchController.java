package com.zsgs.coursemanagement.editsearch;

import java.time.LocalDate;
import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;
import com.zsgs.coursemanagement.editsearch.EditSearchModel.EditSearchModelControllerCallBack;

public class EditSearchController implements EditSearchControllerCallBack, EditSearchModelControllerCallBack {
	private EditSearchViewCallBack editSearchView;
	private EditSearchModelCallBack editSearchModel;
	
	public EditSearchController(EditSearchViewCallBack editSearchView) {
		this.editSearchView = editSearchView;
		this.editSearchModel = new EditSearchModel(this);
	}
	public void editName(String name, String newName) {
		editSearchModel.editName(name,newName);
	}
	public void editPhoneNumber(String name, String phoneNumber, String newPhoneNumber) {
		if(!phoneNumber.matches("[0-9]+") || phoneNumber.length()!=10)
			editSearchView.phoneNumberError("Please enter a valid Phone Number!!!");
		else if(!newPhoneNumber.matches("[0-9]+") || newPhoneNumber.length()!=10)
			editSearchView.phoneNumberError("Please enter a valid New Phone Number!!!");
		else if(newPhoneNumber.equals(phoneNumber))
			editSearchView.phoneNumberError("Both, Phone Number and New Phone Number are same!!!");
		else {
			editSearchModel.editPhoneNumber(name,phoneNumber,newPhoneNumber);
		}
	}
	public void editEmailId(String name, String emailId) {
		if(!emailId.matches("^[A-Za-z0-9]+@(.+)$"))
			editSearchView.emailIdError("Please enter a valid EmailId...");
		else
			editSearchModel.editEmailId(name,emailId);
	}
	public void editDOB(String name, String date) {
		LocalDate dob = LocalDate.parse(date);
		editSearchModel.editDOB(name,dob);
	}
	public void searchByName(String name) {
		editSearchModel.searchByName(name);
	}
	public void searchByPhoneNumber(String phoneNumber) {
		if(!phoneNumber.matches("[0-9]+"))
			editSearchView.phoneNumberError("Please enter a valid Phone Number!!!");
		else
			editSearchModel.searchByPhoneNumber(phoneNumber);
	}
	/******************Model to Controller****************/
	public void editNameSuccess(String message) {
		editSearchView.editNameSuccess(message);
	}
	public void editPhoneNumberSuccess(String message) {
		editSearchView.editPhoneNumberSuccess(message);
	}
	public void editEmailIdSuccess(String message) {
		editSearchView.editEmailIdSuccess(message);
	}
	public void editDOBSuccess(String message) {
		editSearchView.editDOBSuccess(message);
	}
	public void nameNotFoundError(String message) {
		editSearchView.nameNotFoundError(message);
	}
	public void numberNotFoundError(String message) {
		editSearchView.numberNotFoundError(message);
	}
	public void printFoundContacts(List<Contacts> contact) {
		if(contact.isEmpty())
			editSearchView.noContactFound("No contact found with your search");
		else
			editSearchView.printFoundContacts(contact);
	}
}
