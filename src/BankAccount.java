public class BankAccount {
	private int accNo;
	private String accName;
	private double balance;

	BankAccount(int accNo, String accName) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}

	BankAccount(String accName, int accNo) {
		this.accNo = accNo;
		this.accName = accName;
		this.balance = 0.0;
	}

	int getAccNo() {
		return accNo;
	}

	String getAccName() {
		return accName;
	}

	void setAccName(String accName) {
		this.accName = accName;
	}

	double getBalance() {
		return balance;
	}

	void deposit(double amount) {
		balance = balance + amount;
		System.out.println("Deposit " + amount + " successful");
	}

	void withdraw(double amount) {
		if (check(amount)) {
			balance = balance - amount;
			System.out.println("Withdraw " + amount + " successfull.");
		}
	}

	boolean check(double amount) {
		boolean allowed = false;
		if (balance - amount >= 0) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount
					+ " unsuccessfull. Do not have enough available funds.");
		}
		return allowed;
	}

	public String toString() {
		return "Account number: " + accNo + "\n" + "Account name: " + accName
				+ "\n" + "Balance: " + balance + "\n";
	}

}
