public class CurrentAccount extends BankAccount {
	private double overdraftLimit;

	CurrentAccount(int accNo, String accName) {
		super(accNo, accName);
		overdraftLimit = 500;
	}

	CurrentAccount(String accName, int accNo) {
		super(accName, accNo);
		overdraftLimit = 500;
	}

	CurrentAccount(int accNo, String accName, double overdraftLimit) {
		super(accNo, accName);
		this.setOverdraftLimit(overdraftLimit);
	}

	double getOverdraftLimit() {
		return overdraftLimit;
	}

	void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	@Override
	boolean check(double amount) {
		boolean allowed = false;
		if (this.getBalance() - amount >= -overdraftLimit) {
			allowed = true;
		} else {
			System.out.println("Withdraw " + amount
					+ " unsuccessfull. Do not have enough available funds.");
		}
		return allowed;
	}
}