/**
 * Title        CurrentAccount.java
 * Description
 */
class CurrentAccount extends BankAccount {
	private double overdraftLimit;

	CurrentAccount(double initBalance, String name, String address, int birth, double overdraftLimit) {
		super(initBalance, name, address, birth);
		this.overdraftLimit = overdraftLimit;
	}

	double getOverdraftLimit() {
		return overdraftLimit;
	}

	void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	boolean check(double amount) {
		return (this.getBalance() - amount >= -overdraftLimit);
	}
}