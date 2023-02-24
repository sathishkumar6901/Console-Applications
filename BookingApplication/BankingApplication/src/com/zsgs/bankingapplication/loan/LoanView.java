package com.zsgs.bankingapplication.loan;

import java.util.Scanner;

public class LoanView implements LoanViewCallBack{
	private LoanControllerCallBack loanController;
	private Scanner scanner = new Scanner(System.in);
	public LoanView()
	{
		loanController = new LoanController(this);
	}
	
	public void loanMenu()
	{
		System.out.println("------>Loan Application Page<------\n");
		System.out.println("Note: Whether you create a account in past 3 months, you are not eligible to apply loan....\n");
		System.out.println("1.Apply Loan \n2.Exit \n");
		System.out.println("Enter your Choice:");
		int choice = scanner.nextInt();
		if(choice==1)
		{
			applyLoan();
			return;
		}
	}

	private void applyLoan() {
		System.out.println("Enter your Account Number:");
		String accountNumber = scanner.next();
		System.out.println("Enter the Loan amount you want:");
		float amount = scanner.nextFloat();
		System.out.println("How many months you want to paid the loan amount:");
		int months = scanner.nextInt();
		loanController.applyLoan(accountNumber,amount,months);
	}
	
	public void applyLoanSuccess(String message)
	{
		System.out.println(message+"\n");
		System.out.println("Please check your message regularly...");
	}
}
