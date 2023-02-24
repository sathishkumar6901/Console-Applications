package com.zsgs.bankingapplication.loan;

import com.zsgs.bankingapplication.loan.LoanModel.LoanModelControllerCallBack;

public class LoanController implements LoanControllerCallBack, LoanModelControllerCallBack {
	private LoanViewCallBack loanView;
	private LoanModelCallBack loanModel;
	
	public LoanController(LoanViewCallBack loanView) {
		this.loanView = loanView;
		this.loanModel = new LoanModel(this);
	}

	public void applyLoan(String accountNumber, float amount, int months)
	{
		loanModel.applyLoan(accountNumber, amount, months);
	}
	
	public void applyLoanSuccess(String message)
	{
		loanView.applyLoanSuccess(message);
	}
}
