package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import vn.funix.FX14814.java.asm04.dao.AccountDao;

public class Customer extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String customerId;
	private String name;

	public Customer(String customerId, String name) {
		super(customerId, name);
	}

	public Customer(List<String> values) {
		super(values.get(0), values.get(1));
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return AccountDao.list().stream().filter(account -> account.getCustomerId().equals(this.customerId))
				.collect(Collectors.toList());
	}

	public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
		return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst()
				.orElse(null);
	}

	public void displayInformation() {
		System.out.printf("%s | %s | %d tài khoản\n", customerId, name, getAccounts().size());
		for (Account account : getAccounts()) {
			System.out.printf("%s | %,.0f đ\n", account.getAccountNumber(), account.getBalance());
		}
	}

	public void withdraw(Scanner scanner) {
		List<Account> accounts = getAccounts();
		Account account;
		double amount;
		if (accounts.isEmpty()) {
			System.out.println("Khách hàng chưa có tài khoản nào, thao tác không thành công");
			return;
		}

		while (true) {
			System.out.print("Nhập số tài khoản: ");
			account = getAccountByAccountNumber(accounts, scanner.nextLine());
			if (account != null) {
				break;
			} else {
				System.out.println("Số tài khoản không tồn tại!\nNhập lại số tài khoản: ");
			}
		}

		while (true) {
			try {
				System.out.print("Nhập số tiền rút: ");
				amount = Double.parseDouble(scanner.nextLine());
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
		Account fromAccount = accounts.stream().filter(acc -> acc.getAccountNumber().equals(fromAccountNumber))
				.findFirst().orElse(null);

		if (fromAccount == null) {
			System.out.println("Số tài khoản không tồn tại!");
			return;
		}

		System.out.print("Nhập STK nhận: ");
		String toAccountNumber = scanner.nextLine();
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
			System.out.print("Nhập số tiền: ");
			try {
				double amount = Double.parseDouble(scanner.nextLine());
				((SavingsAccount) fromAccount).transfers(toAccount, amount);
			} catch (NumberFormatException e) {
				System.out.println("Số tiền không hợp lệ!");
			}
		}
	}

	public void displayTransactionInformation() {
		System.out.printf("%s | %s | %d tài khoản\n", customerId, name, getAccounts().size());
		for (Account account : getAccounts()) {
			if (account instanceof SavingsAccount) {
				((SavingsAccount) account).displayTransactionsList();
			}
		}
	}
}