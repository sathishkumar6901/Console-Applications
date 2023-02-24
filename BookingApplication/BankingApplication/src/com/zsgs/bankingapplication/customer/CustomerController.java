package com.zsgs.bankingapplication.customer;

import java.util.List;
import java.util.Map.Entry;

import com.zsgs.bankingapplication.customer.CustomerModel.CustomerModelControllerCallBack;
import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Customer;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;

public class CustomerController implements CustomerControllerCallBack, CustomerModelControllerCallBack {
	private CustomerViewCallBack customerView;
	private CustomerModelCallBack customerModel;
	private static int id= 10001;
	
	public CustomerController(CustomerViewCallBack customerView)
	{
		this.customerView = customerView;
		this.customerModel = new CustomerModel(this);
	}
	
	public void checkValidCustomer(String id, String password)
	{
		customerModel.checkValidCustomer(id,password);
	}
	public void addNewCustomer(String name, int age, String phoneNumber, String aadharNumber)
	{
		String customerId = String.valueOf(id);
		id++;
		customerModel.addNewCustomer(customerId,name,age,phoneNumber,aadharNumber);
	}
	
	public void resetLoginPassword(String customerId, String password, String confirmPassword)
	{
		if(password.equals(confirmPassword))
			customerModel.resetLoginPassword(customerId,password);
		else
			customerView.passwordMismatchError();
	}
	
	public List<Notification> getCustomerNotifications(String customerId)
	{
		return customerModel.getCustomerNotifications(customerId);
	}
	
	public void changeViewedStatus(Notification notifications)
	{
		notifications.setViewedResult("seen");
		return;
	}
	public float viewBalance(String accountNumber)
	{
		return customerModel.viewBalance(accountNumber);
	}
	
	public List<Transaction> viewTransactions(String accountNumber)
	{
		return customerModel.viewTransactions(accountNumber);
	}
	
	public void viewPersonalDetails(String customerId)
	{
		customerModel.viewPersonalDetails(customerId);
	}
	
	public void viewAccountDetails(String customerId)
	{
		customerModel.viewAccountDetails(customerId);
	}
	public void addNewCustomerSuccess(String message)
	{
		customerView.addNewCustomerSuccess(message);
	}
	
	public void addNewCustomerFailure(String message)
	{
		customerView.addNewCustomerFailure(message);
	}
	
	public void customerLoginFailure(String message)
	{
		customerView.customerLoginFailure(message);
	}

	public void customerLoginSuccess(String message,String customerId)
	{
		customerView.customerLoginSuccess(message, customerId);
	}

	public void noAccountNumberError(String message)
	{
		customerView.noAccountNumberError(message);
	}
	public void customerPasswordResetSuccess(String message)
	{
		customerView.customerPasswordResetSuccess(message);
	}
	public void printPersonalDetails(Customer currCustomer)
	{
		customerView.printPersonalDetails(currCustomer);
	}
	public void printAccountDetails(Entry<Account, CardDetails> currAccount)
	{
		customerView.printAccountDetails(currAccount);
	}
}
