import java.util.Calendar;

/**
 * Title        BankAccount.java
 * Description  This class defines a bank account.
 */
abstract class BankAccount {
	/**
	 * The customer who has this account.
	 */
	private Customer customer;

	/**
	 * The account number.
	 */
	private int no;

	/**
	 * The account balance.
	 */
	private double balance;

	/**
	 * The account cheque which waits to be saved into balance.
	 */
	private double cheque;

	/**
	 * The account PIN.
	 */
	private String PIN;

	/**
	 * The status of account.
	 */
	private boolean isSuspended;

	/**
	 * Constructor function of BankAccount.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 * @throws CreditHistoryException If the customer has a bad credit history, throws a CreditHistoryException.
	 */
	BankAccount(double initBalance, Customer customer) throws CreditHistoryException {
		if (CreditAgency.checkCreditHistory(customer))
			throw new CreditHistoryException();
		setRandomNo();
		this.balance = initBalance;
		this.cheque = 0;
		this.customer = customer;
		this.PIN = getRandomPIN(6);
		this.isSuspended = false;
	}

	/**
	 * This function returns account number in an int.
	 *
	 * @return The account number.
	 */
	int getNo() {
		return no;
	}

	/**
	 * This function sets a random account number.
	 */
	void setRandomNo() {
		no = 10000000 + (int) (Math.random() * 90000000);
	}

	/**
	 * This function returns the customer who has this account.
	 *
	 * @return The customer who has this account.
	 */
	Customer getCustomer() {
		return customer;
	}

	/**
	 * This function returns the name of the customer.
	 *
	 * @return The name of the customer who has this account.
	 */
	String getName() {
		return customer.getName();
	}

	/**
	 * This function returns the address of the customer.
	 *
	 * @return The address of the customer who has this account.
	 */
	String getAddress() {
		return customer.getAddress();
	}

	/**
	 * This function returns the birthday of the customer.
	 *
	 * @return The birthday of the customer who has this account.
	 */
	Calendar getBirth() {
		return customer.getBirth();
	}

	/**
	 * This function returns the account balance in a double.
	 *
	 * @return The account balance of this account.
	 */
	double getBalance() {
		return balance;
	}

	/**
	 * This function is used for adding some balance silently.
	 *
	 * @param amount The amount you want to add.
	 */
	void addBalance(double amount) {
		balance += amount;
	}

	/**
	 * This function generates a random PIN and returns it.
	 *
	 * @param num The length of PIN which you want to set.
	 * @return The random PIN.
	 */
	private String getRandomPIN(int num) {
		StringBuilder randomPIN = new StringBuilder();
		for (int i = 0; i < num; i++) {
			randomPIN.append((int) (Math.random() * 10));
		}
		return randomPIN.toString();
	}

	/**
	 * This function should be call only when open account.
	 * This function returns the PIN of this account in a string.
	 *
	 * @return The PIN.
	 */
	String getPIN() {
		return PIN;
	}

	/**
	 * This function check if the parameter is equal to PIN.
	 *
	 * @param PIN The string which you want to check.
	 * @return If the parameter is equal to PIN, returns true.
	 */
	boolean checkPIN(String PIN) {
		return this.PIN.equals(PIN);
	}

	/**
	 * This function deposits a mount of cash.
	 * Before depositing, this function checks if the account is suspended.
	 * If the account is suspended, stops the depositing.
	 * If not, deposits it.
	 *
	 * @param amount The amount you want to deposit.
	 */
	void depositByCash(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			System.out.println("Depositing failed.");
			return;
		}
		if (amount <= 0) {
			System.out.println("Amount must be more than 0.");
			System.out.println("Depositing failed.");
			return;
		}
		balance += amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	/**
	 * This function deposits a mount of cheque.
	 * Before depositing, this function checks if the account is suspended.
	 * If the account is suspended, stops the depositing.
	 * If not, deposits it.
	 *
	 * @param amount The amount you want to deposit.
	 */
	void depositByCheque(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		if (amount <= 0) {
			System.out.println("Amount must be more than 0.");
			System.out.println("Depositing failed.");
			return;
		}
		cheque += amount;
		System.out.println("Deposit cheque " + amount + " successfully");
	}

	/**
	 * This function withdraws a mount of cash.
	 * Before withdrawing, this function checks if the account is suspended.
	 * If the account is suspended, stops the withdrawing.
	 * If not, then continues.
	 * And this function checks if the balance is more than the amount.
	 * If not more, stops the withdrawing.
	 * If more, withdraws it.
	 *
	 * @param amount The amount you want to withdraw.
	 */
	void withdraw(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		if (amount <= 0) {
			System.out.println("Amount must be more than 0.");
			System.out.println("Depositing failed.");
			return;
		}
		if (check(amount)) {
			balance -= amount;
			System.out.println("Withdraw " + amount + " successfully.");
		} else {
			System.out.println("Withdraw " + amount + " unsuccessfully. Do not have enough available funds.");
		}
	}

	/**
	 * This function return the status of this account.
	 *
	 * @return If this account is suspended, returns true. Vice versa.
	 */
	boolean isSuspended() {
		return isSuspended;
	}

	/**
	 * This function suspends the account.
	 */
	void suspend() {
		isSuspended = true;
	}

	/**
	 * This function reinstate the account.
	 */
	void reinstate() {
		isSuspended = false;
	}

	/**
	 * This function checks if the balance is more than amount.
	 *
	 * @param amount The amount which you want to check.
	 * @return If the balance is more than amount, returns true. Vice versa.
	 */
	boolean check(double amount) {
		return (balance >= amount);
	}

	/**
	 * This function provides the balance information of this account.
	 */
	void checkBalance() {
		System.out.println("Account balance: " + balance);
	}

	/**
	 * This function clears funds.
	 */
	void clearFunds() {
		balance += cheque;
		cheque = 0;
	}

	/**
	 * This function returns the cheque that waits to be handled in a double.
	 *
	 * @return The cheque.
	 */
	double getCheque() {
		return cheque;
	}

	/**
	 * This function sets the cheque to what you want.
	 *
	 * @param cheque The cheque you want to set.
	 */
	void setCheque(double cheque) {
		this.cheque = cheque;
	}

	/**
	 * This function is used for overriding by SaverAccount class.
	 * This function gives a tip when this account is not SaverAccount.
	 *
	 * @param amount The amount you want to pre-withdraw.
	 */
	void preWithdraw(double amount) {
		System.out.println("Your account is not saver account.");
	}

	/**
	 * This function is used for overriding by CurrentAccount class.
	 * This function gives a tip when this account is not CurrentAccount.
	 *
	 * @param overdraftLimit The overdraft limit you want to set.
	 */
	void setOverdraftLimit(double overdraftLimit) {
		System.out.println("Your account is not current account.");
	}

	/**
	 * This function returns various information about this account.
	 * This is a abstract function. Subclasses need to override it.
	 *
	 * @return The string which contains the information about this account.
	 */
	@Override
	public abstract String toString();
}
