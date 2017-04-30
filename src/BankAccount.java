/**
 * Title        BankAccount.java
 * Description
 */
abstract class BankAccount {
	private int no;
	private String name;
	private double balance;
	private double cheque;
	private String address;
	private int birth;

	private String PIN;

	BankAccount(double initBalance, String name, String address, int birth) throws CreditHistoryException {
		setRandomNo();
		this.balance = initBalance;
		this.cheque = 0;
		this.name = name;
		this.address = address;
		this.birth = birth;
		if (CreditAgency.checkCreditHistory(name))
			throw new CreditHistoryException();
		this.PIN = getRandomPIN(6);
	}

	int getNo() {
		return no;
	}

	void setRandomNo() {
		this.no = 10000000 + (int) (Math.random() * 90000000);
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	double getBalance() {
		return balance;
	}

	void setAddress(String address) {
		this.address = address;
	}

	private String getRandomPIN(int num) {
		StringBuilder randomPIN = new StringBuilder();
		for (int i = 0; i < num; i++) {
			randomPIN.append((int) (Math.random() * 10));
		}
		return randomPIN.toString();
	}

	void deposit(double amount) {
		balance += amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	void depositCheque(double amount) {
		cheque += amount;
		System.out.println("Deposit cheque " + amount + " successfully");
	}

	void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfully.");
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfully. Do not have enough available funds.");
		}
	}

	boolean check(double amount) {
		return (balance - amount >= 0);
	}

	void clearFunds() {
		balance += cheque;
		cheque = 0;
	}
}
