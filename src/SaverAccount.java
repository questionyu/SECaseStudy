/**
 * Title        SaverAccount.java
 * Description
 */
class SaverAccount extends BankAccount {
	private double saverBalance;
	private double preWithdraw;

	SaverAccount(double initBalance, String name, String address, int birth) {
		super(0, name, address, birth);
		this.saverBalance = initBalance;
		this.preWithdraw = 0;
	}

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

	private boolean saverCheck(double amount) {
		return (saverBalance >= amount);
	}

	@Override
	void deposit(double amount) {
		if (isSuspended()) {
			System.out.println("Account is suspended.");
			return;
		}
		saverBalance += amount;
		System.out.println("Deposit " + amount + " successfully");
	}

	@Override
	void clearFunds() {
		saverBalance += getCheque();
		setCheque(0);
		addBalance(preWithdraw);
		preWithdraw = 0;
	}
}
