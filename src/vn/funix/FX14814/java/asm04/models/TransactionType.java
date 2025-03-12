package vn.funix.FX14814.java.asm04.models;

public enum TransactionType {
	DEPOSIT("Nạp tiền"), WITHDRAW("Rút tiền"), TRANSFER("Chuyển tiền");

	private final String description;

	TransactionType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}