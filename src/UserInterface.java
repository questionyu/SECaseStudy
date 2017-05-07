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
		System.out.println("======================================================");
		System.out.println("=                   Banking System                   =");
		System.out.println("======================================================");
		System.out.println("=1.Open an account.                                  =");
		System.out.println("=2.Check balances.                                   =");
		System.out.println("=3.Deposit funds.                                    =");
		System.out.println("=4.Withdraw funds.                                   =");
		System.out.println("=5.Suspend an account.                               =");
		System.out.println("=6.Reinstate an account.                             =");
		System.out.println("=7.Close an account.                                 =");
		System.out.println("=8.Pre-withdraw. (Saver account only.)               =");
		System.out.println("=9.Set overdraft limit. (Current account only.)      =");
		System.out.println("=10.Update all accounts.                             =");
		System.out.println("=11.Clear all accounts' funds.                       =");
		System.out.println("=0.Exit.                                             =");
		System.out.println("======================================================");
		System.out.println("Type your choice:");
		switch (GetSafeInput.getInt(sc)) {
			case 1:
				openAccount();
				break;
			case 2:
				checkBalance();
				break;
			case 3:
				deposit();
				break;
			case 4:
				withdraw();
				break;
			case 5:
				suspend();
				break;
			case 6:
				reinstate();
				break;
			case 7:
				closeAccount();
				break;
			case 8:
				preWithdraw();
				break;
			case 9:
				setOverdraftLimit();
				break;
			case 10:
				updateAccounts();
				break;
			case 11:
				clearFunds();
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
					System.out.print("Overdraft Limit: ");
					Double overdraftLimit = GetSafeInput.getPositiveDouble(sc);
					if (myBank.openCurrentAccount(initBalance, customer, overdraftLimit) == null) {
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
	 * This function leads the user to login system.
	 * If login successfully, returns the bank account.
	 * If failed, return null.
	 *
	 * @return Bank account or null.
	 */
	private BankAccount checkLogin() {
		System.out.print("Account no: ");
		int no = GetSafeInput.getInt(sc);
		BankAccount acc = myBank.getAccount(no);
		if (acc == null) {
			System.out.println("This account does not exist.");
			return null;
		}
		System.out.print("PIN: ");
		String PIN = GetSafeInput.getString(sc);
		if (!acc.checkPIN(PIN)) {
			System.out.print("Wrong PIN! Please retype or type 'cancel' to cancel: ");
			PIN = GetSafeInput.getString(sc);
			if (PIN.equals("cancel"))
				System.out.println("Operation cancelled.");
			return null;
		}
		if (acc.isSuspended()) {
			System.out.println("This account is suspended.");
			return null;
		}
		System.out.println("Logged in.");
		return acc;
	}

	private void checkBalance() {
		System.out.println("=========================");
		System.out.println("=      Check Balance    =");
		System.out.println("=========================");
		BankAccount acc = checkLogin();
		if (acc == null)
			return;
		acc.checkBalance();
	}

	/**
	 * This function provides the UI of the operation of depositing.
	 */
	private void deposit() {
		System.out.println("=========================");
		System.out.println("=        Deposit        =");
		System.out.println("=========================");
		BankAccount acc = checkLogin();
		if (acc == null)
			return;
		System.out.println("Deposit by Cash/Cheque?");
		System.out.println("1.Cash.");
		System.out.println("2.Cheque.");
		System.out.println("3.Cancel.");
label:
		while (true) {
			switch (GetSafeInput.getInt(sc)) {
				case 1:
					System.out.print("Deposit by cash amount: ");
					double cashAmount = GetSafeInput.getPositiveDouble(sc);
					acc.depositByCash(cashAmount);
					break label;
				case 2:
					System.out.print("Deposit by cheque amount: ");
					double chequeAmount = GetSafeInput.getPositiveDouble(sc);
					acc.depositByCheque(chequeAmount);
					break label;
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
		BankAccount acc = checkLogin();
		if (acc == null)
			return;
		System.out.print("Withdraw amount: ");
		double amount = GetSafeInput.getPositiveDouble(sc);
		acc.withdraw(amount);
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
		BankAccount acc = checkLogin();
		if (acc == null)
			return;
		myBank.closeAccount(acc);
	}

	/**
	 * This function provides the UI of the operation of pre-withdrawing.
	 * Saver account only.
	 */
	private void preWithdraw() {
		System.out.println("=============================");
		System.out.println("=        Pre-withdraw       =");
		System.out.println("=============================");
		BankAccount acc = checkLogin();
		if (acc == null)
			return;
		System.out.print("Pre-withdraw amount: ");
		double amount = GetSafeInput.getPositiveDouble(sc);
		acc.preWithdraw(amount);
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
		double overdraftLimit = GetSafeInput.getPositiveDouble(sc);
		acc.setOverdraftLimit(overdraftLimit);
	}

	private void updateAccounts() {
		System.out.println("Bank is updating all accounts...");
		myBank.update();
	}

	private void clearFunds() {
		System.out.println("Bank is clearing all accounts's funds...");
		myBank.clearFunds();
	}
}
