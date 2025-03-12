package vn.funix.FX14814.java.asm03.models;

import java.util.ArrayList;
import java.util.List;

//Class Customer - Kế thừa User, quản lý tài khoản
public class Customer extends User {
	private List<Account> accounts = new ArrayList<>();

	public Customer(String name, String customerId) {
		super(name, customerId);
	}

	public boolean isAccountExisted(String accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return true;
			}
		}

		return false;
	}

	public Account getAccountByAccountNumber(String accountNumber) {
		for (Account account : accounts) {
			if (account.getAccountNumber().equals(accountNumber)) {
				return account;
			}
		}

		return null;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void addAccount(Account account) {
		if (isAccountExisted(account.getAccountNumber())) {
			throw new IllegalArgumentException("Tài khoản " + account.getAccountNumber() + " đã tồn tại!");
		}

		accounts.add(account);
	}

	public boolean isPremiumCustomer() {
		for (Account acc : accounts) {
			if (acc.isPremiumAccount()) {
				return true;
			}
		}

		return false;
	}

	public double getBalance() {
		double sumBalance = 0;
		for (Account acc : accounts) {
			sumBalance += acc.getBalance();
		}

		return sumBalance;
	}

	public void displayInformation() {
		String formattedStr = String.format("%-12s | %20s | %7s | %14sđ", getCustomerId(), getName(),
				(isPremiumCustomer() ? "Premium" : "Normal"), Util.formatAmount(getBalance()));
		System.out.println(formattedStr);

		int count = 0;
		for (Account acc : accounts) {
			count++;
			System.out.printf("%-5d %s\n", count, acc);
		}
	}
}