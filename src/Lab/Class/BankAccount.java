package Lab.Class;

import java.util.concurrent.TimeUnit;

//Lab17.1 17.2
//public class BankAccount {
//
//	private double balance;
//	private String accountNumber;
//
//	public BankAccount(String accountNumber, double initialBalance) {
//		this.accountNumber = accountNumber;
//		this.balance = initialBalance;
//	}
//
//	public synchronized void deposit(double amount) {
//		synchronized (this) {
//			balance += amount;
//			System.out.println(Thread.currentThread().getName() + " deposited: $" + amount + " | Balance: $" + balance);
//		}
//	}
//
//	public synchronized void withdraw(double amount) {
//		synchronized (this) {
//			balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " withdrew: $" + amount + " | Balance: $" + balance);
//		}
//	}
//	
//	@Override
//	public String toString() {
//		return "Account " + accountNumber + ": " + balance;
//	}
//}

//Lab17.3
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
//public class BankAccount {
//	private double balance;
//	private String accountNumber;
//	private final Lock lock;
//
//	public BankAccount(String accountNumber, double initialBalance) {
//		this.accountNumber = accountNumber;
//		this.balance = initialBalance;
//		this.lock = new ReentrantLock();
//	}
//
//	public void deposit(double amount) {
//		lock.lock(); // Khóa trước khi thực hiện giao dịch
//		try {
//			balance += amount;
//			System.out.println(Thread.currentThread().getName() + " deposited: $" + amount + " | Balance: $" + balance);
//		} finally {
//			lock.unlock(); // Đảm bảo mở khóa dù có exception hay không
//		}
//	}
//
//	public void withdraw(double amount) {
//		lock.lock();
//		try {
//			balance -= amount;
//			System.out.println(Thread.currentThread().getName() + " withdrew: $" + amount + " | Balance: $" + balance);
//		} finally {
//			lock.unlock();
//		}
//	}
//
//	@Override
//	public String toString() {
//		return "Account " + accountNumber + ": " + balance;
//	}
//}

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
	private double balance;
	private String accountNumber;
	private final Lock lock;

	public BankAccount(String accountNumber, double initialBalance) {
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.lock = new ReentrantLock(); // Khởi tạo lock
	}

	public void deposit(double amount) {
		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // Thử lấy khóa trong 1 giây
				try {
					balance += amount;
					System.out.println(
							Thread.currentThread().getName() + " deposited: $" + amount + " | Balance: $" + balance);
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println(Thread.currentThread().getName() + " Could not get lock");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void withdraw(double amount) {
		try {
			if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) { // Thử lấy khóa trong 1 giây
				try {
					balance -= amount;
					System.out.println(
							Thread.currentThread().getName() + " withdrew: $" + amount + " | Balance: $" + balance);
				} finally {
					lock.unlock();
				}
			} else {
				System.out.println(Thread.currentThread().getName() + " Could not get lock");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Account " + accountNumber + ": " + balance;
	}
}
