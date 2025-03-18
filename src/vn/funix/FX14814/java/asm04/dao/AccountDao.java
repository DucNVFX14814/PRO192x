package vn.funix.FX14814.java.asm04.dao;

import java.util.ArrayList;
import java.util.List;

import vn.funix.FX14814.java.asm04.models.Account;
import vn.funix.FX14814.java.asm04.models.BinaryFileService;

public class AccountDao {
	private static final String FILE_PATH = "store/accounts.dat";

	public static void save(List<Account> accounts) {
		BinaryFileService.writeFile(FILE_PATH, accounts);
	}

	public static List<Account> list() {
		List<Account> accounts = BinaryFileService.readFile(FILE_PATH);
		return accounts != null ? accounts : new ArrayList<>();
	}

	public static void update(Account editAccount) {
		List<Account> accounts = list();
		boolean hasExist = accounts.stream()
				.anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));

		List<Account> updatedAccounts;
		if (!hasExist) {
			updatedAccounts = new ArrayList<>(accounts);
			updatedAccounts.add(editAccount);
		} else {
			updatedAccounts = new ArrayList<>();
			for (Account account : accounts) {
				if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
					updatedAccounts.add(editAccount);
				} else {
					updatedAccounts.add(account);
				}
			}
		}

		save(updatedAccounts);
	}
}
