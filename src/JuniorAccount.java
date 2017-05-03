import java.util.Calendar;

/**
 * Title        JuniorAccount.java
 * Description  This class defines a junior account.
 */
class JuniorAccount extends BankAccount {
	/**
	 * The age limit of customer who wants to open a JuniorAccount.
	 */
	public static final int ageLimit = 16;

	/**
	 * The constructor function of JuniorAccount.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 * @throws IllegalAgeException If this customer does not meet the criteria of age limit, throws an IllegalAgeException.
	 */
	JuniorAccount(double initBalance, Customer customer) throws IllegalAgeException {
		super(initBalance, customer);
		Calendar rightNow = Calendar.getInstance();
		Calendar birth = customer.getBirth();
		birth.add(Calendar.YEAR, ageLimit);
		if (rightNow.after(birth))
			throw new IllegalAgeException();
	}

	/**
	 * This function returns various information about this account.
	 * Customer, account number, balance, cheque, isSuspended.
	 *
	 * @return The string which contains the information about this account.
	 */
	@Override
	public String toString() {
		return "JuniorAccount{" + "\n" +
				"    customer=" + getCustomer() + "\n" +
				"    no=" + getNo() + "\n" +
				"    balance=" + getBalance() + "\n" +
				"    cheque=" + getCheque() + "\n" +
				"    isSuspended=" + isSuspended() + "\n" +
				'}';
	}
}
