package com.zsgs.coursemanagement.login;

import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.login.LoginModel.LoginModelControllerCallBack;

public class LoginController implements LoginControllerCallBack, LoginModelControllerCallBack {
	private LoginViewCallBack loginView;
	private LoginModelCallBack loginModel;
	private static int id = 1;
	
	public LoginController(LoginViewCallBack loginView) {
		this.loginView = loginView;
		this.loginModel = new LoginModel(this);
	}
	
	public void isValidAdmin(String adminId, String password) {
			loginModel.isValidAdmin(adminId, password);
	}
	
	public void isValidTrainer(String trainerId, String password) {
		loginModel.isValidTrainer(trainerId, password);
	}
	
	public void isValidUser(String userId, String password) {
		loginModel.isValidUser(userId, password);
	}
	
	public void newUser(String userName, String emailId, String password,String phoneNumber) {
		if(!userName.matches("[A-Za-z]+"))
			loginView.userNameError("Please enter a valid UserName");
		else if(!emailId.matches("^[A-Za-z0-9]+@(.+)$"))
			loginView.emailIdError("Please enter a valid EmailId");
		else if((!phoneNumber.matches("[0-9]+")) || phoneNumber.length()!=10)
			loginView.phoneNumberError("\nPlease enter a valid phone number");
		else
		{
			String userId = "UR000"+id;
			id++;
			loginModel.newUser(userName, userId, emailId, password,phoneNumber);
		}		
	}
	public void getOneTimePassword(String id,String emailId) {
		if(!emailId.matches("^[A-Za-z0-9]+@(.+)$"))
			loginView.emailIdError("Please enter a valid EmailId");
		else
			loginModel.getOneTimePassword(id,emailId);
	}
	public void setNewPassword(String id, String emailId, String otp, String password, String confirmPassword) {
		if(password.equals(confirmPassword))
			loginModel.setNewPassword(id,emailId,otp,password);
		else 
			loginView.passwordMismatchError("Please enter a confirm password as same as password",id,emailId);
	}
	/*******model to controller*********/
	public void adminValidationFailure(String message) {
		loginView.adminValidationFailure(message);
	}
	
	public void adminValidationSuccess(Admin currAdmin) {
		loginView.adminValidationSuccess(currAdmin);
	}
	
	public void trainerValidationSuccess(Trainer currTrainer) {
		loginView.trainerValidationSuccess(currTrainer);
	}

	public void trainerValidationFailure(String message) {
		loginView.trainerValidationFailure(message);
	}
	public void userValidationSuccess(User currUser) {
		loginView.userValidationSuccess(currUser);
	}

	public void userValidationFailure(String message) {
		loginView.userValidationFailure(message);
	}
	public void addUserFailure(String message) {
		loginView.addUserFailure(message);
	}
	public void addUserSuccess(User currUser) {
		loginView.addUserSuccess(currUser);
	}
	public void generateOTPFailure(String message) {
		loginView.generateOTPFailure(message);
	}

	public void generateOTPSuccess(String message) {
		loginView.generateOTPSuccess(message);
	}
	public void passwordResetFailure(String message) {
		loginView.passwordResetFailure(message);
	}

	public void passwordResetSuccess(String message) {
		loginView.passwordResetSuccess(message);
	}
}
