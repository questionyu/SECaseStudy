abstract class BankAccount {
	private int no;
	private String name;
	private double balance;
	private String address;
	private int birth;

	private String PIN;

	BankAccount(double initBalance, String name, String address, int birth) {
		this.no = 10000000 + (int) (Math.random() * 9000000);
		this.balance = initBalance;
		this.name = name;
		this.address = address;
		this.birth = birth;

	}

	int getNo() {
		return no;
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

	void deposit(double amount) {
		balance = balance + amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfully.");
		}
	}

	boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= 0) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount	+ " unsuccessfully. Do not have enough available funds.");
		}
		return allowed;
	}


}
