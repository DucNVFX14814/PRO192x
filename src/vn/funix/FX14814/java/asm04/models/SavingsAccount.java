package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import vn.funix.FX14814.java.asm04.dao.AccountDao;

public class SavingsAccount extends Account implements IReport, IWithdraw, ITransfer, Serializable {
	private static final long serialVersionUID = 1L;
	private static final double SAVINGS_ACCOUNT_MIN_WITHDRAW = 50_000;
	private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000;
	private static final double MIN_BALANCE_AFTER_WITHDRAW = 50_000;
	private static final double BASE_MULTIPLE = 10_000;
	private static final String ACCOUNT_TYPE = "SAVINGS";

	public SavingsAccount(String accountNumber, double balance, String customerId) {
		super(accountNumber, balance, customerId);
	}

	@Override
	public void input(Scanner scanner) {
		while (true) {
			System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
			String accountNumber = scanner.nextLine();
			if (accountNumber.matches("\\d{6}")) {
				this.accountNumber = accountNumber;
				break;
			} else {
				System.out.println("Số tài khoản không hợp lệ!");
			}
		}

		while (true) {
			System.out.print("Nhập số dư: ");
			try {
				double amount = Double.parseDouble(scanner.nextLine());
				if (isAccepted(amount)) {
					this.balance = amount;
					createTransaction(amount, new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()), true,
							TransactionType.DEPOSIT);
					break;
				} else {
					System.out.println("Giao dịch thất bại!");
				}
			} catch (NumberFormatException e) {
				System.out.println("Số tiền không hợp lệ!");
			}
		}
	}

	@Override
	public void log(double amount, TransactionType type, Account receiveAccount) {
		String transactionType = "";

		if (type == TransactionType.DEPOSIT) {
			transactionType = "NẠP TIỀN";
		} else if (type == TransactionType.TRANSFER) {
			transactionType = "CHUYỂN TIỀN";
		} else if (type == TransactionType.WITHDRAW) {
			transactionType = "RÚT TIỀN";
		}

		System.out.printf("+----------+----------------------+\n");
		System.out.printf("| BIÊN LAI GIAO DỊCH " + transactionType + " |\n");
		System.out.printf("+----------+----------------------+\n");
		System.out.printf("| NGÀY G/D: %s\n", new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()));
		System.out.printf("| ATM ID: DIGITAL-BANK-ATM 2025  |\n");
		System.out.printf("| SỐ TK: %s\n", accountNumber);
		if (type == TransactionType.TRANSFER) {
			System.out.printf("| SỐ TK NHẬN: %s\n", receiveAccount.getAccountNumber());
		}
		System.out.printf("| SỐ TIỀN: %,.0f đ\n", amount);
		System.out.printf("| SỐ DƯ: %,.0f đ\n", balance);
		System.out.printf("| PHÍ + VAT: 0đ\n");
		System.out.printf("+----------+----------------------+\n");
	}

	@Override
	public boolean isAccepted(double amount) {
		return amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW && (isPremiumAccount() || amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW)
				&& (getBalance() - amount - getFee(amount)) >= MIN_BALANCE_AFTER_WITHDRAW
				&& (amount % BASE_MULTIPLE == 0);
	}

	@Override
	public boolean withdraw(double amount) {
		if (isAccepted(amount)) {
			balance -= amount + getFee(amount);
			AccountDao.update(this);

			createTransaction(amount, new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()), true,
					TransactionType.WITHDRAW);

			log(amount, TransactionType.WITHDRAW, null);

			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean transfers(Account receiveAccount, double amount) {
		if (isAccepted(amount)) {
			System.out.print("Xác nhận thực hiện chuyển " + String.format("%,.0f", amount) + "đ đến tài khoản "
					+ receiveAccount.getAccountNumber() + " (Y/N): ");
			Scanner scanner = new Scanner(System.in);
			String confirm = scanner.nextLine();

			if (confirm.equalsIgnoreCase("Y")) {
				balance -= amount + getFee(amount);
				AccountDao.update(this);

				createTransaction(amount, new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()), true,
						TransactionType.TRANSFER);

				receiveAccount.setBalance(receiveAccount.getBalance() + amount);
				AccountDao.update(receiveAccount);

				receiveAccount.createTransaction(amount, new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()),
						true, TransactionType.DEPOSIT);

				log(amount, TransactionType.TRANSFER, receiveAccount);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s | %20s | %7s | %14sđ", getAccountNumber(), ACCOUNT_TYPE, "",
				Util.formatAmount(getBalance()));
	}

	public double getFee(double amount) {
		return 0;
	}
}