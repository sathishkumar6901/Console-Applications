package com.zsgs.coursemanagement.login;

import java.util.UUID;

import com.zsgs.coursemanagement.dto.Admin;
import com.zsgs.coursemanagement.dto.Trainer;
import com.zsgs.coursemanagement.dto.User;
import com.zsgs.coursemanagement.repository.CourseRepository;

public class LoginModel implements LoginModelCallBack {
	
	private LoginModelControllerCallBack loginController;
	
	public LoginModel(LoginModelControllerCallBack loginController) {
		this.loginController = loginController;
	}
	
	public void isValidAdmin(String adminId, String password) {
		Admin currAdmin = CourseRepository.getInstance().getValidAdmin(adminId, password);
		if(currAdmin==null)
			loginController.adminValidationFailure("Please enter a valid Id and password!!!");
		else
			loginController.adminValidationSuccess(currAdmin);
	}
	public void isValidTrainer(String trainerId, String password) {
		Trainer currTrainer = CourseRepository.getInstance().getValidTrainer(trainerId, password);
		if(currTrainer==null)
			loginController.trainerValidationFailure("Please enter a valid Id and password!!!");
		else
			loginController.trainerValidationSuccess(currTrainer);
	}
	public void isValidUser(String userId, String password) {
		User currUser = CourseRepository.getInstance().getValidUser(userId, password);
		if(currUser==null)
			loginController.userValidationFailure("Please enter a valid Id and password!!!");
		else
			loginController.userValidationSuccess(currUser);
	}
	public void newUser(String userName,String userId, String emailId, String password,String phoneNumber) {
		User currUser = CourseRepository.getInstance().isNewUser(emailId);
		if(currUser==null)
		{
			currUser = CourseRepository.getInstance().addNewUser(userId,userName,emailId,password,phoneNumber);
			loginController.addUserSuccess(currUser);
		}
		else
			loginController.addUserFailure("The given EmailId is already exist!!!");
	}
	public void getOneTimePassword(String id, String emailId) {
		String otp = generateOTP();
		User currUser = CourseRepository.getInstance().setUserPassword(otp,emailId);
		Trainer currTrainer = CourseRepository.getInstance().setTrainerPassword(otp,emailId);
		if(currUser!=null)
			loginController.generateOTPSuccess(id+" Your One Time Password is: "+otp);
		else if(currTrainer!=null)
			loginController.generateOTPSuccess(id+" Your One Time Password is: "+otp);
		else
			loginController.generateOTPFailure("Please enter a valid Id and EmailId");
	}
	public void setNewPassword(String id, String emailId, String otp, String password) {
		User currUser = CourseRepository.getInstance().setNewUserPassword(emailId,otp,password);
		Trainer currTrainer = CourseRepository.getInstance().setNewTrainerPassword(emailId,otp,password);
		if(currUser!=null)
			loginController.passwordResetSuccess(id+", Your password reseted successfully");
		else if(currTrainer!=null)
			loginController.passwordResetSuccess(id+", Your password reseted successfully");
		else
			loginController.passwordResetFailure("Please enter a valid One Time Password");
	}
	private String generateOTP() {
		UUID uuid = UUID.randomUUID();
	    String otp=uuid.toString().replace("-","");
	    String otpPassword = otp.substring(otp.length() - 10);
	    return otpPassword;
	}
	public interface LoginModelControllerCallBack{

		void adminValidationSuccess(Admin currAdmin);

		void passwordResetFailure(String message);

		void passwordResetSuccess(String message);

		void generateOTPFailure(String message);

		void generateOTPSuccess(String message);

		void adminValidationFailure(String message);
		
		void trainerValidationSuccess(Trainer currTrainer);

		void trainerValidationFailure(String message);
		
		void userValidationSuccess(User currUser);

		void userValidationFailure(String message);
		
		void addUserFailure(String message);

		void addUserSuccess(User currUser);
		
	}
}
