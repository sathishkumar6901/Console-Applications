package com.zsgs.bankingapplication.customer;

import java.util.List;

import com.zsgs.bankingapplication.dto.Notification;
import com.zsgs.bankingapplication.dto.Transaction;

public interface CustomerControllerCallBack {

	void addNewCustomer(String name, int age, String phoneNumber, String aadharNumber);

	void checkValidCustomer(String id, String password);

	void resetLoginPassword(String customerId, String password,String confirmPassword);

	List<Notification> getCustomerNotifications(String customerId);

	float viewBalance(String accountNumber);

	List<Transaction> viewTransactions(String accountNumber);

	void changeViewedStatus(Notification notification);

	void viewPersonalDetails(String customerId);

	void viewAccountDetails(String customerId);

}
