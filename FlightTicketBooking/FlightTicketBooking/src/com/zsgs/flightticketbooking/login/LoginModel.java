package com.zsgs.flightticketbooking.login;

import com.zsgs.flightticketbooking.dto.Admin;
import com.zsgs.flightticketbooking.repository.FlightRepository;

public class LoginModel implements LoginModelCallBack{
	
	private LoginModelControllerCallBack loginController;
	
	public LoginModel(LoginModelControllerCallBack loginController) {
		this.loginController = loginController;
	}

	public void checkValidAdmin(String name, String password) {
		Admin adminUser = FlightRepository.getInstance().checkAdminCredencials(name,password);
		if(adminUser!=null)
			loginController.adminLoginSuccess(adminUser);
		else
			loginController.adminLoginFailure("Invalid UserName or Password...");
	}
	
	public interface LoginModelControllerCallBack
	{

		void adminLoginSuccess(Admin adminUser);

		void adminLoginFailure(String string);
		
	}

}
