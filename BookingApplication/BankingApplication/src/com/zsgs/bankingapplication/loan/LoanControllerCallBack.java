package com.zsgs.bankingapplication.loan;

public interface LoanControllerCallBack {

	void applyLoan(String accountNumber, float amount, int months);

}
