package vn.funix.FX14814.java.asm04.exception;

public class CustomerIdNotValidException extends RuntimeException {
	private static final long serialVersionUID = -7684935135005704755L;

	public CustomerIdNotValidException(String message) {
		super(message);
	}
}