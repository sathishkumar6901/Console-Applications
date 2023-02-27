package com.zsgs.coursemanagement.editsearch;

import java.util.List;

import com.zsgs.coursemanagement.dto.Contacts;

public interface EditSearchViewCallBack {

	void editNameSuccess(String message);

	void editPhoneNumberSuccess(String message);

	void editEmailIdSuccess(String message);

	void editDOBSuccess(String message);
	
	void nameNotFoundError(String message);

	void numberNotFoundError(String message);
	
	void phoneNumberError(String message);
	
	void emailIdError(String message);

	void printFoundContacts(List<Contacts> contact);

	void noContactFound(String message);

}
