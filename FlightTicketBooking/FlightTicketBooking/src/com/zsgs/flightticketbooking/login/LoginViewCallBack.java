package com.zsgs.flightticketbooking.login;

import com.zsgs.flightticketbooking.dto.Admin;

public interface LoginViewCallBack {

	void loginSuccess(Admin adminUser);

	void loginFailure(String message);

}
