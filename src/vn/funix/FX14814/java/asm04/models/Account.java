package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.funix.FX14814.java.asm04.dao.TransactionDao;

public abstract class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final double MIN_PREMIUM_BALANCE = 10_000_000;

	protected String accountNumber;
	protected double balance;
	protected String customerId;

	public Account(String accountNumber, double balance, String customerId) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCustomerId() {
		return customerId;
	}

	public List<Transaction> getTransactions() {
		List<Transaction> allTransactions = TransactionDao.list();
		List<Transaction> accountTransactions = new ArrayList<>();
		for (Transaction transaction : allTransactions) {
			if (transaction.getAccountNumber().equals(this.accountNumber)) {
				accountTransactions.add(transaction);
			}
		}
		return accountTransactions;
	}

	public void displayTransactionsList() {
		List<Transaction> transactions = getTransactions();
		System.out.println("Lich su giao dich:");
		for (Transaction transaction : transactions) {
			System.out.println(transaction);
		}
	}

	protected Transaction createTransaction(double amount, String time, boolean status, TransactionType type) {
		Transaction transaction = new Transaction(accountNumber, amount, time, status, type);
		List<Transaction> transactions = TransactionDao.list();
		transactions.add(transaction);
		TransactionDao.save(transactions);
		return transaction;
	}

	public boolean isPremiumAccount() {
		return balance >= MIN_PREMIUM_BALANCE;
	}

	public abstract void input(Scanner scanner);
}
