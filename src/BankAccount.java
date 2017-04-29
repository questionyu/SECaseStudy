/**
 * Title        BankAccount.java
 * Description
 */
abstract class BankAccount {
	private int no;
	private String name;
	private double balance;
	private String address;
	private int birth;

	private String PIN;

	BankAccount(double initBalance, String name, String address, int birth) throws CreditHistoryException {
		this.no = getRandomNo();
		this.balance = initBalance;
		this.name = name;
		this.address = address;
		this.birth = birth;
		if (CreditAgency.checkCreditHistory(name))
			throw new CreditHistoryException();
	}

	int getRandomNo() {
		return 10000000 + (int) (Math.random() * 9000000);
	}

	int getNo() {
		return no;
	}

	void setNo(int no) {
		this.no = no;
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
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfully. Do not have enough available funds.");
		}
	}

	boolean check(double amount) {
		return (balance - amount >= 0);
	}


}
