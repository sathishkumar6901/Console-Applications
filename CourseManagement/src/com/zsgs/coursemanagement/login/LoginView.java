package com.zsgs.coursemanagement.login;

import com.zsgs.coursemanagement.Base;
import com.zsgs.coursemanagement.admin.AdminView;
import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.trainer.TrainerView;
import com.zsgs.coursemanagement.user.UserView;

public class LoginView extends Base implements LoginViewCallBack{
	private LoginControllerCallBack loginController;
	
	public LoginView(){
		loginController = new LoginController(this);
	}
	
	public static void main(String[] args) {
		LoginView loginView = new LoginView();
		loginView.mainMenu();
	}

	private void mainMenu() {
		System.out.println("------>ZSGS Course Management<------\n");
		boolean repeat = true;
		do {
			System.out.println("1.Admin Login \n2.Trainer Login \n3.User SignUp \n4.User Login \n5.Fotget Password \n6.Exit\n");
			System.out.print("Enter your choice:");
			char choice = getChoice();
			switch(choice)
			{
				case '1': adminLogin();
						  break;
				case '2': trainerLogin();
						  break;
				case '3': userSignUp();
						  break;
				case '4': userLogin();
						  break;
				case '5': forgetPassword();
						  break;
				case '6': repeat = false;
						  break;
				default: System.out.println("Please enter a valid option\n");
			}
		}while(repeat);
		System.out.println("\n------>Thank You<------\n");
	}

	private void forgetPassword() {
		System.out.println("\n------>Password Reset Page<------\n");
		System.out.print("Enter your Id:");
		String id = getId();
		System.out.print("Enter your Email Id:");
		String emailId = getEmailId();
		loginController.getOneTimePassword(id,emailId);
		resetPassword(id,emailId);		
	}

	private void resetPassword(String id, String emailId) {
		System.out.print("Enter your One Time Password:");
		String otp = getPassword();
		System.out.print("Enter your New Password:");
		String password = getPassword();
		System.out.print("Re-enter your password(as same as new password):");
		String confirmPassword = getPassword();
		loginController.setNewPassword(id,emailId,otp,password,confirmPassword);
	}

	private void adminLogin() {
		System.out.println("\n------>Admin Login Page<------\n");
		System.out.print("Enter Admin Id:");
		String adminId = getId();
		System.out.print("Enter Admin Password:");
		String password = getPassword();
		loginController.isValidAdmin(adminId, password);
	}
	
	private void trainerLogin() {
		System.out.println("\n------>Trainer Login Page<------\n");
		System.out.print("Enter Trainer Id:");
		String trainerId = getId();
		System.out.print("Enter Trainer Password:");
		String password = getPassword();
		loginController.isValidTrainer(trainerId, password);
	}
	
	private void userSignUp() {
		System.out.println("\n------>User SignUp Page<------\n");
		System.out.print("Enter User Name:");
		String userName = getName(); 
		System.out.print("Enter email Id:");
		String emailId = getEmailId();
		System.out.print("Enter User Password:");
		String password = getPassword();
		System.out.print("Enter your Phone Number:");
		String phoneNumber = getPhoneNumber();
		loginController.newUser(userName, emailId, password, phoneNumber);
	}
	private void userLogin() {
		System.out.println("\n------>User Login Page<------\n");
		System.out.print("Enter User Id:");
		String userId = getId();
		System.out.print("Enter User Password:");
		String password = getPassword();
		loginController.isValidUser(userId, password);
	}
	
	/*******controller to view****/
	public void adminValidationSuccess(Admin currAdmin) {
		System.out.println("\n------>Welcome "+currAdmin.getAdminName()+" <------\n");
		AdminView adminView = new AdminView();
		adminView.adminMenu(currAdmin);
	}
	public void adminValidationFailure(String message) {
		System.out.println(message+"\n");
	}
	public void trainerValidationSuccess(Trainer currTrainer) {
		System.out.println("\n------>Welcome "+currTrainer.getTrainerName()+" <------\n");
		TrainerView trainerView = new TrainerView();
		trainerView.trainerMenu(currTrainer);
	}
	public void trainerValidationFailure(String message) {
		System.out.println(message+"\n");
	}
	public void userValidationSuccess(User currUser) {
		System.out.println("\n------>Welcome "+currUser.getUserName()+" <------\n");
		UserView userView = new UserView();
		userView.userMenu(currUser);
	}
	public void addUserSuccess(User currUser) {
		System.out.println("\nYour account created successfully!!!\n");
		System.out.println("Your User Id is:"+currUser.getUserId());
		System.out.println("\n------>Welcome "+currUser.getUserName()+" <------\n");
		UserView userView = new UserView();
		userView.userMenu(currUser);
	}
	public void userValidationFailure(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void userNameError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void emailIdError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void phoneNumberError(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void addUserFailure(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void generateOTPFailure(String message) {
		System.out.println("\n"+message+"\n");
		forgetPassword();
	}

	public void generateOTPSuccess(String message) {
		System.out.println("\n"+message+"\n");
	}
	public void passwordMismatchError(String message, String id, String emailId) {
		System.out.println("\n"+message+"\n");
		resetPassword(id,emailId);
	}
	public void passwordResetFailure(String message) {
		System.out.println("\n"+message+"\n");
	}

	public void passwordResetSuccess(String message) {
		System.out.println("\n"+message+"\n");
		mainMenu();
	}
}
