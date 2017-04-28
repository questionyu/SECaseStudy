public class BankTest {

	public static void main(String[] args) {
		Bank myBank = new Bank();

		try {
			BankAccount acc1 = new SaverAccount(500, "John Smith", "New York", 1990801);
			myBank.checkDuplicated(acc1);
		} catch () {

		}

		myBank.openAccount(acc1);
		acc1.deposit(500);
		acc1.withdraw(200);
		System.out.println(acc1);
		acc1.withdraw(700);
		System.out.println(acc1);

		CurrentAccount acc2 = new CurrentAccount("Tom Will", );
		myBank.openAccount(acc2);
		acc2.setOverdraftLimit(1000);
		acc2.deposit(3000);
		acc2.withdraw(400);
		System.out.println(acc2);
		acc2.withdraw(2800);
		System.out.println(acc2);
		acc2.withdraw(5000);
		System.out.println(acc2);

		myBank.update();

	}

}
