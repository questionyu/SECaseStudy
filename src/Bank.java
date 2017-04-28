import java.util.ArrayList;

class Bank {
	private ArrayList<BankAccount> bankAccounts = new ArrayList<>();

	boolean checkDuplicated(BankAccount newAccount) {
		for (BankAccount i : bankAccounts) {
			if (i.getNo() == newAccount.getNo())
				return true;
		}
		return false;
	}

	void openAccount(BankAccount newAccount) {
		bankAccounts.add(newAccount);
	}

	void closeAccount(BankAccount b) {
		bankAccounts.remove(b);
	}

	void update() {
		for (BankAccount b : bankAccounts) {
			if (b.getBalance() < 0) {
				System.out.println(b.getName() + " is in overdraft, a letter is sent");
			}
		}
	}

}
