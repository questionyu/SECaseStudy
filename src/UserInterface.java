import javax.swing.*;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Title        UserInterface.java
 * Description  This class provide a command line tool to user.
 */
public class UserInterface {
	/**
	 * The Scanner's instance.
	 * Be used for scan the input from console.
	 */
	private Scanner sc = new Scanner(System.in);

	/**
	 * The Bank's instance.
	 * Be used for storing all bank accounts.
	 */
	private Bank myBank = new Bank();

	/**
	 * The constructor function of UserInterface.
	 * This function run the init function periodically.
	 */
	private UserInterface() {
		while (true) {
			init();
		}
	}

	/**
	 * Program starts from here.
	 *
	 * @param args Useless arguments.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(UserInterface::new);
	}

	/**
	 * The initial function.
	 * Provide the UI of this program.
	 */
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
				System.out.println("Bye~");
				System.exit(0);
			default:
				System.out.println("Input wrong, please retype:");
		}
	}

	/**
	 * This function provides the UI of the operation of opening account.
	 */
	private void openAccount() {
		System.out.print("Name: ");
		String name = GetSafeInput.getString(sc);
		System.out.print("Address: ");
		String address = GetSafeInput.getString(sc);
		System.out.print("Birth: ");
		Calendar birth = GetSafeInput.getCalendar(sc);
		Customer customer = new Customer(name, address, birth);
		System.out.print("Initial balance: ");
		int initBalance = GetSafeInput.getPositiveInt(sc);
		System.out.println("Which type account do you want to open?");
		System.out.println("1.Saver account.");
		System.out.println("2.Current account.");
		System.out.println("3.Junior account.");
		System.out.println("4.Cancel.");
label:
		while (true) {
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					if (myBank.openSaverAccount(initBalance, customer) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					break label;
				case 2:
					if (myBank.openCurrentAccount(initBalance, customer) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					break label;
				case 3:
					if (myBank.openJuniorAccount(initBalance, customer) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					break label;
				case 4:
					System.out.println("Operation cancelled.");
					return;
				default:
					System.out.println("Input wrong, please retype:");
			}
		}
	}

	/**
	 * This function provides the UI of the operation of depositing.
	 */
	private void deposit() {
		System.out.print("Account no: ");
		int no = GetSafeInput.getInt(sc);
		BankAccount acc = myBank.getAccount(no);
		if (acc == null) {
			System.out.println("This account does not exist.");
			return;
		}
		System.out.print("PIN: ");
		String PIN = GetSafeInput.getString(sc);
		if (!acc.checkPIN(PIN)) {
			System.out.print("Wrong PIN! Please retype or type 'cancel' to cancel: ");
			PIN = GetSafeInput.getString(sc);
			if (PIN.equals("cancel"))
				return;
		}
		System.out.println("Logged in.");
		System.out.print("Deposit amount: ");
		double amount = GetSafeInput.getPositiveDouble(sc);
		acc.deposit(amount);
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
