package vn.funix.FX14814.java.asm03.models;

import java.util.ArrayList;
import java.util.List;

// Class quản lý tài khoản tiết kiệm của khách hàng
public class LoansAccount extends Account implements ReportService, Withdraw {
	private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05; // Phí giao dịch đối với tài khoản thường
	private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01; // Phí giao dịch đối với tài khoản Premium
	private static final double MIN_BALANCE_AFTER_WITHDRAW = 50_000; // Hạn mức tối thiểu
	private static final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000; // Hạn mức tối đa
	private static final String ACCOUNT_TYPE = "LOANS"; // Loại tài khoản

	private List<Transaction> transactions = new ArrayList<>();

	public LoansAccount(String accountNumber, double balance) {
		super(accountNumber, getCheckedMaxBalance(balance));
		transactions.add(new Transaction(accountNumber, ACCOUNT_TYPE, balance, balance, 0, true));
	}

	private static double getCheckedMaxBalance(double balance) {
		if (balance > LOAN_ACCOUNT_MAX_BALANCE) {
			throw new IllegalArgumentException(
					"Hạn mức không được quá giới hạn " + Util.formatAmount(LOAN_ACCOUNT_MAX_BALANCE) + "đ!");
		}

		return balance;
	}

	public void displayTransactions() {
		for (Transaction transaction : transactions) {
			if (transaction.getStatus()) {
				transaction.displayTransaction();
			}
		}
	}

	public void displayTransactionsDetail() {
		for (Transaction transaction : transactions) {
			if (transaction.getStatus()) {
				transaction.displayTransactionDetail();
			}
		}
	}

	public double getFee(double amount) {
		return isPremiumAccount() ? amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : amount * LOAN_ACCOUNT_WITHDRAW_FEE;
	}

	@Override
	public void log(Transaction transaction) {
		transaction.displayTransactionDetail();
	}

	@Override
	public boolean withdraw(double amount) {
		if (isAccepted(amount)) {
			setBalance(getBalance() - amount - getFee(amount));
			Transaction transaction = new Transaction(getAccountNumber(), ACCOUNT_TYPE, amount * -1, getBalance(),
					getFee(amount), true);
			transactions.add(transaction);
			log(transaction);
			return true;
		} else {
			transactions.add(new Transaction(getAccountNumber(), ACCOUNT_TYPE, amount * -1, getBalance(),
					getFee(amount), false));
			return false;
		}
	}

	@Override
	public boolean isAccepted(double amount) {
		return (getBalance() - amount - getFee(amount)) >= MIN_BALANCE_AFTER_WITHDRAW;
	}

	@Override
	public String toString() {
		return String.format("%s | %20s | %7s | %14sđ", getAccountNumber(), ACCOUNT_TYPE, "",
				Util.formatAmount(getBalance()));
	}
}
