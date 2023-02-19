package com.zsgs.flightticketbooking.login;

import com.zsgs.flightticketbooking.dto.Admin;
import com.zsgs.flightticketbooking.login.LoginModel.LoginModelControllerCallBack;

public class LoginController implements LoginControllerCallBack, LoginModelControllerCallBack{
	private LoginViewCallBack loginView;
	private LoginModelCallBack loginModel;

	public LoginController(LoginView loginView) {
		this.loginView = loginView;
		this.loginModel = new LoginModel(this);
	}

	public void checkValidAdmin(String name, String password) {
		loginModel.checkValidAdmin(name,password);
	}
	
	public void adminLoginSuccess(Admin adminUser)
	{
		loginView.loginSuccess(adminUser);
	}
	public void adminLoginFailure(String message)
	{
		loginView.loginFailure(message);
	}

}
