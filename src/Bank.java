import java.util.ArrayList;

class Bank {
	private ArrayList<BankAccount> bank = new ArrayList<>();

	void openAccount(BankAccount b) {
		bank.add(b);
	}

	void closeAccount(BankAccount b) {
		bank.remove(b);
	}

	void update() {
		for (BankAccount b : bank) {
			if (b.getBalance() < 0) {
				System.out.println(b.getAccName()
						+ " is in overdraft, a letter is sent");
			}
		}
	}

}
