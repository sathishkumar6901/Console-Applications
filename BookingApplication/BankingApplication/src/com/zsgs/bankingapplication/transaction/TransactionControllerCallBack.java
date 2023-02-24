package com.zsgs.bankingapplication.transaction;

public interface TransactionControllerCallBack {

	void resetTransactionPassword(String accountNumber, String password, String confirmPassword);

	void withdrawAmount(String accountNumber, String pin, float amount);

	void depositAmount(String accountNumber, String IFSCCode, float amount);

	void fundTransfer(String fromAccountNumber, String toAccountNumber, String IFSCCode, float amount);

	void requestOnlineTransaction(String accountNumber, String cardNumber, String pin);

	void resetPIN(String accountNumber, String cardNumber, String pin);

}
