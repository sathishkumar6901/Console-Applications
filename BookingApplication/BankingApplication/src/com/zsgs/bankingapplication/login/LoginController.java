package com.zsgs.bankingapplication.login;

import java.util.List;
import java.util.Map;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Admin;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;
import com.zsgs.bankingapplication.login.LoginModel.LoginModelControllerCallBack;

public class LoginController implements LoginControllerCallBack,LoginModelControllerCallBack{
	private LoginViewCallBack loginView;
	private LoginModelCallBack loginModel;

	private static int accountNumber = 1023564;
	private static int cardNumber = 2345;
	private static int cvvNumber = 243;
	public LoginController(LoginView loginView) {
		this.loginView = loginView;
		this.loginModel = new LoginModel(this);
	}

	public void checkValidAdmin(String id, String password)
	{
		loginModel.checkValidAdmin(id,password);
	}
	
	public List<Notification> getAdminNotifications()
	{
		return loginModel.getAdminNotifications();
	}
	public void showHistory(String customerId)
	{
		loginModel.showHistory(customerId);
	}
	public void viewAccounts()
	{
		loginModel.viewAccounts();
	}
	public void addNewAdmin(String id, String name, String password)
	{
		loginModel.addNewAdmin(id,name,password);
	}
	public void addAccount(String customerId)
	{
		loginModel.addAccount(customerId,Integer.toString(accountNumber),"456378961234"+Integer.toString(cardNumber),Integer.toString(cvvNumber));
		accountNumber++;
		cardNumber++;
		cvvNumber++;
	}
	public void addRequestReply(Notification notification)
	{
		loginModel.addRequestReply(notification);
	}
	public void adminValidationSuccess(Admin currAdmin)
	{
		loginView.adminValidationSuccess(currAdmin);
	}
	public void adminValidationFailure(String message)
	{
		loginView.adminValidationFailure(message);
	}
	
	public void addAdminStatus(String message)
	{
		loginView.addAdminStatus(message);
	}
	public void viewAccountSuccess(Map<Account, CardDetails> accounts)
	{
		loginView.viewAccountSuccess(accounts);
	}
	public void printHistory(List<Transaction> transactions)
	{
		loginView.printHistory(transactions);
	}
}
