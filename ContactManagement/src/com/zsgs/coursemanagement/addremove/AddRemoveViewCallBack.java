package com.zsgs.coursemanagement.addremove;

public interface AddRemoveViewCallBack {

	void emailIdError(String message);

	void phoneNumberError(String message);

	void addNewContactSuccess(String message);

	void addExistingContactSuccess(String message);

	void addNewContactFailure(String message);

	void addExistingContactFailue(String message);

	void deleteContactSuccess(String message);

	void deleteContactFailure(String message);

}
