package com.zsgs.bankingapplication.transaction;

public interface TransactionViewCallBack {

	void passwordMismatchError();

	void customerPasswordResetSuccess(String message);

	void insufficientAmountError(String message);

	void invalidCardDetailsError(String message);

	void inValidAccountDetails(String message);

	void resetPinSuccess(String message);

}
