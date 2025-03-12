package vn.funix.FX14814.java.asm02.models;

//Class User - Quản lý thông tin người dùng
public abstract class User {
	private String name;
	private String customerId;

	public User(String name, String customerId) {
		checkValidCCCD(customerId);

		this.name = name;
		this.customerId = customerId;
	}

	private void checkValidCCCD(String cccd) {
		if (!Util.isValidCCCD(cccd)) {
			throw new IllegalArgumentException("Số CCCD không hợp lệ!");
		}
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		checkValidCCCD(customerId);

		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}