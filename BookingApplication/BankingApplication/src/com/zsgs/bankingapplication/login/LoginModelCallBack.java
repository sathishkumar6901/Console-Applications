package com.zsgs.bankingapplication.login;

import java.util.List;

import com.zsgs.bankingapplication.dto.Notification;

public interface LoginModelCallBack {

	void checkValidAdmin(String id, String password);

	void viewAccounts();

	void addNewAdmin(String id, String name, String password);

	List<Notification> getAdminNotifications();

	void showHistory(String customerId);

	void addAccount(String customerId, String accountNumber, String cardNumber, String cvvNumber);

	void addRequestReply(Notification notification);

}
