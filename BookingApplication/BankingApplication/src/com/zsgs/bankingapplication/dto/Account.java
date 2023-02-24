package com.zsgs.bankingapplication.dto;

public class Account{
	private String customerId;
	private String accountNumber;
	private final String IFSCCode = "SK000ZS01";
	private String transactionPassword;
	private float balance;
	
	public Account(String customerId, String accountNumber,String transactionPassword, float balance) {
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.transactionPassword = transactionPassword;
		this.balance = balance;
	}
	
	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getIFSCCode() {
		return IFSCCode;
	}
	
	public class LoanAccount
	{
		private float requestAmount;
		private int dueMonths;
		private String requestStatus="pending";
		public LoanAccount(float requestAmount,int dueMonths)
		{
			this.requestAmount = requestAmount;
			this.dueMonths = dueMonths;
		}
		public float getRequestAmount() {
			return requestAmount;
		}

		public int getDueMonths() {
			return dueMonths;
		}

		public String getRequestStatus() {
			return requestStatus;
		}
		public void setRequestStatus(String requestStatus) {
			this.requestStatus = requestStatus;
		}
	}
}
