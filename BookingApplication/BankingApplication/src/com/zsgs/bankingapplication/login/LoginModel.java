package com.zsgs.bankingapplication.login;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Admin;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;
import com.zsgs.bankingapplication.repository.BankingRepository;

public class LoginModel implements LoginModelCallBack {
	private LoginModelControllerCallBack loginController;
	

	public LoginModel(LoginModelControllerCallBack loginController) {
		this.loginController = loginController;
	}
	private String generatePassword() {
		UUID uuid = UUID.randomUUID();
        String pass=uuid.toString().replace("-","");
        String password = pass.substring(pass.length() - 10);
        return password;
	}
	public void checkValidAdmin(String id, String password)
	{
		Admin currAdmin = BankingRepository.getInstance().checkValidAdmin(id,password);
		if(currAdmin!=null)
			loginController.adminValidationSuccess(currAdmin);
		else
			loginController.adminValidationFailure("Please ensure, Is the login id and password is correct?");
	}
	public List<Notification> getAdminNotifications()
	{
		return BankingRepository.getInstance().getAdminNotifications();
	}
	
	public void showHistory(String customerId)
	{
		Account currAccount = BankingRepository.getInstance().checkValidAccount(customerId);
		List<Transaction> transactions = BankingRepository.getInstance().getTransactions(currAccount.getAccountNumber());
		loginController.printHistory(transactions);
	}
	public void viewAccounts()
	{
		Map<Account, CardDetails> accounts = BankingRepository.getInstance().getAccountsDetails();
		
		loginController.viewAccountSuccess(accounts);
	}
	
	public void addAccount(String customerId, String accountNumber, String cardNumber, String cvvNumber)
	{
		String transactionPassword = generatePassword();
		BankingRepository.getInstance().addNewAccount(customerId,accountNumber,transactionPassword,cardNumber,cvvNumber);
	}
	public void addRequestReply(Notification notification)
	{
		BankingRepository.getInstance().addRequestReply(notification);
	}
	public void addNewAdmin(String id, String name, String password)
	{
		Admin currAdmin = BankingRepository.getInstance().isAdminIdExist(id);
		if(currAdmin==null)
		{
			BankingRepository.getInstance().addAdmin(id,name,password);
			loginController.addAdminStatus("New Admin "+name+" added successfully!!!");
		}
			
		else
			loginController.addAdminStatus("The given admin id is already exist...");
		
	}
	public interface LoginModelControllerCallBack
	{

		void adminValidationSuccess(Admin currAdmin);

		void printHistory(List<Transaction> transactions);

		void addAdminStatus(String message);

		void viewAccountSuccess(Map<Account, CardDetails> accounts);

		void adminValidationFailure(String message);
		
	}
}
