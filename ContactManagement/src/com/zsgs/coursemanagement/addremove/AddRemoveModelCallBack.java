package com.zsgs.coursemanagement.addremove;

import java.time.LocalDate;

public interface AddRemoveModelCallBack {

	void addNewContact(String name, String emailId, String phoneNumber, LocalDate dob);

	void addExistingContact(String phoneNumber, String name);

	void deleteContact(String phoneNumber, String name);

}
