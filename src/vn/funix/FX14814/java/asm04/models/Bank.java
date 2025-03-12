package vn.funix.FX14814.java.asm04.models;

import java.util.UUID;

//Class Bank - Quản lý danh sách khách hàng
public class Bank {
	private String bankId;
	private String bankName;

	public Bank(String bankName) {
		this.bankId = UUID.randomUUID().toString();
		this.bankName = bankName;
	}

	public String getBankId() {
		return bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}