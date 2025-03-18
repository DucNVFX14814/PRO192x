package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;

import vn.funix.FX14814.java.asm04.exception.CustomerIdNotValidException;

//Class User - Quản lý thông tin người dùng
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String customerId;

	public User() {
		this.name = "";
		this.customerId = "";
	}

	public User(String customerId, String name) {
		checkValidCCCD(customerId);

		this.name = name;
		this.customerId = customerId;
	}

	private void checkValidCCCD(String cccd) {
		if (!Util.isValidCCCD(cccd)) {
			throw new CustomerIdNotValidException("Số CCCD " + cccd + " không hợp lệ!");
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
