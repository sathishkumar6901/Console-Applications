package com.zsgs.bankingapplication.dto;

public class CardDetails{
	private String customerId;
	private String accountNumber;
	private String cardNumber;
	private String pinNumber;
	private String cvvNumber;
	
	public CardDetails(String customerId, String accountNumber, String cardNumber, String pinNumber, String cvvNumber) {
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.cardNumber = cardNumber;
		this.pinNumber = pinNumber;
		this.cvvNumber = cvvNumber;
	}

	public String getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCvvNumber() {
		return cvvNumber;
	}
}
