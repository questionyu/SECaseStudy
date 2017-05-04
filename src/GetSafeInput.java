import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Title        GetSafeInput.java
 * Description  This class provides some useful functions to get some variables from console safely.
 */
abstract class GetSafeInput {
	/**
	 * This function gets a positive int from console.
	 *
	 * @param sc Scanner
	 * @return The positive int from console.
	 */
	static int getPositiveInt(Scanner sc) {
		int positiveInt;
		while ((positiveInt = getInt(sc)) <= 0) {
			System.out.println("Input wrong, please retype:");
		}
		return positiveInt;
	}

	/**
	 * This function gets an int from console.
	 *
	 * @param sc Scanner
	 * @return The int from console.
	 */
	static int getInt(Scanner sc) {
		String inputs = sc.nextLine();
		while (!isInteger(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.nextLine();
		}
		return Integer.parseInt(inputs);
	}

	/**
	 * This function judges the parameter is or not an int.
	 *
	 * @param s The string which need to be judged.
	 * @return If the parameter is an int, return true. Vice versa.
	 */
	private static boolean isInteger(String s) {
		Pattern pattern = Pattern.compile("-?\\d+");
		return pattern.matcher(s).matches();
	}

	/**
	 * This function gets a positive double from console.
	 *
	 * @param sc Scanner
	 * @return The positive double from console.
	 */
	static double getPositiveDouble(Scanner sc) {
		double positiveDouble;
		while ((positiveDouble = getDouble(sc)) <= 0) {
			System.out.println("Input wrong, please retype:");
		}
		return positiveDouble;
	}

	/**
	 * This function gets a double from console.
	 *
	 * @param sc Scanner
	 * @return The double from console.
	 */
	static double getDouble(Scanner sc) {
		String inputs = sc.nextLine();
		while (!isDouble(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.nextLine();
		}
		return Double.parseDouble(inputs);
	}

	/**
	 * This function judges the parameter is or not a double.
	 *
	 * @param s The string which need to be judged.
	 * @return If the parameter is a double, return true. Vice versa.
	 */
	private static boolean isDouble(String s) {
		Pattern pattern = Pattern.compile("-?\\d+\\.?\\d*");
		return pattern.matcher(s).matches();
	}

	/**
	 * This function gets a string from console.
	 *
	 * @param sc Scanner
	 * @return The string from console.
	 */
	static String getString(Scanner sc) {
		return sc.nextLine();
	}

	/**
	 * This function gets a calendar from console.
	 *
	 * @param sc Scanner
	 * @return The calendar from console.
	 */
	static Calendar getCalendar(Scanner sc) {
		Calendar inputCalendar = Calendar.getInstance();
		String inputs = sc.nextLine();
		while (!isCalendar(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.nextLine();
		}
		String[] get = inputs.split("-");
		inputCalendar.set(Integer.parseInt(get[0]), Integer.parseInt(get[1]) - 1, Integer.parseInt(get[2]));
		return inputCalendar;
	}

	/**
	 * This function judges the parameter is or not a calendar.
	 * This function matches:
	 * 1996-02-29 1996-2-29 1996-2-2
	 * does not match:
	 * 1996-02-30 1996-2-31 1995-2-29 1995-2-0
	 *
	 * @param s The string which need to be judged.
	 * @return If the parameter is a calendar, return true. Vice versa.
	 */
	private static boolean isCalendar(String s) {
		Pattern pattern = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})-(((0?[13578]|1[02])-(0?[1-9]|[12][0-9]|3[01]))|((0?[469]|11)-(0?[1-9]|[12][0-9]|30))|(0?2-(0?[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[13579][26])00))-0?2-29)");
		return pattern.matcher(s).matches();
	}
}