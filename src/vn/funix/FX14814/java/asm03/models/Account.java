package vn.funix.FX14814.java.asm03.models;

//Class Account - Quản lý tài khoản
public class Account {
	private static final double MIN_PREMIUM_BALANCE = 10_000_000;
	private static final double MIN_BALANCE = 50_000;

	private String accountNumber;
	private double balance;

	public Account(String accountNumber, double balance) {
		checkValidAccountNumber(accountNumber);
		checkMinBalance(balance);

		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public void checkValidAccountNumber(String accountNumber) {
		if (accountNumber == null || !accountNumber.matches("\\d{6}")) {
			throw new IllegalArgumentException("STK không hợp lệ!");
		}
	}

	private void checkMinBalance(double balance) {
		if (balance < MIN_BALANCE) {
			throw new IllegalArgumentException("Số dư TK không dưới " + Util.formatAmount(MIN_BALANCE) + "đ!");
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		checkValidAccountNumber(accountNumber);

		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		checkMinBalance(balance);

		this.balance = balance;
	}

	public boolean isPremiumAccount() {
		return balance >= MIN_PREMIUM_BALANCE;
	}

	@Override
	public String toString() {
		return String.format("%s | %30s |%15sđ", accountNumber, "", Util.formatAmount(balance));
	}
}