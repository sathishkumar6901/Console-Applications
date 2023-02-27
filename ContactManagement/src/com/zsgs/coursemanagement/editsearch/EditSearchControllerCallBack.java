package com.zsgs.coursemanagement.editsearch;

public interface EditSearchControllerCallBack {

	void editName(String name, String newName);

	void editPhoneNumber(String name, String phoneNumber, String newPhoneNumber);

	void editEmailId(String name, String emailId);

	void editDOB(String name, String date);

	void searchByName(String name);

	void searchByPhoneNumber(String phoneNumber);

}
