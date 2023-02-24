package com.zsgs.bankingapplication.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.zsgs.bankingapplication.dto.Customer;
import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Admin;
import com.zsgs.bankingapplication.dto.AdminCredentials;
import com.zsgs.bankingapplication.dto.CustomerCredentials;
import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;
import com.zsgs.bankingapplication.dto.CardDetails;


public class BankingRepository extends PasswordProtection{

		private static BankingRepository bankingDbInstance;
		
		private List<AdminCredentials> adminCredentials = new ArrayList<>();
		private List<CustomerCredentials> customerCredentials = new ArrayList<>();
		private List<Transaction> transactions = new ArrayList<>();
		private List<Account.LoanAccount> loanAccounts = new ArrayList<>();
		private List<Notification> notifications = new ArrayList<>();
		private Map<Account, CardDetails> accounts = new HashMap<>();
		
		public static BankingRepository getInstance()
		{
			if(bankingDbInstance==null)
			{
				bankingDbInstance = new BankingRepository();
				bankingDbInstance.addAdmin();
			}
			return bankingDbInstance;
		}

		private void addAdmin() {
			adminCredentials.add(new AdminCredentials("001","SathishKrishna",bankingDbInstance.toEncrypt("zsgs")));
		}

		public Admin checkValidAdmin(String id, String password) {
			for(AdminCredentials credential:adminCredentials)
			{
				if(credential.getId().equals(id) && password.equals(bankingDbInstance.toDecrypt(credential.getPassword())))
					return credential;
			}
			return null;
		}
		public Admin isAdminIdExist(String id) {
			for(AdminCredentials credential:adminCredentials)
			{
				if(credential.getId().equals(id))
					return credential;
			}
			return null;
		}
		
		public void addAdmin(String id, String name, String password) {
			String encryptedPassword = bankingDbInstance.toEncrypt(password);
			adminCredentials.add(new AdminCredentials(id,name,encryptedPassword));
		}
		public Map<Account, CardDetails> getAccountsDetails() {
			return accounts;
		}
		
		public Customer checkValidCustomer(String id, String password) {
			for(CustomerCredentials credential:customerCredentials)
				if(credential.getCustomerId().equals(id) && password.equals(bankingDbInstance.toDecrypt(credential.getPassword())))
						return credential;
			return null;
		}
		
		public Account checkValidAccount(String customerId) {
			for(Account account:accounts.keySet())
				if(account.getCustomerId().equals(customerId))
						return account;
			return null;
		}
		
		public Customer isCustomerExist(String aadharNumber) {
			for(CustomerCredentials credential:customerCredentials)
				if(credential.getAadharNumber().equals(aadharNumber))
						return credential;
			return null;
		}

		public void addNewCustomer(String customerId,String name, String password, int age, String phoneNumber, String aadharNumber) {
			String encryptedPassword = bankingDbInstance.toEncrypt(password);
			customerCredentials.add(new CustomerCredentials(customerId,name,encryptedPassword,age,phoneNumber,aadharNumber));			
		}

		public void customerLoginPasswordReset(String customerId, String password) {
			for(CustomerCredentials currCustomer:customerCredentials)
				if(currCustomer.getCustomerId().equals(customerId))
				{
					String encryptedPassword = bankingDbInstance.toEncrypt(password);
					currCustomer.setPassword(encryptedPassword);
					return;
				}			
		}

		public void transactionPasswordReset(String accountNumber, String password) {
			for(Account account:accounts.keySet())
				if(account.getAccountNumber().equals(accountNumber))
				{
					String encryptedPassword = bankingDbInstance.toEncrypt(password);
					account.setTransactionPassword(encryptedPassword);
					return;
				}
		}

		public Customer getCurrentCustomer(String customerId) {
			for(CustomerCredentials currCustomer:customerCredentials)
				if(currCustomer.getCustomerId().equals(customerId))
					return currCustomer;
			return null;
		}

		public Entry<Account, CardDetails> getCurrentAccount(String customerId) {
			for(Map.Entry<Account, CardDetails> account:accounts.entrySet())
			{
				if(account.getKey().getCustomerId().equals(customerId))
						return account;
			}
			return null;
		}

		public void makeTransaction(String accountNumber, String transactionId, LocalDate transactionDate,float amount, String transactionType, float currBalance) {
			transactions.add(new Transaction(accountNumber, transactionId, transactionDate,transactionType,amount,currBalance));
		}

		public Account isValidCard(String cardNumber, String pin) {
			for(Map.Entry<Account, CardDetails> account:accounts.entrySet())
			{
				if(account.getValue().getCardNumber().equals(cardNumber) && pin.equals(bankingDbInstance.toDecrypt(account.getValue().getPinNumber())))
					return account.getKey();
			}
			return null;
		}

		public Account isValidAccount(String accountNumber, String IFSCCode) {
			for(Account account:accounts.keySet())
				if(account.getAccountNumber().equals(accountNumber) && account.getIFSCCode().equals(IFSCCode))
						return account;
			return null;
		}

		public Account getAccount(String fromAccountNumber) {
			for(Account account:accounts.keySet())
				if(account.getAccountNumber().equals(fromAccountNumber))
						return account;
			return null;
		}

		public List<Transaction> getTransactions(String accountNumber) {
			List<Transaction> account = new ArrayList<>();
			for(Transaction transaction:transactions)
				if(transaction.getAccountNumber().equals(accountNumber))
						account.add(transaction);
			return account;
		}

		public void addRequest(String customerId, String requestType) {
			notifications.add(new Notification(customerId,requestType));
		}

		public void addLoanAccount(String accountNumber, float amount, int months) {
			Account currAccount = getAccount(accountNumber);
			if(currAccount!=null)
				loanAccounts.add(currAccount.new LoanAccount(amount,months));
		}

		public List<Notification> getCustomerNotifications(String customerId) {
			List<Notification> currNotifications = new ArrayList<>();
			for(Notification notification:notifications)
			{
				if(notification.getCustomerId().equals(customerId) && notification.getViewedResult().equals("unseen") &&(!notification.getRequestResult().equals("pending")))
					currNotifications.add(notification);
			}
			return currNotifications;
		}

		public List<Notification> getAdminNotifications() {
			List<Notification> list = new ArrayList<>();
			for(Notification notification:notifications)
			{
				if(notification.getRequestResult().equals("pending"))
					list.add(notification);
			}
			return list;
		}

		public void addNewAccount(String customerId, String accountNumber, String transactionPassword,
				String cardNumber, String cvvNumber) {
			String encryptedPassword = bankingDbInstance.toEncrypt(transactionPassword);
			String encryptedPin = bankingDbInstance.toEncrypt("1234");
			accounts.put(new Account(customerId,accountNumber,encryptedPassword,0),new CardDetails(customerId,accountNumber,cardNumber,encryptedPin,cvvNumber));
			
		}

		public void resetPIN(String accountNumber, String cardNumber, String pin) {
			for(Map.Entry<Account, CardDetails> account:accounts.entrySet())
			{
				if(account.getKey().getAccountNumber().equals(accountNumber) && account.getValue().getCardNumber().equals(cardNumber))
				{
					account.getValue().setPinNumber(bankingDbInstance.toEncrypt(pin));
					return;
				}
			}
			return;
		}

		public void addRequestReply(Notification notification) {
			notification.setReplyMessage(notification.getCustomerId()+" Your "+notification.getRequestType()+" request "+notification.getRequestResult()+" by admin.\n"+"For further details contact our bank....\n");
		}	
}
