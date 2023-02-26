package com.zsgs.coursemanagement.login;

import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.dto.User;

public interface LoginViewCallBack {

	void adminValidationFailure(String message);

	void adminValidationSuccess(Admin currAdmin);

	void trainerValidationSuccess(Trainer currTrainer);

	void trainerValidationFailure(String message);

	void userValidationSuccess(User currUser);

	void userValidationFailure(String message);

	void userNameError(String message);

	void emailIdError(String message);

	void phoneNumberError(String message);

	void addUserFailure(String message);

	void addUserSuccess(User currUser);

	void generateOTPFailure(String message);

	void generateOTPSuccess(String message);

	void passwordMismatchError(String message, String id, String emailId);

	void passwordResetFailure(String message);

	void passwordResetSuccess(String message);

}
