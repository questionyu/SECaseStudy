/**
 * Title        CurrentAccount.java
 * Description  This class defines a current account.
 */
class CurrentAccount extends BankAccount {
	/**
	 * The overdraft limit.
	 */
	private double overdraftLimit;

	/**
	 * The constructor function of CurrentAccount.
	 *
	 * @param initBalance    Initial balance.
	 * @param customer       The customer who wants to open an account.
	 * @param overdraftLimit The overdraft limit.
	 */
	CurrentAccount(double initBalance, Customer customer, double overdraftLimit) {
		super(initBalance, customer);
		setOverdraftLimit(overdraftLimit);
	}

	/**
	 * This function provides the balance information of this account.
	 */
	@Override
	void checkBalance() {
		super.checkBalance();
		System.out.println("Account overdraft limit: " + overdraftLimit);
	}

	/**
	 * This function returns the overdraft limit in a double.
	 *
	 * @return The overdraft limit.
	 */
	double getOverdraftLimit() {
		return overdraftLimit;
	}

	/**
	 * This function sets the overdraft limit to what you want.
	 *
	 * @param overdraftLimit The overdraft limit you want to set.
	 */
	@Override
	void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	/**
	 * This function checks if the balance is more than (amount + overdraft limit).
	 *
	 * @param amount The amount which you want to check.
	 * @return If the balance is more than (amount + overdraft limit), returns true. Vice versa.
	 */
	@Override
	boolean check(double amount) {
		return (this.getBalance() - amount >= -overdraftLimit);
	}

	/**
	 * This function returns various information about this account.
	 * Customer, account number, balance, cheque, overdraft limit, isSuspended.
	 *
	 * @return The string which contains the information about this account.
	 */
	@Override
	public String toString() {
		return "CurrentAccount{" + "\n" +
				"    customer=" + getCustomer() + "\n" +
				"    no=" + getNo() + "\n" +
				"    balance=" + getBalance() + "\n" +
				"    cheque=" + getCheque() + "\n" +
				"    overdraftLimit=" + getOverdraftLimit() + "\n" +
				"    isSuspended=" + isSuspended() + "\n" +
				'}';
	}
}