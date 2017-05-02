import javax.swing.*;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Title        UserInterface.java
 * Description  This class provide a command line tool to user.
 */
public class UserInterface {
	private Scanner sc = new Scanner(System.in);
	private Bank myBank = new Bank();

	private UserInterface() {
		while (true) {
			init();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(UserInterface::new);
	}

	private void init() {
		System.out.println("================================================================");
		System.out.println("=                        Banking System                        =");
		System.out.println("================================================================");
		System.out.println("=1.Open an account.                                            =");
		System.out.println("=2.Deposit funds.                                              =");
		System.out.println("=3.Withdraw funds.                                             =");
		System.out.println("=4.Suspend an account.                                         =");
		System.out.println("=5.Reinstate an account.                                       =");
		System.out.println("=6.Close an account.                                           =");
		System.out.println("=7.Exit.                                                       =");
		System.out.println("================================================================");
		System.out.println("Type your choice:");
		switch (GetSafeInput.getInt(sc)) {
			case 1:
				openAccount();
				break;
			case 2:
				deposit();
				break;
			case 3:
				withdraw();
				break;
			case 4:
				suspend();
				break;
			case 5:
				reinstate();
				break;
			case 6:
				closeAccount();
				break;
			case 7:
				System.exit(0);
			default:
				System.out.println("Input wrong, please retype:");
		}
	}

	private void openAccount() {
		System.out.println("Name:");
		String name = GetSafeInput.getString(sc);
		System.out.println("Address:");
		String address = GetSafeInput.getString(sc);
		System.out.println("Birth:");
		Calendar birth = GetSafeInput.getCalendar(sc);
		myBank.openJuniorAccount(500, name, address, birth);
	}

	private void deposit() {

	}

	private void withdraw() {

	}

	private void suspend() {

	}

	private void reinstate() {

	}

	private void closeAccount() {

	}
}
