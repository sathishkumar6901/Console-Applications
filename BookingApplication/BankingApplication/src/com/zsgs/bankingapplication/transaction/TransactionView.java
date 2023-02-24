package com.zsgs.bankingapplication.transaction;

import java.util.Scanner;

public class TransactionView implements TransactionViewCallBack{
	private TransactionControllerCallBack transactionController;
	private Scanner scanner = new Scanner(System.in);
	public TransactionView()
	{
		transactionController = new TransactionController(this);
		
	}
	public void transactionMenu() {
		boolean repeat = true;
		do {
			System.out.println("------>Transaction Menu<------\n");
			System.out.println("1.Request Online Transaction \n2.Reset Transaction Password \n3.Reset PIN \n4.Withdraw \n5.Deposit \n6.Fund Transfer \n7.Exit\n");
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch(choice) 
			{
				case 1: requestOnlineTransaction();
						break;
				case 2: resetTransactionPassword(); 
						break;
				case 3: resetPIN();
						break;
				case 4: withdrawAmount();
						break;
				case 5: depositAmount();
						break;
				case 6: fundTransfer();
						break;
				case 7: repeat=false;
						break;
			}
			
		}while(repeat);
		
	}

	private void resetPIN() {
		System.out.println("------>Reset Card PIN page<------\n");
		System.out.println("Enter your Account Number:");
		String accountNumber = scanner.next();
		System.out.println("Enter your Card Number:");
		String cardNumber = scanner.next();
		System.out.println("Enter PIN you want to reset:");
		String pin = scanner.next();
		transactionController.resetPIN(accountNumber,cardNumber,pin);
	}
	private void requestOnlineTransaction() {
		System.out.println("------>Request Online Transaction Page<------\n");
		System.out.println("Enter your Account Number:");
		String accountNumber = scanner.next();
		System.out.println("Enter your Card Number:");
		String cardNumber = scanner.next();
		System.out.println("Enter your PIN:");
		String pin = scanner.next();
		transactionController.requestOnlineTransaction(accountNumber,cardNumber,pin);
	}
	private void fundTransfer() {
		System.out.println("------>Fund Transaction Page<------\n");
		System.out.println("Enter your Account Number:");
		String fromAccountNumber = scanner.next();
		System.out.println("Enter Receiver Account Number:");
		String toAccountNumber = scanner.next();
		System.out.println("Enter Receiver's IFSC Code:");
		String IFSCCode =scanner.next();
		System.out.println("Enter the amount to be transfer:");
		float amount = scanner.nextFloat();
		transactionController.fundTransfer(fromAccountNumber,toAccountNumber,IFSCCode,amount);
	}
	private void depositAmount() {
		System.out.println("----->Amount Deposit Page<-----\n");
		System.out.println("Enter your Account Number:");
		String accountNumber = scanner.next();
		System.out.println("Enter your IFSc Code:");
		String IFSCCode = scanner.next();
		System.out.println("Enter the amount you want to deposit:");
		float amount = scanner.nextFloat();
		transactionController.depositAmount(accountNumber,IFSCCode,amount);
		
	}
	private void withdrawAmount() {
		System.out.println("----->Amount Withdraw Page<-----\n");
		System.out.println("Enter your Card Number:");
		String cardNumber = scanner.next();
		System.out.println("ENter your PIN:");
		String pin = scanner.next();
		System.out.println("Enter the amount you want to withdraw:");
		float amount = scanner.nextFloat();
		transactionController.withdrawAmount(cardNumber,pin,amount);
		
		
	}
	private void resetTransactionPassword() {
		System.out.println("----->Transaction Password Reset Page<-----\n");
		System.out.println("Enter your Account Number:");
		String accountNumber = scanner.next();
		System.out.println("Enter the transaction password you want to reset:");
		String password = scanner.next();
		System.out.println("Enter the confirm password as same as the password:");
		String confirmPassword = scanner.next();
		transactionController.resetTransactionPassword(accountNumber,password,confirmPassword);
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
	
	public void invalidCardDetailsError(String message)
	{
		System.out.println(message+"\n");
	}
	public void insufficientAmountError(String message)
	{
		System.out.println(message+"\n");
	}
	public void inValidAccountDetails(String message)
	{
		System.out.println(message+"\n");
	}
	public void resetPinSuccess(String message)
	{
		System.out.println(message+"\n");
	}
}
