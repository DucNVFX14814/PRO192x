package vn.funix.FX14814.java.asm03.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Class Bank - Quản lý danh sách khách hàng
public class Bank {
	private String id;
	private List<Customer> customers;

	public Bank() {
		this.id = UUID.randomUUID().toString();
		this.customers = new ArrayList<>();
	}

	public boolean isCustomerExisted(String customerId) {
		for (Customer customer : customers) {
			if (customer.getCustomerId().equals(customerId)) {
				return true;
			}
		}

		return false;
	}

	public String getId() {
		return id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public Customer getCustomerByCCCD(String customerId) {
		for (Customer customer : customers) {
			if (customer.getCustomerId().equals(customerId)) {
				return customer;
			}
		}

		return null;
	}

	public Customer getCustomerByName(String name) {
		for (Customer customer : customers) {
			if (customer.getName().toUpperCase().contains(name.toUpperCase())) {
				return customer;
			}
		}

		return null;
	}

	public void addAccount(String customerId, Account account) {
		Customer customer = getCustomerByCCCD(customerId);
		if (customer != null) {
			customer.addAccount(account);
		}
	}

	public void addCustomer(Customer customer) {
		if (isCustomerExisted(customer.getCustomerId())) {
			throw new IllegalArgumentException("Khách hàng " + customer.getCustomerId() + " đã tồn tại!");
		}

		customers.add(customer);
	}

	public void displayCustomers() {
		for (Customer customer : customers) {
			customer.displayInformation();
		}
	}
}