import java.util.Calendar;

/**
 * Title        CurrentAccount.java
 * Description
 */
class CurrentAccount extends BankAccount {
	private double overdraftLimit;

	CurrentAccount(double initBalance, String name, String address, Calendar birth, double overdraftLimit) {
		super(initBalance, name, address, birth);
		setOverdraftLimit(overdraftLimit);
	}

	double getOverdraftLimit() {
		return overdraftLimit;
	}

	@Override
	void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	boolean check(double amount) {
		return (this.getBalance() - amount >= -overdraftLimit);
	}
}