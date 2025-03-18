package vn.funix.FX14814.java.asm04.models;

public class JUnitTest {

//	private SavingsAccount savingsAccount;
//	private LoansAccount loansAccount;
//	private Account account;
//	private Bank bank;
//	private Customer customer;
//	private Exception exception;
//
//	@BeforeEach
//	void setUp() {
//		savingsAccount = new SavingsAccount("123456", 50_000_000);
//		loansAccount = new LoansAccount("999999", 50_000_000);
//		account = new Account("111111", 50_000_000);
//		bank = new Bank();
//		customer = new Customer("Funix", "001215000001");
//		customer.addAccount(account);
//		bank.addCustomer(customer);
//	}
//
//	@Test
//	void testSavingsAccountWithdraw() {
//		assertTrue(savingsAccount.withdraw(1_000_000), "Rút tiền hợp lệ phải thành công");
//		assertEquals(49_000_000, savingsAccount.getBalance(), "Số dư sau khi rút phải giảm chính xác");
//		assertFalse(savingsAccount.withdraw(48_990_000), "Rút quá số dư tối thiểu còn lại phải thất bại");
//		assertFalse(savingsAccount.withdraw(12_345), "Số tiền không phải bội số của 10.000 phải thất bại");
//	}
//
//	@Test
//	void testLoansAccountWithdraw() {
//		assertTrue(loansAccount.withdraw(5_000_000), "Rút tiền hợp lệ phải thành công");
//		assertEquals(44_950_000, loansAccount.getBalance(), "Số dư phải trừ đi do có phí 0.01 cho tài khoản premium");
//		assertFalse(loansAccount.withdraw(100_000_000), "Rút quá hạn mức phải thất bại");
//	}
//
//	@Test
//	void testGetFee() {
//		assertEquals(0, savingsAccount.getFee(1_000_000), "Phí rút tiền của SavingsAccount phải bằng 0");
//		assertEquals(10_000, loansAccount.getFee(1_000_000),
//				"Phí rút tiền của LoansAccount phải bằng 1000000 * 0.01 do là tại khoản premium");
//	}
//
//	@Test
//	void testValidateAccount() {
//		assertDoesNotThrow(() -> account.checkValidAccountNumber("222222"), "STK hợp lệ không nên ném lỗi");
//		exception = assertThrows(IllegalArgumentException.class, () -> account.checkValidAccountNumber("12345"),
//				"STK không đủ 6 chữ số phải ném lỗi");
//		assertEquals("STK không hợp lệ!", exception.getMessage());
//		exception = assertThrows(IllegalArgumentException.class, () -> account.checkValidAccountNumber("12A456"),
//				"STK chứa ký tự không phải số phải ném lỗi");
//		assertEquals("STK không hợp lệ!", exception.getMessage());
//	}
//
//	@Test
//	void testIsPremiumAccount() {
//		assertTrue(account.isPremiumAccount(), "Tài khoản có số dư trên 10 triệu -> Premium");
//	}
//
//	@Test
//	void testGetCustomerByCCCD() {
//		assertEquals(customer, bank.getCustomerByCCCD("001215000001"), "Khách hàng phải là Funix");
//		assertNull(bank.getCustomerByCCCD("001215000002"), "Khách hàng không tồn tại nên phải trả về null");
//	}
//
//	@Test
//	void testIsCustomerExisted() {
//		assertTrue(bank.isCustomerExisted("001215000001"), "Khách hàng 001215000001 tồn tại");
//		assertFalse(bank.isCustomerExisted("001215000002"), "Khách hàng 001215000002 không tồn tại");
//	}
//
//	@Test
//	void testIsAccountExisted() {
//		assertTrue(customer.isAccountExisted("111111"), "Tài khoản 111111 tồn tại");
//		assertFalse(bank.isCustomerExisted("111112"), "Tài khoản 111112 không tồn tại");
//	}
//
//	@Test
//	void testGetBalance() {
//		customer.addAccount(new Account("456789", 1_000_000));
//		assertEquals(51_000_000, customer.getBalance(), "Khách hàng có tổng cộng 51_000_000 từ 2 tài khoản");
//	}
//
//	@Test
//	void testIsPremiumCustomer() {
//		assertTrue(customer.isPremiumCustomer(), "Khách hàng có tổng số dư trên 10 triệu -> Premium");
//	}
//
//	@Test
//	void testGetAccountByAccountNumber() {
//		assertEquals(account, customer.getAccountByAccountNumber("111111"), "Tài khoản phải là 111111");
//		assertNull(customer.getAccountByAccountNumber("111112"), "Tài khoản không tồn tại nên phải trả về null");
//	}
//
//	@Test
//	void testIsValidCCCD() {
//		assertTrue(Util.isValidCCCD("001215000001"), "CCCD hợp lệ");
//		assertFalse(Util.isValidCCCD("001215000"), "CCCD không đủ 12 số");
//		assertFalse(Util.isValidCCCD("00121500000a"), "CCCD chứa ký tự không phải số");
//		assertFalse(Util.isValidCCCD("999123456789"), "CCCD có mã tỉnh không hợp lệ");
//		assertFalse(Util.isValidCCCD("000999999999"), "CCCD có thông tin mã tỉnh không hợp lệ");
//		assertTrue(Util.isValidCCCD("001999999999"), "CCCD có thông tin năm sinh và giới tính hợp lệ");
//		assertFalse(Util.isValidCCCD(null), "CCCD null không hợp lệ");
//	}
}
