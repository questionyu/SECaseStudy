import java.util.ArrayList;

/**
 * Title        Bank.java
 * Description  This class defines a bank.
 */
class Bank {
	/**
	 * This ArrayList save all bank accounts.
	 */
	private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

	/**
	 * This function returns an account whose account number equals to no.
	 *
	 * @param no The account number which is used for searching account.
	 * @return The account whose account number equals to no. Or null when can not find an account.
	 */
	BankAccount getAccount(int no) {
		for (BankAccount acc : bankAccounts) {
			if (acc.getNo() == no)
				return acc;
		}
		return null;
	}

	/**
	 * This function checks if there is duplicated account number.
	 *
	 * @param no The account number which you want to check if is duplicated.
	 * @return If bank has an account whose account number is same, returns true. Vice versa.
	 */
	private boolean checkDuplicated(int no) {
		for (BankAccount acc : bankAccounts) {
			if (acc.getNo() == no)
				return true;
		}
		return false;
	}

	/**
	 * This function opens a saver account.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 * @return If open successfully, returns account. If fails, returns null.
	 */
	SaverAccount openSaverAccount(double initBalance, Customer customer) {
		try {
			SaverAccount acc = new SaverAccount(initBalance, customer);
			while (checkDuplicated(acc.getNo())) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			System.out.println("Open account successfully.");
			System.out.println("Account no: " + acc.getNo() + ", PIN: " + acc.getPIN());
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		}
		return null;
	}

	/**
	 * This function opens a Junior account.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 * @return If open successfully, returns account. If fails, returns null.
	 */
	JuniorAccount openJuniorAccount(double initBalance, Customer customer) {
		try {
			JuniorAccount acc = new JuniorAccount(initBalance, customer);
			while (checkDuplicated(acc.getNo())) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			System.out.println("Open account successfully.");
			System.out.println("Account no: " + acc.getNo() + ", PIN: " + acc.getPIN());
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		} catch (IllegalAgeException e) {
			System.out.println("Age > 16 can not open junior account.");
		}
		return null;
	}

	/**
	 * This function opens a current account without a specified overdraft limit.
	 * Overdraft will be set 500 by default.
	 *
	 * @param initBalance Initial balance.
	 * @param customer    The customer who wants to open an account.
	 * @return If open successfully, returns account. If fails, return null.
	 */
	CurrentAccount openCurrentAccount(double initBalance, Customer customer) {
		return openCurrentAccount(initBalance, customer, 500);
	}

	/**
	 * This function opens a current account.
	 *
	 * @param initBalance Initial balance.
	 * @param customer The customer who wants to open an account.
	 * @param overdraftLimit The overdraft limit.
	 * @return If open successfully, returns account. If fails, return null.
	 */
	CurrentAccount openCurrentAccount(double initBalance, Customer customer, double overdraftLimit) {
		try {
			CurrentAccount acc = new CurrentAccount(initBalance, customer, overdraftLimit);
			while (checkDuplicated(acc.getNo())) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			System.out.println("Open account successfully.");
			System.out.println("Account no: " + acc.getNo() + ", PIN: " + acc.getPIN());
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		}
		return null;
	}

	/**
	 * This function closes a account.
	 * Before closing, this function checks if the balance of this account is cleared.
	 * If the balance is not cleared, stops the closing.
	 * If cleared, closes it.
	 *
	 * @param acc The account which you wants to close.
	 */
	void closeAccount(BankAccount acc) {
		if (acc.getBalance() != 0) {
			System.out.println("The balance is not cleared.");
			return;
		}
		bankAccounts.remove(acc);
		System.out.println("Close account successfully.");
	}

	/**
	 * This function updates all account.
	 */
	void update() {
		for (BankAccount acc : bankAccounts) {
			acc.clearFunds();
			if (acc.getBalance() < 0) {
				System.out.println(acc.getName() + " is in overdraft, a letter is sent");
			}
		}
	}

}
