package vn.funix.FX14814.java.asm03.models;

import java.util.ArrayList;
import java.util.List;

// Class quản lý tài khoản tiết kiệm của khách hàng
public class SavingsAccount extends Account implements ReportService, Withdraw {
	private static final double SAVINGS_ACCOUNT_MIN_WITHDRAW = 50_000; // Số tiền tối thiểu có thể rút trong 1 lần
	private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000; // Số tiền tối đa có thể rút trong 1 lần
	private static final double MIN_BALANCE_AFTER_WITHDRAW = 50_000; // Hạn mức tối thiểu
	private static final double BASE_MULTIPLE = 10_000; // Bội số cơ sở
	private static final String ACCOUNT_TYPE = "SAVINGS"; // Loại tài khoản

	private List<Transaction> transactions = new ArrayList<>();

	public SavingsAccount(String accountNumber, double balance) {
		super(accountNumber, balance);
		transactions.add(new Transaction(accountNumber, ACCOUNT_TYPE, balance, balance, 0, true));
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
		return 0;
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
		return amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW && (isPremiumAccount() || amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW)
				&& (getBalance() - amount - getFee(amount)) >= MIN_BALANCE_AFTER_WITHDRAW
				&& (amount % BASE_MULTIPLE == 0);
	}

	@Override
	public String toString() {
		return String.format("%s | %20s | %7s | %14sđ", getAccountNumber(), ACCOUNT_TYPE, "",
				Util.formatAmount(getBalance()));
	}
}
