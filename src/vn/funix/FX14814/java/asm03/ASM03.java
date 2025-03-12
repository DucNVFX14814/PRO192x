package vn.funix.FX14814.java.asm03;

import java.util.Scanner;

import vn.funix.FX14814.java.asm03.models.Customer;
import vn.funix.FX14814.java.asm03.models.DigitalBank;
import vn.funix.FX14814.java.asm03.models.DigitalCustomer;
import vn.funix.FX14814.java.asm03.models.LoansAccount;
import vn.funix.FX14814.java.asm03.models.SavingsAccount;
import vn.funix.FX14814.java.asm03.models.Util;

public class ASM03 {

	// Hằng số cho AUTHOR và VERSION
	private static final String AUTHOR = "FX14814";
	private static final String VERSION = "v3.0.0";
	private static final String CUSTOMER_ID = "001215000001";
	private static final String CUSTOMER_NAME = "FUNIX";

	private static final Scanner scanner = new Scanner(System.in);
	private static final DigitalBank activeBank = new DigitalBank();

	public static void main(String[] args) {
		activeBank.addCustomer(new DigitalCustomer(CUSTOMER_NAME, CUSTOMER_ID));

		activeBank.addAccount(CUSTOMER_ID, new SavingsAccount("123456", 25000000));
		activeBank.addAccount(CUSTOMER_ID, new LoansAccount("999999", 5000000));

		// Hiển thị thông tin phần mềm
		System.out.println(Util.getDivider());
		System.out.println("|               NGÂN HÀNG SỐ - " + AUTHOR + " - " + VERSION + "               |");

		while (true) {
			// Hiển thị menu chính
			try {
				System.out.println(Util.getDivider());
				System.out.println("| 1. Xem thông tin khách hàng                                 |");
				System.out.println("| 2. Thêm tài khoản ATM                                       |");
				System.out.println("| 3. Thêm tài khoản tín dụng                                  |");
				System.out.println("| 4. Rút tiền                                                 |");
				System.out.println("| 5. Tra cứu lịch sử giao dịch                                |");
				System.out.println("| 6. Tra cứu lịch sử giao dịch chi tiểt theo tài khoản        |");
				System.out.println("| 0. Thoát                                                    |");
				System.out.println(Util.getDivider());
				System.out.print("Chọn chức năng: ");

				int choice = Integer.parseInt(scanner.nextLine().trim()); // Chuyển input thành số nguyên
				System.out.println(Util.getDivider());
				switch (choice) {
				case 1:
					showCustomer();
					break;
				case 2:
					addSavingsAccount();
					break;
				case 3:
					addLoansAccount();
					break;
				case 4:
					withdraw();
					break;
				case 5:
					displayTransactionsHistory();
					break;
//				case 6:
//					displayTransactionHistoryDetail();
//					break;
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

	// 1. Xem thông tin khách hàng
	private static void showCustomer() {
		Customer customer = activeBank.getCustomerByCCCD(CUSTOMER_ID);
		if (customer != null) {
			customer.displayInformation();
		}
	}

	// 2. Thêm tài khoản ATM
	private static void addSavingsAccount() {
		while (true) {
			try {
				System.out.print("Nhập số tài khoản (6 chữ số): ");
				String accountNumber = scanner.nextLine().trim();

				if (accountNumber.equalsIgnoreCase("No")) {
					return;
				}

				System.out.print("Nhập số dư: ");
				double balance = Double.parseDouble(scanner.nextLine().trim()); // Chuyển input thành số thực
				activeBank.addAccount(CUSTOMER_ID, new SavingsAccount(accountNumber, balance));
				System.out.println("Đã thêm tài khoản ATM " + accountNumber);
				return;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}

	// 3. Thêm tài khoản tín dụng
	private static void addLoansAccount() {
		while (true) {
			try {
				System.out.print("Nhập số tài khoản (6 chữ số): ");
				String accountNumber = scanner.nextLine().trim();

				if (accountNumber.equalsIgnoreCase("No")) {
					return;
				}

				System.out.print("Nhập số dư: ");
				double balance = Double.parseDouble(scanner.nextLine().trim()); // Chuyển input thành số thực
				activeBank.addAccount(CUSTOMER_ID, new LoansAccount(accountNumber, balance));
				System.out.println("Đã thêm tài khoản tín dụng " + accountNumber);
				return;
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}

	// 4. Rút tiền
	private static void withdraw() {
		while (true) {
			try {
				System.out.print("Nhập số tài khoản (6 chữ số): ");
				String accountNumber = scanner.nextLine().trim();

				if (accountNumber.equalsIgnoreCase("No")) {
					return;
				}

				System.out.print("Nhập số tiền cần rút: ");
				double amount = Double.parseDouble(scanner.nextLine().trim()); // Chuyển input thành số thực

				Boolean withdrawStatus = activeBank.withdraw(CUSTOMER_ID, accountNumber, amount);
				if (withdrawStatus) {
					System.out.println("Đã rút " + Util.formatAmount(amount) + "đ từ tài khoản " + accountNumber);
					return;
				} else {
					System.out.println("Rút tiền không thành công");
					System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
			}
		}
	}

	// 5. Tra cứu lịch sử giao dịch
	private static void displayTransactionsHistory() {
		showCustomer();
		
		System.out.println(Util.getDivider());
		
		Boolean displayStatus = activeBank.displayTransactions(CUSTOMER_ID);
		if (!displayStatus) {
			System.out.println("Tra cứu lịch sử giao dịch không thành công");
		}
	}

//	// 6. Tra cứu lịch sử giao dịch chi tiểt theo tài khoản
//	private static void displayTransactionHistoryDetail() {
//		while (true) {
//			try {
//				System.out.print("Nhập số tài khoản (6 chữ số): ");
//				String accountNumber = scanner.nextLine().trim();
//
//				if (accountNumber.equalsIgnoreCase("No")) {
//					return;
//				}
//
//				if (accountNumber.matches("\\d{6}")) {
//					Boolean displayStatus = activeBank.displayTransactionsDetail(CUSTOMER_ID, accountNumber);
//					if (displayStatus) {
//						return;
//					} else {
//						System.out.println("Tra cứu lịch sử giao dịch không thành công");
//						System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
//					}
//				} else {
//					System.out.println("STK không hợp lệ!");
//					System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
//				}
//			} catch (NumberFormatException e) {
//				System.out.println("Lỗi: Vui lòng nhập một số hợp lệ.");
//			} catch (IllegalArgumentException e) {
//				System.out.println(e.getMessage());
//				System.out.println("Vui lòng nhập lại hoặc 'No' để thoát.");
//			}
//		}
//	}
}