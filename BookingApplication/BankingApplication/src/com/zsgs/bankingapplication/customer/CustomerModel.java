package com.zsgs.bankingapplication.customer;

import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Customer;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.repository.BankingRepository;

public class CustomerModel implements CustomerModelCallBack {
	private CustomerModelControllerCallBack customerController;
	
	public CustomerModel(CustomerModelControllerCallBack customerController) {
		this.customerController = customerController;
	}
	private String generatePassword() {
		UUID uuid = UUID.randomUUID();
        String pass=uuid.toString().replace("-","");
        String password = pass.substring(pass.length() - 10);
        return password;
	}
	
	public void checkValidCustomer(String id, String password)
	{
		Customer currCustomer = BankingRepository.getInstance().checkValidCustomer(id,password);
		if(currCustomer!=null)
		{
			Account currAccount = BankingRepository.getInstance().checkValidAccount(id);
			if(currAccount==null)
				customerController.noAccountNumberError("Your account is temporarily hold by an Admin for verification.\n");
			else
				customerController.customerLoginSuccess("Congratulations!!! You are loggedin successfully!!!",currCustomer.getCustomerId());
		}
		else
			customerController.customerLoginFailure("Please ensure, Is the login id and password is correct?");
	}
	
	public void addNewCustomer(String customerId,String name, int age, String phoneNumber, String aadharNumber)
	{
		Customer currCustomer = BankingRepository.getInstance().isCustomerExist(aadharNumber);
		if(currCustomer==null)
		{
			String password =generatePassword();
			BankingRepository.getInstance().addNewCustomer(customerId,name,password,age,phoneNumber,aadharNumber);
			BankingRepository.getInstance().addRequest(customerId,"New Customer");
			customerController.addNewCustomerSuccess("Congratulations!!! Your account successfully added.\nThis is your CustomerId: "+customerId+"\nThis is your Temporary Password is: "+password);
		}
		else
			customerController.addNewCustomerFailure("This account is already exist!!!");
	}

	public void resetLoginPassword(String customerId, String password)
	{
		BankingRepository.getInstance().customerLoginPasswordReset(customerId,password);
		customerController.customerPasswordResetSuccess("Your Login password changed successfully!!!");
	}
	
	public List<Notification> getCustomerNotifications(String customerId)
	{
		return BankingRepository.getInstance().getCustomerNotifications(customerId);
	}
	
	public void viewPersonalDetails(String customerId)
	{
		Customer currCustomer = BankingRepository.getInstance().getCurrentCustomer(customerId); 
		customerController.printPersonalDetails(currCustomer);
	}
	public void viewAccountDetails(String customerId)
	{
		Entry<Account,CardDetails> currAccount = BankingRepository.getInstance().getCurrentAccount(customerId);
		customerController.printAccountDetails(currAccount);
	}
	public float viewBalance(String accountNumber)
	{
		Account currAccount = BankingRepository.getInstance().getAccount(accountNumber);
		return currAccount.getBalance();
	}
	
	public List<Transaction> viewTransactions(String accountNumber)
	{
		return BankingRepository.getInstance().getTransactions(accountNumber);
	}

	public interface CustomerModelControllerCallBack
	{

		void addNewCustomerSuccess(String message);

		void printAccountDetails(Entry<Account, CardDetails> currAccount);

		void printPersonalDetails(Customer currCustomer);

		void customerPasswordResetSuccess(String message);

		void customerLoginFailure(String message);

		void customerLoginSuccess(String message, String customerId);

		void noAccountNumberError(String message);

		void addNewCustomerFailure(String message);
		
	}
}
