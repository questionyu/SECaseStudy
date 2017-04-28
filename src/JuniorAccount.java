/**
 * Title        JuniorAccount.java
 * Description
 */
class JuniorAccount extends BankAccount {
	private int age;

	JuniorAccount(double initBalance, String name, String address, int birth, int age) {
		super(initBalance, name, address, birth);
		if (age > 16)
			//System.out.println("Age > 16 can not open junior account.");
			throw new IllegalAgeException();
		this.age = age;
	}
}
