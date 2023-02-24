package com.zsgs.bankingapplication.login;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.zsgs.bankingapplication.customer.*;
import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Admin;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;

public class LoginView implements LoginViewCallBack{
	
	private LoginControllerCallBack loginController;
	private Scanner scanner = new Scanner(System.in);
	public LoginView()
	{
		loginController = new LoginController(this);
	}

	public static void main(String[] args) {
		LoginView loginView =new LoginView();
		loginView.mainMenu();
	}

	public void mainMenu() {
		System.out.println("---------->ZSGS Bank Application<---------\n");
		boolean repeat = true;
		do {
			System.out.println("1.Admin Login \n2.Customer Login \n3.Exit\n");
			System.out.print("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1: adminLogin();
						break;
				case 2: CustomerView customerView = new CustomerView();
						customerView.customerModule();
						break;
				case 3: repeat = false;
			}
		}while(repeat);
		System.out.println("------>Thank You<------");
	}

	private void adminLogin() {
		System.out.println("------>Admin Login Page<------\n");
		System.out.println("Enter your Id:");
		String id = scanner.next();
		System.out.println("Enter your password:");
		String password = scanner.next();
		loginController.checkValidAdmin(id,password);
		
	}
	
	private void adminMenu() {
		boolean repeat = true;
		List<Notification> notifications = loginController.getAdminNotifications();
		if(!notifications.isEmpty())
			System.out.println("You have a new messages!!!\n");
		
		do {
			System.out.println("1.Add New Admin \n2.View Notifications \n3.View Accounts \n4.Logout\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice)
			{
			case 1: {
					System.out.println("Enter admin id:");
					String id = scanner.next();
					System.out.println("Enter admin name:");
					String name = scanner.next();
					System.out.println("Enter admin password:");
					String password = scanner.next();
					loginController.addNewAdmin(id,name,password);
					break;
					}
			case 2: viewNotifications(notifications);
					break;
			case 3: {
					loginController.viewAccounts();
					break;
					}
			case 4: repeat = false;
					mainMenu();
					break;
					
			default: System.out.println("Please enter a valid choice");
					 break;
			}
		}while(repeat);
		
	}
	
	private void viewNotifications(List<Notification> notifications) {
		if(notifications.isEmpty())
			System.out.println("You don't have any new notifications!!!\n");
		else
		{
			System.out.println("------>Notifications List<------\n");
			System.out.println("Customer Id\tRequest Type");
			for(Notification notification:notifications)
			{
				System.out.printf("%-15s %s\n",notification.getCustomerId(),notification.getRequestType());
			}
			System.out.println();
			CustomerView customerView = new CustomerView();
			for(Notification notification:notifications)
			{
				if(notification.getRequestType().equals("New Customer"))
				{
					customerView.viewPersonalDetails(notification.getCustomerId());
				}
				else if(notification.getRequestType().equals("Online Transaction"))
				{
					customerView.viewPersonalDetails(notification.getCustomerId());
					customerView.viewAccountDetails(notification.getCustomerId());
				}
				else
				{
					loginController.showHistory(notification.getCustomerId());
				}
				System.out.println("Do you want to accept the request?");
				String requestResult = scanner.next();
				if(notification.getRequestType().equals("New Customer") && requestResult.equals("accepted"))
				{
					loginController.addAccount(notification.getCustomerId());
				}
					
				notification.setRequestResult(requestResult);
				loginController.addRequestReply(notification);
				System.out.println("\n");
			}
		}
	}

	public void adminValidationSuccess(Admin currAdmin)
	{
		System.out.println("------>Welcome"+currAdmin.getName()+"<------\n");
		adminMenu();
	}
	

	public void adminValidationFailure(String message)
	{
		System.out.println(message+"\n");
		adminLogin();
	}
	public void addAdminStatus(String message)
	{
		System.out.println(message+"\n");
		adminMenu();
	}
	public void viewAccountSuccess(Map<Account, CardDetails> accounts)
	{
		System.out.println("Customer Id\t|Account Number\t|IFSC Code\t|Card Number\t|Balance");
		for(Map.Entry<Account, CardDetails> account:accounts.entrySet())
		{
			System.out.printf("%-15s %-15s %-12s %-21s %.2f\n",account.getKey().getCustomerId(),account.getKey().getAccountNumber(),account.getKey().getIFSCCode(),account.getValue().getCardNumber(),account.getKey().getBalance());			
		}
		System.out.println();
	} 
	public void printHistory(List<Transaction> transactions)
	{
		System.out.println("------>View Transaction History Page<------\n");
			System.out.println("Transaction Date\tTransaction Id\tAmount  \tTransaction Type\tBalance");
			for(Transaction transaction:transactions)
			{
				System.out.printf("%-20tD%-17s%-12f%-10s%10.2f\n",transaction.getTransactionDate(),transaction.getTransactionId(),transaction.getTransactionAmount(),transaction.getTransactionType(),transaction.getCurrentBalance());
				
			}
	}
}
