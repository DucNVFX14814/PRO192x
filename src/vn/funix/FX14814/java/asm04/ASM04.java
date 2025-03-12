package vn.funix.FX14814.java.asm04;

import java.util.Scanner;

import vn.funix.FX14814.java.asm04.models.DigitalBank;
import vn.funix.FX14814.java.asm04.models.Util;

public class ASM04 {

	// Hằng số cho AUTHOR và VERSION
	private static final String AUTHOR = "FX14814";
	private static final String VERSION = "v4.0.0";

	private static final Scanner scanner = new Scanner(System.in);
	private static final DigitalBank activeBank = new DigitalBank("My Bank");

	public static void main(String[] args) {
		// Hiển thị thông tin phần mềm
		System.out.println(Util.getDivider());
		System.out.println("|               NGÂN HÀNG SỐ - " + AUTHOR + " - " + VERSION + "               |");

		while (true) {
			// Hiển thị menu chính
			try {
				System.out.println(Util.getDivider());
				System.out.println("| 1. Xem danh sách khách hàng                                 |");
				System.out.println("| 2. Nhập danh sách khách hàng                                |");
				System.out.println("| 3. Thêm tài khoản ATM                                       |");
				System.out.println("| 4. Chuyển tiền                                              |");
				System.out.println("| 5. Rút tiền                                                 |");
				System.out.println("| 6. Tra cứu lịch sử giao dịch                                |");
				System.out.println("| 0. Thoát                                                    |");
				System.out.println(Util.getDivider());
				System.out.print("Chọn chức năng: ");

				int choice = Integer.parseInt(scanner.nextLine().trim()); // Chuyển input thành số nguyên

				System.out.println(Util.getDivider());
				switch (choice) {
				case 1:
					showCustomers();
					break;
				case 2:
					addCustomers();
					break;
				case 3:
					addAccount();
					break;
				case 4:
					transfer();
					break;
				case 5:
					withdraw();
					break;
				case 6:
					displayTransactionsHistory();
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

	// 1. Xem danh sách khách hàng
	private static void showCustomers() {
		activeBank.showCustomers();
	}

	// 2. Nhập danh sách khách hàng
	private static void addCustomers() {
		System.out.print("Nhập đường dẫn đến file: ");
		String fileName = scanner.nextLine();
		activeBank.addCustomers(fileName);
	}

	// 3. Thêm tài khoản ATM
	private static void addAccount() {
		System.out.print("Nhap ma so khach hang: ");
		String customerId = scanner.nextLine();
		activeBank.addSavingAccount(scanner, customerId);
	}

	// 4. Chuyển tiền
	private static void transfer() {
		System.out.print("Nhap ma so khach hang: ");
		String customerId = scanner.nextLine();
		activeBank.transfers(scanner, customerId);
	}

	// 5. Rút tiền
	private static void withdraw() {
		System.out.print("Nhap ma so khach hang: ");
		String customerId = scanner.nextLine();
		activeBank.withdraw(scanner, customerId);
	}

	// 6. Tra cứu lịch sử giao dịch
	private static void displayTransactionsHistory() {
		System.out.print("Nhap ma so khach hang: ");
		String customerId = scanner.nextLine();
		activeBank.displayTransactionInformation(customerId);
	}
}