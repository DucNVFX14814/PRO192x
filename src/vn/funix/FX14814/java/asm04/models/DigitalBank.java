package vn.funix.FX14814.java.asm04.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import vn.funix.FX14814.java.asm04.dao.AccountDao;
import vn.funix.FX14814.java.asm04.dao.CustomerDao;
import vn.funix.FX14814.java.asm04.exception.CustomerIdNotValidException;

public class DigitalBank extends Bank {

	public DigitalBank(String bankName) {
		super(bankName);
	}

	public void showCustomers() {
		List<Customer> customers = CustomerDao.list();
		if (customers.isEmpty()) {
			System.out.println("Chưa có khách hàng nào trong danh sách!");
			return;
		}

		for (Customer customer : customers) {
			customer.displayInformation();
			System.out.println();
		}
	}

	public void addCustomers(String fileName) {
		List<List<String>> records = TextFileService.readFile(fileName);
		if (records.isEmpty())
			return;

		List<Customer> existingCustomers = CustomerDao.list();

		for (List<String> record : records) {
			if (record.size() >= 2) {
				try {
					Customer newCustomer = new Customer(record);
					if (isValidCustomerId(newCustomer.getCustomerId())) {
						if (!isCustomerExisted(existingCustomers, newCustomer)) {
							existingCustomers.add(newCustomer);
							System.out.println(
									"Đã thêm khách hàng " + newCustomer.getCustomerId() + " vào danh sách khách hàng");
						} else {
							System.out.println("Khách hàng " + newCustomer.getCustomerId()
									+ " đã tồn tại trong danh sách, thêm khách hàng không thành công");
						}
					} else {
						System.out.println("Mã số " + newCustomer.getCustomerId() + " không hợp lệ");
					}
				} catch (CustomerIdNotValidException e) {
					System.out.println(e.getMessage());
				}
			}
		}

		CustomerDao.save(existingCustomers);
	}

	public void addSavingAccount(Scanner scanner, String customerId) {
		List<Customer> customers = CustomerDao.list();
		Customer customer = getCustomerById(customers, customerId);

		if (customer == null) {
			System.out.println("Khách hàng " + customerId + " không tồn tại!");
			return;
		}

		List<Account> accounts = AccountDao.list();
		SavingsAccount newAccount = new SavingsAccount("", 0, customerId);

		if (!newAccount.input(scanner)) {
			System.out.println("Thêm tài khoản thất bại!");
			return;
		}

		if (!isAccountExisted(accounts, newAccount)) {
			accounts.add(newAccount);
			AccountDao.save(accounts);
			
			newAccount.createTransaction(newAccount.getBalance(),
					new SimpleDateFormat(Util.DATE_FORMAT).format(new Date()), true, TransactionType.DEPOSIT);
			
			System.out.println("Thêm tài khoản thành công!");
		} else {
			System.out.println("Số tài khoản đã tồn tại!");
		}
	}

	public void withdraw(Scanner scanner, String customerId) {
		List<Customer> customers = CustomerDao.list();
		Customer customer = getCustomerById(customers, customerId);

		if (customer == null) {
			System.out.println("Khách hàng " + customerId + " không tồn tại!");
			return;
		}

		customer.displayInformation();

		customer.withdraw(scanner);
	}

	public void transfers(Scanner scanner, String customerId) {
		List<Customer> customers = CustomerDao.list();
		Customer customer = getCustomerById(customers, customerId);

		if (customer == null) {
			System.out.println("Khách hàng " + customerId + " không tồn tại!");
			return;
		}

		customer.displayInformation();

		customer.transfers(scanner);
	}

	public void displayTransactionInformation(String customerId) {
		List<Customer> customers = CustomerDao.list();
		Customer customer = getCustomerById(customers, customerId);

		if (customer == null) {
			System.out.println("Khách hàng " + customerId + " không tồn tại!");
			return;
		}

		customer.displayInformation();

		customer.displayTransactionInformation();
	}

	private boolean isValidCustomerId(String customerId) {
		return customerId != null && customerId.matches("\\d{12}");
	}

	private boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
		return customers.stream().anyMatch(cus -> cus.getCustomerId().equals(newCustomer.getCustomerId()));
	}

	private boolean isAccountExisted(List<Account> accounts, Account newAccount) {
		return accounts.stream().anyMatch(acc -> acc.getAccountNumber().equals(newAccount.getAccountNumber()));
	}

	private Customer getCustomerById(List<Customer> customers, String customerId) {
		return customers.stream().filter(cus -> cus.getCustomerId().equals(customerId)).findFirst().orElse(null);
	}
}