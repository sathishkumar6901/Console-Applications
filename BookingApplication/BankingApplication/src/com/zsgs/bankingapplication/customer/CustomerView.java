package com.zsgs.bankingapplication.customer;

import java.util.Scanner;
import java.util.List;
import java.util.Map.Entry;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Customer;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;
import com.zsgs.bankingapplication.loan.LoanView;
import com.zsgs.bankingapplication.transaction.TransactionView;

public class CustomerView implements CustomerViewCallBack{
	private CustomerControllerCallBack customerController;
	private Scanner scanner =new Scanner(System.in);
	public CustomerView()
	{
		customerController = new CustomerController(this);
	}
	
	public void customerModule()
	{
		boolean repeat = true;
		System.out.println("------>Welcome to Customer Login Page<------\n");
		do {
			System.out.println("1.Login \n2.Register \n3.Reset Login Password \n4.Exit\n");
			System.out.println("Note: if you are new customer, please register first...\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
			case 1: customerLogin();
					break;
			case 2: Registration();
					break;
			case 3: resetLoginPassword();
					break;
			case 4: repeat = false;
					break;
			default:System.out.println("Please Enter a Valid choice...\n");
					break;
			}
		}while(repeat);
	}
	
	private void customerMenu(String customerId)
	{
		List<Notification> notifications = customerController.getCustomerNotifications(customerId);
		if(!notifications.isEmpty())
			System.out.println("You have a new notifications!!!\n");
		boolean repeat = true;
		do {
			System.out.println("1.View notifications \n2.View profile \n3.View Balance \n4.Transactions(deposit/withdraw/etc..) \n5.View History \n6.Apply Loan \n7.Logout\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1: viewNotification(notifications);
						break;
				case 2: viewPersonalDetails(customerId);
						viewAccountDetails(customerId);
						break;
				case 3: System.out.println("Enter your Account Number:");
						String accountNumber = scanner.next();
						System.out.printf("your Account Balance is: %.2f\n",customerController.viewBalance(accountNumber));
						System.out.println();
						break;
				case 4: TransactionView transactionView = new TransactionView();
						transactionView.transactionMenu();
						break;
				case 5: viewHistory();
						break;
				case 6: LoanView loanView = new LoanView();
						loanView.loanMenu();
						break;
				case 7: repeat = false;
						break;
			}
			
		}while(repeat);
	}

	public void viewAccountDetails(String customerId) {
		customerController.viewAccountDetails(customerId);
		
	}

	public void viewPersonalDetails(String customerId) {
		customerController.viewPersonalDetails(customerId);
		
	}

	private void viewHistory() {
		System.out.println("------>View Transaction History Page<------\n");
		System.out.println("Enter your Account Number:");
		String accountNumber =scanner.next();
		List<Transaction> transactions = customerController.viewTransactions(accountNumber);
		if(transactions.isEmpty())
				System.out.println("You didn't make any transactions till now!!!\n");
		else
		{
			System.out.println("Transaction Date\tTransaction Id\tAmount \tTransaction Type\tBalance");
			for(Transaction transaction:transactions)
			{
				System.out.printf("%-24tD%-16s%-8.2f%-7s%-10.2f\n",transaction.getTransactionDate(),transaction.getTransactionId(),transaction.getTransactionAmount(),transaction.getTransactionType(),transaction.getCurrentBalance());
				
			}
		}
		
	}

	private void viewNotification(List<Notification> notifications) {
		if(notifications.isEmpty())
			System.out.println("You don't have any new notifications!!!\n");
		else
		{
			System.out.println("------>Notifications List<------\n");
			for(Notification notification:notifications)
			{
				System.out.println(notification.getReplyMessage()+"\n");
				customerController.changeViewedStatus(notification);
			}
			System.out.println();
		}
	}

	private void customerLogin() {
		System.out.println("------>Customer Login Page<------\n");
		System.out.println("Enter your Customer Id:");
		String id = scanner.next();
		System.out.println("Enter your Password:");
		String password = scanner.next();
		customerController.checkValidCustomer(id,password);
	}

	private void Registration() {
		System.out.println("------>Customer Registration Page<------\n");
		System.out.println("Enter your Name:");
		String name = scanner.next();
		System.out.println("Enter your age:");
		int age =scanner.nextInt();
		System.out.println("Enter your Phone Number:");
		String phoneNumber = scanner.next();
		System.out.println("Enter your Aadhar Number:");
		String aadharNumber = scanner.next();
		customerController.addNewCustomer(name,age,phoneNumber,aadharNumber);
	}
	
	private void resetLoginPassword() {
		System.out.println("----->Login Password Reset Page<-----\n");
		System.out.println("Enter your Customer Id:");
		String customerId = scanner.next();
		System.out.println("Enter the login password you want to reset:");
		String password = scanner.next();
		System.out.println("Enter the confirm password as same as the password:");
		String confirmPassword = scanner.next();
		customerController.resetLoginPassword(customerId,password,confirmPassword);
	}
	
	public void customerLoginFailure(String message)
	{
		System.out.println(message+"\n");
	}

	public void customerLoginSuccess(String message,String customerId)
	{
		System.out.println(message+"\n");
		customerMenu(customerId);
	}
	
	public void addNewCustomerSuccess(String message)
	{
		System.out.println(message+"\n");
		System.out.println("Please change your password as soon as possible\n");
		System.out.println("Your account is temporarily hold by an Admin for verification.\n");
		System.out.println("Once the verification is completed, your account will be activated!!!\n");
		System.out.println("please check the message regularly...\n");
	}
	
	public void addNewCustomerFailure(String message)
	{
		System.out.println(message+"\n");
	}
	
	public void noAccountNumberError(String message)
	{
		System.out.println(message+"\n");
	}
	
	public void passwordMismatchError()
	{
		System.out.println("Your password and confirm password are mismatched!!!\n");
		System.out.println("Please enter the confirm password as same as the password\n");
	}
	
	public void customerPasswordResetSuccess(String message)
	{
		System.out.println(message+"\n");
	}
	
	public void printPersonalDetails(Customer currCustomer)
	{
		System.out.println("------>USER PERSONAL DETAILS<------\n");
		System.out.printf("Id:%-15s\n",currCustomer.getCustomerId());
		System.out.printf("Name:%-15s\n",currCustomer.getCustomerName());
		System.out.printf("Age:%-15d\n",currCustomer.getAge());
		System.out.printf("PhoneNumber:%-15s\n",currCustomer.getPhoneNumber());
		System.out.printf("Aadhar Number:%-15s\n", currCustomer.getAadharNumber());
		System.out.println();
	}
	public void printAccountDetails(Entry<Account, CardDetails> currAccount)
	{
		System.out.println("------>USER ACCOUNT DETAILS<------\n");
		System.out.printf("Account Number:%-18s\n", currAccount.getKey().getAccountNumber());
		System.out.printf("IFSE code:%-18s\n",currAccount.getKey().getIFSCCode());
		System.out.printf("Card Number:%-18s\n", currAccount.getValue().getCardNumber());
		System.out.printf("Balance:%-18.2f\n", currAccount.getKey().getBalance());
		System.out.println();
	}
}
