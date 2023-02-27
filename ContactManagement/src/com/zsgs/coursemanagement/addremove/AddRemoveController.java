package com.zsgs.coursemanagement.addremove;

import java.time.LocalDate;

import com.zsgs.coursemanagement.addremove.AddRemoveModel.AddRemoveModelControllerCallBack;

public class AddRemoveController implements AddRemoveControllerCallBack, AddRemoveModelControllerCallBack {
	private AddRemoveViewCallBack addRemoveView;
	private AddRemoveModelCallBack addRemoveModel;
	
	public AddRemoveController(AddRemoveViewCallBack addRemoveView) {
		this.addRemoveView = addRemoveView;
		this.addRemoveModel = new AddRemoveModel(this);
	}
	
	public void addNewContact(String name, String emailId, String phoneNumber, String date) {
		if(!emailId.matches("^[A-Za-z0-9]+@(.+)$"))
			addRemoveView.emailIdError("Please enter a valid EmailId...");
		else if(!phoneNumber.matches("[0-9]+") || phoneNumber.length()!=10)
			addRemoveView.phoneNumberError("Please enter a valid Phone Number");
		else {
			LocalDate dob = LocalDate.parse(date);
			addRemoveModel.addNewContact(name,emailId,phoneNumber,dob);
		}	
	}
	public void addExistingContact(String phoneNumber, String name) {
		if(!phoneNumber.matches("[0-9]+") || phoneNumber.length()!=10)
			addRemoveView.phoneNumberError("Please enter a valid Phone Number");
		else 
			addRemoveModel.addExistingContact(phoneNumber,name);
	}
	public void deleteContact(String name, String phoneNumber) {
		if(!phoneNumber.matches("[0-9]+") || phoneNumber.length()!=10)
			addRemoveView.phoneNumberError("Please enter a valid Phone Number");
		else 
			addRemoveModel.deleteContact(phoneNumber,name);
	}
	/*************Model to Controller************/
	public void addNewContactSuccess(String message) {
		addRemoveView.addNewContactSuccess(message);
	}
	public void addNewContactFailure(String message) {
		addRemoveView.addNewContactFailure(message);
	}
	public void addExistingContactSuccess(String message) {
		addRemoveView.addExistingContactSuccess(message);
	}
	public void addExistingContactFailue(String message) {
		addRemoveView.addExistingContactFailue(message);
	}
	public void deleteContactSuccess(String message) {
		addRemoveView.deleteContactSuccess(message);
	}
	public void deleteContactFailure(String message) {
		addRemoveView.deleteContactFailure(message);
	}
}
