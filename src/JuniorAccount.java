/**
 * Title        JuniorAccount.java
 * Description
 */
class JuniorAccount extends BankAccount {
	private int age;

	JuniorAccount(double initBalance, String name, String address, int birth, int age) throws IllegalAgeException {
		super(initBalance, name, address, birth);
		if (age > 16)
			throw new IllegalAgeException();
		this.age = age;
	}
}
