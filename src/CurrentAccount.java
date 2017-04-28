class CurrentAccount extends BankAccount {
	private double overdraftLimit;

	CurrentAccount(double initBalance, String name, String address, int birth) {
		super(initBalance, name, address, birth);
		overdraftLimit = 500;
	}

	CurrentAccount(double initBalance, String name, String address, int birth, double overdraftLimit) {
		super(initBalance, name, address, birth);
		this.overdraftLimit = overdraftLimit;
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