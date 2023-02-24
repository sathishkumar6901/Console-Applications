package com.zsgs.bankingapplication.login;

import java.util.List;
import java.util.Map;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.Admin;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Transaction;

public interface LoginViewCallBack {
	void mainMenu();

	void adminValidationSuccess(Admin currAdmin);

	void adminValidationFailure(String message);

	void viewAccountSuccess(Map<Account, CardDetails> accounts);

	void addAdminStatus(String message);

	void printHistory(List<Transaction> transactions);
}
