package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import vn.funix.FX14814.java.asm04.dao.AccountDao;

public class Customer extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
	}

	public Customer(String customerId, String name) {
		super(customerId, name);
	}

	public Customer(List<String> values) {
		super(values.get(0), values.get(1));
	}

	public List<Account> getAccounts() {
		return AccountDao.list().stream().filter(account -> account.getCustomerId().equals(this.customerId))
				.collect(Collectors.toList());
	}

	public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
		return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst()
				.orElse(null);
	}

	public boolean isPremiumCustomer(List<Account> accounts) {
		if (accounts.isEmpty()) {
			accounts = getAccounts();
		}

		for (Account acc : accounts) {
			if (acc.isPremiumAccount()) {
				return true;
			}
		}

		return false;
	}

	public double getBalance(List<Account> accounts) {
		if (accounts.isEmpty()) {
			accounts = getAccounts();
		}

		double sumBalance = 0;
		for (Account acc : accounts) {
			sumBalance += acc.getBalance();
		}

		return sumBalance;
	}

	public void displayInformation() {
		List<Account> accounts = getAccounts();
		String formattedStr = String.format("%-12s | %-20s | %-7s | %14sđ", getCustomerId(), getName(),
				(isPremiumCustomer(accounts) ? "Premium" : "Normal"), Util.formatAmount(getBalance(accounts)));
		System.out.println(formattedStr);

		int count = 0;
		for (Account acc : accounts) {
			count++;
			System.out.printf("%-5d %s\n", count, acc);
		}
	}

	public void withdraw(Scanner scanner) {
		Account account;
		double amount;
		List<Account> accounts = getAccounts();
		if (accounts.isEmpty()) {
			System.out.println("Khách hàng chưa có tài khoản nào, thao tác không thành công");
			return;
		}

		while (true) {
			System.out.print("Nhập số tài khoản: ");
			String accountNumber = scanner.nextLine();

			if (accountNumber.equalsIgnoreCase("ex") || accountNumber.equalsIgnoreCase("exit"))
				return;

			account = getAccountByAccountNumber(accounts, accountNumber);

			if (account != null) {
				break;
			} else {
				System.out.println("Số tài khoản không tồn tại!%nNhập lại số tài khoản: ");
			}
		}

		while (true) {
			try {
				System.out.print("Nhập số tiền rút: ");
				String answer = scanner.nextLine();
				if (answer.equalsIgnoreCase("ex") || answer.equalsIgnoreCase("exit"))
					return;

				amount = Double.parseDouble(answer);
				
				if (amount > 0) {
					break;
				} else {
					System.out.println("Số tiền phải lớn hơn 0!\nNhập lại số tiền rút: ");
				}
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
			}
		}

		if (account instanceof SavingsAccount) {
			((SavingsAccount) account).withdraw(amount);
		}
	}

	public void transfers(Scanner scanner) {
		List<Account> accounts = getAccounts();
		if (accounts.isEmpty()) {
			System.out.println("Khách hàng chưa có tài khoản nào!");
			return;
		}

		System.out.print("Nhập STK chuyển: ");
		String fromAccountNumber = scanner.nextLine();

		if (fromAccountNumber.equalsIgnoreCase("ex") || fromAccountNumber.equalsIgnoreCase("exit"))
			return;

		Account fromAccount = accounts.stream().filter(acc -> acc.getAccountNumber().equals(fromAccountNumber))
				.findFirst().orElse(null);

		if (fromAccount == null) {
			System.out.println("Số tài khoản không tồn tại!");
			return;
		}

		System.out.print("Nhập STK nhận: ");
		String toAccountNumber = scanner.nextLine();

		if (toAccountNumber.equalsIgnoreCase("ex") || toAccountNumber.equalsIgnoreCase("exit"))
			return;

		Account toAccount = AccountDao.list().stream().filter(acc -> acc.getAccountNumber().equals(toAccountNumber))
				.findFirst().orElse(null);

		if (toAccount == null) {
			System.out.println("Số tài khoản nhận không tồn tại!");
			return;
		}

		if (fromAccountNumber.equals(toAccountNumber)) {
			System.out.println("Không thể chuyển tiền cho chính mình!");
			return;
		}

		if (fromAccount instanceof SavingsAccount) {
			System.out.print("Nhập số tiền chuyển: ");
			try {
				String answer = scanner.nextLine();
				if (answer.equalsIgnoreCase("ex") || answer.equalsIgnoreCase("exit"))
					return;

				double amount = Double.parseDouble(answer);
				((SavingsAccount) fromAccount).transfers(toAccount, amount);
			} catch (NumberFormatException e) {
				System.out.println("Số tiền không hợp lệ!");
			}
		}
	}

	public void displayTransactionInformation() {
		System.out.printf("%n%-12s | %-10s | %-17s | %-19s | %s%n", "Account", "Type", "Amount", "Time", "Transaction ID");
		for (Account account : getAccounts()) {
			if (account instanceof SavingsAccount) {
				((SavingsAccount) account).displayTransactionsList();
//			} else if (account instanceof LoansAccount) {
//				((LoansAccount) account).displayTransactions();
			}
		}
	}

	@Override
	public String toString() {
		return String.format("Customer {name = %s, customerId = %s}", name, customerId);
	}
}