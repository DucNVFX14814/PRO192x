package vn.funix.FX14814.java.asm04.dao;

import java.util.ArrayList;
import java.util.List;

import vn.funix.FX14814.java.asm04.models.BinaryFileService;
import vn.funix.FX14814.java.asm04.models.Customer;

public class CustomerDao {
	private static final String FILE_PATH = "store/customers.dat";

	public static void save(List<Customer> customers) {
		BinaryFileService.writeFile(FILE_PATH, customers);
	}

	public static List<Customer> list() {
		List<Customer> customers = BinaryFileService.readFile(FILE_PATH);
		return customers != null ? customers : new ArrayList<>();
	}
}