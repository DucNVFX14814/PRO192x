package vn.funix.FX14814.java.asm03.models;

// Class quản lý thông tin khách hàng tiềm năng
public class DigitalCustomer extends Customer {

	public DigitalCustomer(String name, String customerId) {
		super(name, customerId);
	}

	public boolean withdraw(String accountNumber, double amount) {
		for (Account account : getAccounts()) {
			if (account.getAccountNumber().equals(accountNumber)) {
				if (account instanceof SavingsAccount) {
					return ((SavingsAccount) account).withdraw(amount);
				} else if (account instanceof LoansAccount) {
					return ((LoansAccount) account).withdraw(amount);
				}
			}
		}

		System.out.println("Tài khoản " + accountNumber + " không tồn tại!");
		return false;
	}

	public void displayTransactions() {
		System.out.printf("%12s | %20s | %23s | %s%n", "Account", "Amount", "Time", "Transaction ID");
		for (Account account : getAccounts()) {
			if (account instanceof SavingsAccount) {
				((SavingsAccount) account).displayTransactions();
			} else if (account instanceof LoansAccount) {
				((LoansAccount) account).displayTransactions();
			}
		}
	}

	public boolean displayTransactionsDetail(String accountNumber) {
		for (Account account : getAccounts()) {
			if (account.getAccountNumber().equals(accountNumber)) {
				if (account instanceof SavingsAccount) {
					((SavingsAccount) account).displayTransactionsDetail();
				} else if (account instanceof LoansAccount) {
					((LoansAccount) account).displayTransactionsDetail();
				}

				return true;
			}
		}

		System.out.println("Tài khoản " + accountNumber + " không tồn tại!");
		return false;
	}
}
