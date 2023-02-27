package com.zsgs.coursemanagement.editsearch;

import java.time.LocalDate;

public interface EditSearchModelCallBack {

	void editName(String name, String newName);

	void editPhoneNumber(String name, String phoneNumber, String newPhoneNumber);

	void editEmailId(String name, String emailId);

	void editDOB(String name, LocalDate dob);

	void searchByName(String name);

	void searchByPhoneNumber(String phoneNumber);

}
