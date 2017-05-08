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
	 * The logged account.
	 */
	private BankAccount acc;

	/**
	 * The constructor function of UserInterface.
	 * This function run the init function periodically.
	 */
	private UserInterface() {
		while (true) {
			System.out.println("Press ENTER to continue:");
			GetSafeInput.getString(sc);
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
		System.out.println("======================================================");
		System.out.println("=                   Banking System                   =");
		System.out.println("======================================================");
		System.out.println("=1.Open an account.                                  =");
		System.out.println("=2.Login.                                            =");
		System.out.println("=3.Bank management.                                  =");
		System.out.println("=0.Exit.                                             =");
		System.out.println("======================================================");
		switch (GetSafeInput.getInt(sc)) {
			case 1:
				openAccount();
				break;
			case 2:
				checkLogin();
				break;
			case 3:
				bank();
				break;
			case 0:
				myBank.saveBankAccounts();
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
		System.out.println("==============================");
		System.out.println("=        Open Account        =");
		System.out.println("==============================");
		System.out.print("Name: ");
		String name = GetSafeInput.getString(sc);
		System.out.print("Address: ");
		String address = GetSafeInput.getString(sc);
		System.out.print("Birth (e.g. 1996-02-29): ");
		Calendar birth = GetSafeInput.getCalendar(sc);
		Customer customer = new Customer(name, address, birth);
		System.out.print("Initial balance: ");
		double initBalance = GetSafeInput.getPositiveDouble(sc);
		System.out.println("Which type account do you want to open?");
		System.out.println("1.Saver account.");
		System.out.println("2.Current account.");
		System.out.println("3.Junior account.");
		System.out.println("4.Cancel.");
		while (true) {
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					if (myBank.openSaverAccount(initBalance, customer) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					return;
				case 2:
					System.out.print("Overdraft Limit: ");
					Double overdraftLimit = GetSafeInput.getPositiveDouble(sc);
					if (myBank.openCurrentAccount(initBalance, customer, overdraftLimit) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					return;
				case 3:
					if (myBank.openJuniorAccount(initBalance, customer) == null) {
						System.out.println("Please choose another account type:");
						break;
					}
					return;
				case 4:
					System.out.println("Operation cancelled.");
					return;
				default:
					System.out.println("Input wrong, please retype:");
			}
		}
	}

	/**
	 * This function leads the user to login system.
	 */
	private void checkLogin() {
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
			if (PIN.equals("cancel")) {
				System.out.println("Operation cancelled.");
				return;
			}
		}
		if (acc.isSuspended()) {
			System.out.println("This account is suspended.");
			return;
		}
		System.out.println("Logged in.");
		this.acc = acc;
		user();
	}

	/**
	 * Provides the UI of the user transactions.
	 */
	private void user() {
		while (true) {
			System.out.println("Press ENTER to continue:");
			GetSafeInput.getString(sc);
			System.out.println("================================");
			System.out.println("=             User             =");
			System.out.println("================================");
			System.out.println("=1.Check balances.             =");
			System.out.println("=2.Deposit funds.              =");
			System.out.println("=3.Withdraw funds.             =");
			System.out.println("=4.Pre-withdraw.               =");
			System.out.println("=0.Exit login.                 =");
			System.out.println("================================");
			System.out.println("Type your choice:");
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					checkBalance();
					break;
				case 2:
					deposit();
					break;
				case 3:
					withdraw();
					break;
				case 4:
					preWithdraw();
					break;
				case 0:
					System.out.println("Exit login successfully.");
					return;
				default:
					System.out.println("Input wrong, please retype:");
			}
		}
	}

	/**
	 * This function shows the balance of the account.
	 */
	private void checkBalance() {
		System.out.println("=========================");
		System.out.println("=      Check Balance    =");
		System.out.println("=========================");
		acc.checkBalance();
	}

	/**
	 * This function provides the UI of the operation of depositing.
	 */
	private void deposit() {
		System.out.println("=========================");
		System.out.println("=        Deposit        =");
		System.out.println("=========================");
		System.out.println("Deposit by Cash/Cheque?");
		System.out.println("1.Cash.");
		System.out.println("2.Cheque.");
		System.out.println("3.Cancel.");
		while (true) {
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					System.out.print("Deposit by cash amount: ");
					double cashAmount = GetSafeInput.getPositiveDouble(sc);
					acc.depositByCash(cashAmount);
					return;
				case 2:
					System.out.print("Deposit by cheque amount: ");
					double chequeAmount = GetSafeInput.getPositiveDouble(sc);
					acc.depositByCheque(chequeAmount);
					return;
				case 3:
					System.out.println("Operation cancelled.");
					return;
				default:
					System.out.println("Input wrong, please retype:");
			}
		}
	}

	/**
	 * This function provides the UI of the operation of withdrawing.
	 */
	private void withdraw() {
		System.out.println("=========================");
		System.out.println("=        Withdraw       =");
		System.out.println("=========================");
		System.out.print("Withdraw amount: ");
		double amount = GetSafeInput.getPositiveDouble(sc);
		acc.withdraw(amount);
	}

	/**
	 * This function provides the UI of the operation of pre-withdrawing.
	 * Saver account only.
	 */
	private void preWithdraw() {
		System.out.println("=============================");
		System.out.println("=        Pre-withdraw       =");
		System.out.println("=============================");
		System.out.print("Pre-withdraw amount: ");
		double amount = GetSafeInput.getPositiveDouble(sc);
		acc.preWithdraw(amount);
	}

	/**
	 * Provides the UI of the bank management.
	 */
	private void bank() {
		while (true) {
			System.out.println("Press ENTER to continue:");
			GetSafeInput.getString(sc);
			System.out.println("================================");
			System.out.println("=         Bank Management      =");
			System.out.println("================================");
			System.out.println("=1.Suspend an account.         =");
			System.out.println("=2.Reinstate an account.       =");
			System.out.println("=3.Close an account.           =");
			System.out.println("=4.Set overdraft limit.        =");
			System.out.println("=5.Update all accounts.        =");
			System.out.println("=6.Clear all accounts' funds.  =");
			System.out.println("=0.Back.                       =");
			System.out.println("================================");
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					suspend();
					break;
				case 2:
					reinstate();
					break;
				case 3:
					closeAccount();
					break;
				case 4:
					setOverdraftLimit();
					break;
				case 5:
					updateAccounts();
					break;
				case 6:
					clearFunds();
					break;
				case 0:
					return;
				default:
					System.out.println("Input wrong, please retype:");
			}
		}
	}

	/**
	 * This function provides the UI of the operation of suspending.
	 */
	private void suspend() {
		System.out.println("========================");
		System.out.println("=        Suspend       =");
		System.out.println("========================");
		System.out.print("Account no: ");
		int no = GetSafeInput.getInt(sc);
		BankAccount acc = myBank.getAccount(no);
		if (acc == null) {
			System.out.println("This account does not exist.");
			return;
		}
		if (acc.isSuspended()) {
			System.out.println("This account already be suspended.");
			System.out.println("Operation cancelled.");
			return;
		}
		acc.suspend();
		System.out.println(acc);
	}

	/**
	 * This function provides the UI of the operation of reinstating.
	 */
	private void reinstate() {
		System.out.println("========================");
		System.out.println("=        Reinstate     =");
		System.out.println("========================");
		System.out.print("Account no: ");
		int no = GetSafeInput.getInt(sc);
		BankAccount acc = myBank.getAccount(no);
		if (acc == null) {
			System.out.println("This account does not exist.");
			return;
		}
		if (!acc.isSuspended()) {
			System.out.println("This account is not suspended.");
			System.out.println("Operation cancelled.");
			return;
		}
		acc.reinstate();
		System.out.println(acc);
	}

	/**
	 * This function provides the UI of the operation of closing account.
	 */
	private void closeAccount() {
		System.out.println("==============================");
		System.out.println("=        Close Account       =");
		System.out.println("==============================");
		myBank.closeAccount(acc);
	}

	/**
	 * This function provides the UI of the operation of setting overdraft limit.
	 * Current account only.
	 */
	private void setOverdraftLimit() {
		System.out.println("=============================");
		System.out.println("=    Set Overdraft Limit    =");
		System.out.println("=============================");
		System.out.print("Account no: ");
		int no = GetSafeInput.getInt(sc);
		BankAccount acc = myBank.getAccount(no);
		if (acc == null) {
			System.out.println("This account does not exist.");
			return;
		}
		if (acc.isSuspended()) {
			System.out.println("This account is suspended.");
			System.out.println("Operation cancelled.");
			return;
		}
		System.out.print("New overdraft limit: ");
		double overdraftLimit = GetSafeInput.getPositiveDouble(sc);
		acc.setOverdraftLimit(overdraftLimit);
	}

	/**
	 * This function updates all bank accounts.
	 */
	private void updateAccounts() {
		System.out.println("Bank is updating all accounts...");
		myBank.update();
	}

	/**
	 * This function clear all bank accounts' funds.
	 */
	private void clearFunds() {
		System.out.println("Bank is clearing all accounts's funds...");
		myBank.clearFunds();
	}
}
