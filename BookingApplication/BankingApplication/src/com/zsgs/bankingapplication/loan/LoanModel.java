package com.zsgs.bankingapplication.loan;

import com.zsgs.bankingapplication.dto.Account;
import com.zsgs.bankingapplication.repository.BankingRepository;

public class LoanModel implements LoanModelCallBack {
	private LoanModelControllerCallBack loanController;

	public LoanModel(LoanModelControllerCallBack loanController) {
		this.loanController = loanController;
	}

	public void applyLoan(String accountNumber, float amount, int months)
	{
		BankingRepository.getInstance().addLoanAccount(accountNumber,amount,months);
		Account account = BankingRepository.getInstance().getAccount(accountNumber);
		BankingRepository.getInstance().addRequest(account.getCustomerId(),"Apply Loan");
		loanController.applyLoanSuccess("Your Loan application submitted successfully!!!");
	}
	public interface LoanModelControllerCallBack
	{

		void applyLoanSuccess(String message);
		
	}
}
