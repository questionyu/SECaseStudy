/**
 * Title        SaverAccount.java
 * Description This class defines a saver account.
 */
class SaverAccount extends BankAccount {
	/**
	 * The special balance of saver account.
	 */
	private double saverBalance;

	/**
	 * The pre-withdraw-amount of saver account.
	 */
	private double preWithdraw;

	/**
	 * The constructor function of SaverAccount.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 */
	SaverAccount(double initBalance, Customer customer) {
		super(0, customer);
		this.saverBalance = initBalance;
		this.preWithdraw = 0;
	}

	/**
	 * This function pre-withdraw a mount of cash from special balance.
	 * And wait for clearFunds of Bank to save pre-withdraw-amount to generic balance.
	 * Before pre-withdrawing, this function checks if the account is suspended.
	 * If the account is suspended, stops the pre-withdrawing.
	 * If not, then continues.
	 * And this function checks if the special balance is more than the amount.
	 * If not more, stops the pre-withdrawing.
	 * If more, pre-withdraws it.
	 *
	 * @param amount The amount you want to pre-withdraw.
	 */
	@Override
	void preWithdraw(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		if (saverCheck(amount)) {
			saverBalance -= amount;
			preWithdraw += amount;
			System.out.println("Pre-withdraw " + amount + " successfully.");
		} else {
			System.out.println("Pre-withdraw " + amount + " unsuccessfully. Do not have enough available funds.");
		}
	}

	/**
	 * This function checks if the special balance is more than amount.
	 *
	 * @param amount The amount which you want to check.
	 * @return If the special balance is more than amount, returns true. Vice versa.
	 */
	private boolean saverCheck(double amount) {
		return (saverBalance >= amount);
	}

	/**
	 * This function deposits a mount of cash.
	 * Before depositing, this function checks if the account is suspended.
	 * If the account is suspended, stops the depositing.
	 * If not, deposits it.
	 *
	 * @param amount The amount you want to deposit.
	 */
	@Override
	void deposit(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		saverBalance += amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	/**
	 * This function clears funds.
	 */
	@Override
	void clearFunds() {
		saverBalance += getCheque();
		setCheque(0);
		addBalance(preWithdraw);
		preWithdraw = 0;
	}

	/**
	 * This function returns various information about this account.
	 * Customer, account number, balance, cheque, overdraft limit, isSuspended.
	 *
	 * @return The string which contains the information about this account.
	 */
	@Override
	public String toString() {
		return "SaverAccount{" + "\n" +
				"    customer=" + getCustomer() + "\n" +
				"    no=" + getNo() + "\n" +
				"    balance=" + getBalance() + "\n" +
				"    cheque=" + getCheque() + "\n" +
				"    saverBalance=" + saverBalance + "\n" +
				"    preWithdraw=" + preWithdraw + "\n" +
				"    isSuspended=" + isSuspended() + "\n" +
				'}';
	}
}
