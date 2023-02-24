package com.zsgs.bankingapplication.loan;

public interface LoanModelCallBack {

	void applyLoan(String accountNumber, float amount, int months);

}
