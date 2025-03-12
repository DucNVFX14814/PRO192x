package vn.funix.FX14814.java.asm03.models;

// Class quản lý ngân hàng tiềm năng
public class DigitalBank extends Bank {

	public DigitalBank() {
		super();
	}

	public boolean withdraw(String customerId, String accountNumber, double amount) {
		DigitalCustomer customer = (DigitalCustomer) getCustomerByCCCD(customerId);
		if (customer != null) {
			return customer.withdraw(accountNumber, amount);
		}

		System.out.println("Khách hàng " + customerId + " không tồn tại!");
		return false;
	}

	public boolean displayTransactions(String customerId) {
		DigitalCustomer customer = (DigitalCustomer) getCustomerByCCCD(customerId);
		if (customer != null) {
			customer.displayTransactions();
			return true;
		}

		System.out.println("Khách hàng " + customerId + " không tồn tại!");
		return false;
	}
	
	public boolean displayTransactionsDetail(String customerId, String accountNumber) {
		DigitalCustomer customer = (DigitalCustomer) getCustomerByCCCD(customerId);
		if (customer != null) {
			return customer.displayTransactionsDetail(accountNumber);
		}

		System.out.println("Khách hàng " + customerId + " không tồn tại!");
		return false;
	}
}
