package com.zsgs.bankingapplication.dto;

import java.time.LocalDate;

public class Transaction {
	private String accountNumber;
	private String transactionId;
	private LocalDate transactionDate;
	private String transactionType;
	private float transactionAmount;
	private float currentBalance;
	
	public Transaction(String accountNumber, String transactionId, LocalDate transactionDate, String transactionType,float transactionAmount, float currentBalance) {
		this.accountNumber = accountNumber;
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.currentBalance = currentBalance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public float getTransactionAmount() {
		return transactionAmount;
	}
	public float getCurrentBalance() {
		return currentBalance;
	}
	
}
