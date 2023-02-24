package com.zsgs.bankingapplication.transaction;

import java.time.LocalDate;
import java.util.UUID;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.repository.BankingRepository;

public class TransactionModel implements TransactionModelCallBack {
	private TransactionModelControllerCallBack transactionController;
	
	public TransactionModel(TransactionModelControllerCallBack transactionController)
	{
		this.transactionController = transactionController;
	}
	
	public void requestOnlineTransaction(String accountNumber, String cardNumber, String pin)
	{
		Account currAccount = BankingRepository.getInstance().isValidCard(cardNumber, pin);
		if(currAccount!=null)
		{
			BankingRepository.getInstance().addRequest(currAccount.getCustomerId(), "Online Transaction");
		}
		else
			transactionController.invalidCardDetailsError("Please ensure, Is the given card details are correct/not");
	}
	
	public void resetTransactionPassword(String accountNumber, String password)
	{
		BankingRepository.getInstance().transactionPasswordReset(accountNumber,password);
		transactionController.customerPasswordResetSuccess("Your Transaction password changed successfully!!!");
	}
	
	public void resetPIN(String accountNumber, String cardNumber,String pin)
	{
		BankingRepository.getInstance().resetPIN(accountNumber,cardNumber,pin);
		transactionController.resetPinSuccess("your card PIN resetted successfully!!!");
	}
	public void withdrawAmount(String cardNumber,String pin, float amount)
	{
		Account currAccount = BankingRepository.getInstance().isValidCard(cardNumber,pin);
		if(currAccount!=null)
		{
			if(currAccount.getBalance()<amount)
				transactionController.insufficientAmountError("Yoyr current balance is: "+currAccount.getBalance()+".\nPlease choose less than current balance");
			else
			{
				currAccount.setBalance(currAccount.getBalance()-amount);
				float currBalance =currAccount.getBalance();
				String transactionId = generateTransactionId();
				String transactionType = "debited";
				LocalDate transactionDate = LocalDate.now();
				BankingRepository.getInstance().makeTransaction(currAccount.getAccountNumber(),transactionId,transactionDate,amount,transactionType,currBalance);
			}
		}
		else
		{
			transactionController.invalidCardDetailsError("Please ensure, Is the given card details are correct/not");
		}
	}
	public void depositAmount(String accountNumber, String IFSCCode, float amount)
	{
		Account currAccount = BankingRepository.getInstance().isValidAccount(accountNumber,IFSCCode);
		if(currAccount!=null)
		{
			currAccount.setBalance(currAccount.getBalance()+amount);
			float currBalance =currAccount.getBalance();
			String transactionId = generateTransactionId();
			String transactionType = "credited";
			LocalDate transactionDate = LocalDate.now();
			BankingRepository.getInstance().makeTransaction(accountNumber,transactionId,transactionDate,amount,transactionType,currBalance);
		}
		else
			transactionController.inValidAccountDetails("Please enter a valid Account Number and IFSC Code");
	}
	public void fundTransfer(String fromAccountNumber, String toAccountNumber, String IFSCCode,float amount)
	{
		Account currAccount = BankingRepository.getInstance().isValidAccount(toAccountNumber,IFSCCode);
		if(currAccount!=null)
		{
			Account fromAccount =BankingRepository.getInstance().getAccount(fromAccountNumber);
			fromAccount.setBalance(fromAccount.getBalance()-amount);
			float currBalance =fromAccount.getBalance();
			String transactionId = generateTransactionId();
			String transactionType = "debited";
			LocalDate transactionDate = LocalDate.now();
			BankingRepository.getInstance().makeTransaction(fromAccount.getAccountNumber(),transactionId,transactionDate,amount,transactionType,currBalance);
			
			currAccount.setBalance(currAccount.getBalance()+amount);
			currBalance =currAccount.getBalance();
			transactionType = "credited";
			BankingRepository.getInstance().makeTransaction(toAccountNumber,transactionId,transactionDate,amount,transactionType,currBalance);
		}
		else
			transactionController.inValidAccountDetails("Please enter a valid Receiver Account Number and IFSC Code");
	}
	private String generateTransactionId() {
		UUID uuid = UUID.randomUUID();
        String id=uuid.toString().replace("-","");
        String transactionId = id.substring(id.length() - 10);
        return transactionId;
	}
	public interface TransactionModelControllerCallBack
	{

		void customerPasswordResetSuccess(String message);

		void resetPinSuccess(String message);

		void inValidAccountDetails(String message);

		void invalidCardDetailsError(String message);

		void insufficientAmountError(String message);
		
	}
}
