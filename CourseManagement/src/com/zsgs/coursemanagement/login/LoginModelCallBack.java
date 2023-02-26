package com.zsgs.coursemanagement.login;

public interface LoginModelCallBack {

	void isValidAdmin(String adminId, String password);

	void isValidTrainer(String trainerId, String password);

	void isValidUser(String userId, String password);

	void newUser(String userName,String userId, String emailId, String password, String phoneNumber);

	void getOneTimePassword(String id,String emailId);

	void setNewPassword(String id, String emailId, String otp, String password);

}
