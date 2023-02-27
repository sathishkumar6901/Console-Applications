package com.zsgs.coursemanagement.addremove;

public interface AddRemoveControllerCallBack {

	void addNewContact(String name, String emailId, String phoneNumber, String date);

	void addExistingContact(String phoneNumber, String name);

	void deleteContact(String name, String phoneNumber);

}
