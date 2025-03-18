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
	private static final double MIN_BALANCE = 50_000;
	private static final double BASE_MULTIPLE = 10_000;
	private static final String ACCOUNT_TYPE = "SAVINGS";

	public SavingsAccount(String accountNumber, double balance, String customerId) {
		super(accountNumber, balance, customerId);
	}

	@Override
	public boolean input(Scanner scanner) {
		while (true) {
			System.out.print("Nhập số tài khoản gồm 6 chữ số: ");
			String accountNumber = scanner.nextLine();

			if (accountNumber.equalsIgnoreCase("ex") || accountNumber.equalsIgnoreCase("exit"))
				return false;

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
				String answer = scanner.nextLine();
				if (answer.equalsIgnoreCase("ex") || answer.equalsIgnoreCase("exit"))
					return false;

				double amount = Double.parseDouble(answer);
				if (amount >= MIN_BALANCE) {
					this.balance = amount;
					break;
				} else {
					System.out.println("Số dư tối thiểu " + Util.formatAmount(MIN_BALANCE) + "đ");
				}
			} catch (NumberFormatException e) {
				System.out.println("Số tiền không hợp lệ!");
			}
		}

		return true;
	}

	public double getFee(double amount) {
		return 0;
	}

	@Override
	public boolean isAccepted(double amount) {
		return amount >= SAVINGS_ACCOUNT_MIN_WITHDRAW && (isPremiumAccount() || amount <= SAVINGS_ACCOUNT_MAX_WITHDRAW)
				&& (getBalance() - amount - getFee(amount)) >= MIN_BALANCE && (amount % BASE_MULTIPLE == 0);
	}

	@Override
	public boolean withdraw(double amount) {
		if (isAccepted(amount)) {
			balance -= amount + getFee(amount);
			AccountDao.update(this);

			createTransaction(amount * -1, new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()), true,
					TransactionType.WITHDRAW);

			System.out.println("Rút tiền thành công, biên lai giao dịch:");
			log(amount, TransactionType.WITHDRAW, null);

			return true;
		}

		System.out.println("Số tiền không hợp lệ!");
		return false;
	}

	@Override
	public boolean transfers(Account receiveAccount, double amount) {
		if (isAccepted(amount)) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String confirm;

			while (true) {
				System.out.print("Xác nhận thực hiện chuyển " + Util.formatAmount(amount) + "đ từ tài khoản ["
						+ this.accountNumber + "] đến tài khoản [" + receiveAccount.getAccountNumber() + "] (Y/N): ");

				confirm = scanner.nextLine();

				if (confirm.equalsIgnoreCase("Y")) {
					balance -= amount + getFee(amount);
					AccountDao.update(this);

					String time = new SimpleDateFormat(Util.DATE_FORMAT).format(new Date());
					createTransaction(amount * -1, time, true, TransactionType.TRANSFERS);

					receiveAccount.setBalance(receiveAccount.getBalance() + amount);
					AccountDao.update(receiveAccount);

					receiveAccount.createTransaction(amount, time, true, TransactionType.TRANSFERS);

					System.out.println("Chuyển tiền thành công, biên lai giao dịch:");
					log(amount, TransactionType.TRANSFERS, receiveAccount);
					return true;
				} else if (confirm.equalsIgnoreCase("N")) {
					System.out.println("Chuyển tiền thất bại!");
					return false;
				} else {
					System.out.println("Vui lòng nhập /'Y/' hoặc /'N/'!");
				}
			}
		}

		System.out.println("Số tiền không hợp lệ!");
		return false;
	}

	@Override
	public String toString() {
		return String.format("%s | %-20s | %7s | %14sđ", getAccountNumber(), ACCOUNT_TYPE, "",
				Util.formatAmount(getBalance()));
	}

	@Override
	public void log(double amount, TransactionType type, Account receiveAccount) {
		String title = "BIÊN LAI GIAO DỊCH " + type.getDescription().toUpperCase();
		int width = Util.getDivider().length() - 2;

		int padding = (width - title.length()) / 2;
		String formattedText = " ".repeat(padding) + title + " ".repeat(width - title.length() - padding);

		System.out.println(Util.getDivider());
		System.out.printf("|" + formattedText + "|%n");
		System.out.println(Util.getDivider());
		System.out.printf("| NGÀY G/D: %49s |%n", new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()));
		System.out.printf("| ATM ID: %51s |%n", "DIGITAL-BANK-ATM 2025");
		System.out.printf("| SỐ TK: %52s |%n", accountNumber);
		if (type == TransactionType.TRANSFERS) {
			System.out.printf("| SỐ TK NHẬN: %47s |%n", receiveAccount.getAccountNumber());
		}
		System.out.printf("| SỐ TIỀN: %49sđ |%n", Util.formatAmount(amount));
		System.out.printf("| SỐ DƯ: %51sđ |%n", Util.formatAmount(balance));
		System.out.printf("| PHÍ + VAT: %47sđ |%n", "0");
		System.out.println(Util.getDivider());
	}
}