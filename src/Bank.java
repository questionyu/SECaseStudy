import java.util.ArrayList;

/**
 * Title        Bank.java
 * Description
 */
class Bank {
	private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

	boolean checkDuplicated(BankAccount newAccount) {
		for (BankAccount i : bankAccounts) {
			if (i.getNo() == newAccount.getNo())
				return true;
		}
		return false;
	}

	SaverAccount openSaverAccount(double initBalance, String name, String address, int birth) {
		try {
			SaverAccount acc = new SaverAccount(initBalance, name, address, birth);
			while (checkDuplicated(acc)) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		}
		return null;
	}

	JuniorAccount openJuniorAccount(double initBalance, String name, String address, int birth, int age) {
		try {
			JuniorAccount acc = new JuniorAccount(initBalance, name, address, birth, age);
			while (checkDuplicated(acc)) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		} catch (IllegalAgeException e) {
			System.out.println("Age > 16 can not open junior account.");
		}
		return null;
	}

	CurrentAccount openCurrentAccount(double initBalance, String name, String address, int birth) {
		return openCurrentAccount(initBalance, name, address, birth, 500);
	}

	CurrentAccount openCurrentAccount(double initBalance, String name, String address, int birth, double overdraftLimit) {
		try {
			CurrentAccount acc = new CurrentAccount(initBalance, name, address, birth, overdraftLimit);
			while (checkDuplicated(acc)) {
				acc.setRandomNo();
			}
			bankAccounts.add(acc);
			return acc;
		} catch (CreditHistoryException e) {
			System.out.println("This user's credit history is bad. Can not open a new account.");
		}
		return null;
	}

	void closeAccount(BankAccount acc) {
		if (acc.getBalance() != 0) {
			System.out.println("The balance is not cleared.");
			return;
		}
		bankAccounts.remove(acc);
	}

	void update() {
		for (BankAccount acc : bankAccounts) {
			acc.clearFunds();
			if (acc.getBalance() < 0) {
				System.out.println(acc.getName() + " is in overdraft, a letter is sent");
			}
		}
	}

}
