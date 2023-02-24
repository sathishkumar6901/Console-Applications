package com.zsgs.bankingapplication.customer;

import java.util.Map.Entry;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.dto.CardDetails;
import com.zsgs.bankingapplication.dto.Customer;

public interface CustomerViewCallBack {

	void addNewCustomerSuccess(String message);

	void addNewCustomerFailure(String message);

	void noAccountNumberError(String message);

	void customerLoginSuccess(String message, String customerId);

	void customerLoginFailure(String message);

	void passwordMismatchError();

	void customerPasswordResetSuccess(String message);

	void printPersonalDetails(Customer currCustomer);

	void printAccountDetails(Entry<Account, CardDetails> currAccount);

}
