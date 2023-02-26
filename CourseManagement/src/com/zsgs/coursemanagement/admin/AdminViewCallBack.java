package com.zsgs.coursemanagement.admin;

public interface AdminViewCallBack {

	void addAdminSuccess(String message);

	void addCourseSuccess(String message);

	void NameError(String message);

	void emailIdError(String message);

	void phoneNumberError(String message);

	void addTrainerSuccess(String message);

}
