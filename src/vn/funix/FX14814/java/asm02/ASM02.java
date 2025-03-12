package vn.funix.FX14814.java.asm02;

import java.util.Scanner;

import vn.funix.FX14814.java.asm02.models.Account;
import vn.funix.FX14814.java.asm02.models.Bank;
import vn.funix.FX14814.java.asm02.models.Customer;
import vn.funix.FX14814.java.asm02.models.Util;

public class ASM02 {

	// Hằng số cho AUTHOR và VERSION
	private static final String AUTHOR = "FX14814";
	private static final String VERSION = "v2.0.0";
	private static final Scanner scanner = new Scanner(System.in);

	private static Bank bank = new Bank();

	public static void main(String[] args) {
		Customer cus = new Customer("Nguyễn Việt Đức", "035198005432");
		cus.addAccount(new Account("999999", 123000000));
		cus.addAccount(new Account("987654", 15000000));
		bank.addCustomer(cus);
		cus = new Customer("Nguyễn Văn A", "035198005444");
		cus.addAccount(new Account("987666", 5550000));
		bank.addCustomer(cus);

		// Hiển thị thông tin phần mềm
		System.out.println(Util.getDivider());
		System.out.println("|               NGÂN HÀNG SỐ - " + AUTHOR + " - " + VERSION + "               |");

		while (true) {
			// Hiển thị menu chính
			try {
				System.out.println(Util.getDivider());
				System.out.println("| 1. Thêm khách hàng                                          |");
				System.out.println("| 2. Thêm tài khoản                                           |");
				System.out.println("| 3. Hiển thị danh sách khách hàng                            |");
				System.out.println("| 4. Tìm theo CCCD khách hàng                                 |");
				System.out.println("| 5. Tìm theo tên khách hàng                                  |");
				System.out.println("| 0. Thoát                                                    |");
				System.out.println(Util.getDivider());
				System.out.print("Chọn chức năng: ");

				int choice = Integer.parseInt(scanner.nextLine().trim()); // Chuyển input thành số nguyên
				System.out.println(Util.getDivider());
				switch (choice) {
				case 1:
					addCustomer();
					break;
				case 2:
					addAccount();
					break;
				case 3:
					bank.displayCustomers();
					break;
				case 4:
					searchCustomerByCCCD();
					break;
				case 5:
					searchCustomerByName();
					break;
				case 0:
					System.out.println("Kết thúc chương trình!");
					scanner.close();
					return;
				default:
					System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
			}
		}
	}

	// 1. Thêm khách hàng
	private static void addCustomer() {
		System.out.print("Nhập tên khách hàng: ");
		String name = scanner.nextLine().trim();

		while (true) {
			System.out.print("Nhập số CCCD: ");
			String cccd = scanner.nextLine().trim();

			if (cccd.equalsIgnoreCase("No")) {
				return;
			}

			try {
				Customer customer = new Customer(name, cccd);
				bank.addCustomer(customer);
				System.out.println("Đã thêm khách hàng " + name + " " + cccd + " vào danh sách");
				return;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}

		}
	}

	// 2. Thêm tài khoản
	private static void addAccount() {
		String cccd;
		
		while (true) {
			System.out.print("Nhập số CCCD khách hàng: ");
			cccd = scanner.nextLine().trim();

			if (cccd.equalsIgnoreCase("No")) {
				return;
			}

			if (bank.getCustomerByCCCD(cccd) != null) {
				break;
			} else {
				System.out.println("Khách hàng " + cccd + " không tồn tại!");
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}

		while (true) {
			try {
				System.out.print("Nhập số tài khoản (6 chữ số): ");
				String accountNumber = scanner.nextLine().trim();

				if (accountNumber.equalsIgnoreCase("No")) {
					return;
				}

				System.out.print("Nhập số dư: ");
				double balance = Double.parseDouble(scanner.nextLine().trim()); // Chuyển input thành số thực
				
				bank.addAccount(cccd, new Account(accountNumber, balance));
				System.out.println("Đã thêm tài khoản " + accountNumber + " vào khách hàng " + cccd);
				return;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}

	// 4. Tìm theo CCCD khách hàng
	private static void searchCustomerByCCCD() {
		while (true) {
			System.out.print("Nhập số CCCD khách hàng: ");
			String cccd = scanner.nextLine().trim();

			if (cccd.equalsIgnoreCase("No")) {
				return;
			}

			Customer customer = bank.getCustomerByCCCD(cccd);
			if (customer != null) {
				customer.displayInformation();
				return;
			} else {
				System.out.println("Khách hàng " + cccd + " không tồn tại!");
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}

	// 5. Tìm theo tên khách hàng
	private static void searchCustomerByName() {
		while (true) {
			System.out.print("Nhập tên khách hàng: ");
			String name = scanner.nextLine().trim();

			if (name.equalsIgnoreCase("No")) {
				return;
			}

			Customer customer = bank.getCustomerByName(name.trim());
			if (customer != null) {
				customer.displayInformation();
				return;
			} else {
				System.out.println("Khách hàng với tên " + name + " không tồn tại!");
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}
}
