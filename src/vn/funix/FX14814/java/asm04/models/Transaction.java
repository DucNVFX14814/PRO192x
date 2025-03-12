package vn.funix.FX14814.java.asm04.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String accountNumber;
	private double amount;
	private String time;
	private boolean status;
	private TransactionType type;

	public Transaction(String accountNumber, double amount, String time, boolean status, TransactionType type) {
		this.id = String.valueOf(UUID.randomUUID());
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.time = time;
		this.status = status;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public String getTime() {
		return time;
	}

	public boolean isStatus() {
		return status;
	}

	public TransactionType getType() {
		return type;
	}

	@Override
	public String toString() {
		SimpleDateFormat formatter = new SimpleDateFormat(Util.DATE_FORMAT);
		String sign = (type == TransactionType.WITHDRAW || type == TransactionType.TRANSFER) ? "-" : "+";
		return String.format("[GD] %s | %s | %s%,.0f đ | %s", formatter.format(new Date()), accountNumber, sign, amount,
				type.getDescription());
	}
}
