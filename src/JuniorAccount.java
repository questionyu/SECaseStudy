import java.util.Calendar;

/**
 * Title        JuniorAccount.java
 * Description
 */
class JuniorAccount extends BankAccount {
	public static final int ageLimit = 16;

	JuniorAccount(double initBalance, String name, String address, Calendar birth) throws IllegalAgeException {
		super(initBalance, name, address, birth);
		Calendar rightNow = Calendar.getInstance();
		birth.add(Calendar.YEAR, ageLimit);
		if (rightNow.after(birth))
			throw new IllegalAgeException();
	}
}
