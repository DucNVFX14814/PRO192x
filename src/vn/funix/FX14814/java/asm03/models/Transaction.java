package vn.funix.FX14814.java.asm03.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transaction {
	private static String ATM_ID = "DIGITAL-BANK-ATM 2025";

	private String id;
	private String atmId;
	private String accountNumber;
	private String accountType;
	private double amount;
	private double balance;
	private double fee;
	private String time;
	private boolean status;

	public Transaction(String accountNumber, String accountType, double amount, double balance, double fee,
			boolean status) {
		this.id = UUID.randomUUID().toString();
		this.atmId = ATM_ID;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.amount = amount;
		this.balance = balance;
		this.fee = fee;
		this.status = status;
		this.time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	}

	public String getId() {
		return id;
	}

	public String getAtmId() {
		return atmId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	public double getFee() {
		return fee;
	}

	public boolean getStatus() {
		return status;
	}

	public String getTime() {
		return time;
	}

	public void displayTransaction() {
		System.out.printf("%-5s %6s | %19sđ | %23s | %s%n", "[GD]", accountNumber, Util.formatAmount(amount), time, id);
	}

	public void displayTransactionDetail() {
		System.out.println(Util.getDivider());
		System.out.printf("%30s%n", "BIÊN LAI GIAO DỊCH " + accountType);
		System.out.printf("NGÀY G/D: %28s%n", time);
		System.out.printf("ATM ID: %30s%n", atmId);
		System.out.printf("SÔ TK: %31s%n", accountNumber);
		System.out.printf("SỐ TIỀN: %28sđ%n", Util.formatAmount(amount));
		System.out.printf("SỐ DƯ: %30sđ%n", Util.formatAmount(balance));
		System.out.printf("PHÍ + VAT: %26sđ%n", Util.formatAmount(fee));
		System.out.println(Util.getDivider());
	}
}
