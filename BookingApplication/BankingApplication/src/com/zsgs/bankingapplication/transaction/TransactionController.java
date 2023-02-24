package com.zsgs.bankingapplication.transaction;

import com.zsgs.bankingapplication.transaction.TransactionModel.TransactionModelControllerCallBack;

public class TransactionController implements TransactionControllerCallBack, TransactionModelControllerCallBack {
	private TransactionViewCallBack transactionView;
	private TransactionModelCallBack transactionModel;
	
	public TransactionController(TransactionViewCallBack transactionView)
	{
		this.transactionView = transactionView;
		this.transactionModel = new TransactionModel(this);
	}
	
	public void requestOnlineTransaction(String accountNumber, String cardNumber, String pin)
	{
		transactionModel.requestOnlineTransaction(accountNumber, cardNumber, pin);
	}
	public void resetTransactionPassword(String accountNumber, String password, String confirmPassword)
	{
		if(password.equals(confirmPassword))
			transactionModel.resetTransactionPassword(accountNumber,password);
		else
			transactionView.passwordMismatchError();
	}
	
	public void resetPIN(String accountNumber, String cardNumber,String pin)
	{
		transactionModel.resetPIN(accountNumber,cardNumber,pin);
	}
	public void withdrawAmount(String cardNumber,String pin, float amount)
	{
		transactionModel.withdrawAmount(cardNumber,pin,amount);
	}
	
	public void depositAmount(String accountNumber, String IFSCCode, float amount)
	{
		transactionModel.depositAmount(accountNumber, IFSCCode, amount);
	}
	
	public void fundTransfer(String fromAccountNumber, String toAccountNumber, String IFSCCode,float amount)
	{
		transactionModel.fundTransfer(fromAccountNumber, toAccountNumber, IFSCCode,amount);
	}
	public void customerPasswordResetSuccess(String message)
	{
		transactionView.customerPasswordResetSuccess(message);
	}
	
	public void invalidCardDetailsError(String message)
	{
		transactionView.invalidCardDetailsError(message);
	}
	public void insufficientAmountError(String message)
	{
		transactionView.insufficientAmountError(message);
	}
	public void inValidAccountDetails(String message)
	{
		transactionView.inValidAccountDetails(message);
	}
	public void resetPinSuccess(String message)
	{
		transactionView.resetPinSuccess(message);
	}
}
