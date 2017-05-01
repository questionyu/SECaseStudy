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
	private boolean isSuspended;

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
		this.isSuspended = false;
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

	String getAddress() {
		return address;
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
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		balance += amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	void depositCheque(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		cheque += amount;
		System.out.println("Deposit cheque " + amount + " successfully");
	}

	void withdraw(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		if (check(amount)) {
			balance -= amount;
			System.out.println("Withdraw " + amount + " successfully.");
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfully. Do not have enough available funds.");
		}
	}

	boolean isSuspended() {
		return isSuspended;
	}

	void suspend() {
		this.isSuspended = true;
	}

	void reinstated() {
		this.isSuspended = false;
	}

	boolean check(double amount) {
		return (balance >= amount);
	}

	void clearFunds() {
		balance += cheque;
		cheque = 0;
	}

	double getCheque() {
		return cheque;
	}

	void setCheque(double cheque) {
		this.cheque = cheque;
	}
}
