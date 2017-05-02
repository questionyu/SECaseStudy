import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Title        GetSafeInput.java
 * Description
 */
class GetSafeInput {
	/**
	 * 阻塞式的从控制台读入一个安全的整数
	 * 说明：显式使用Scanner对象
	 *
	 * @param sc Scanner对象
	 * @return 如果是整数则返回，如果不是整数继续读取，知道读入一个整数结束
	 */
	static int getInt(Scanner sc) {
		String inputs = sc.next();
		while (!isInteger(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.next();
		}
		return Integer.parseInt(inputs);
	}

	/**
	 * 判断是否是整数
	 * 方法：利用正则表达式
	 *
	 * @param s 需要判断的字符串
	 * @return 是整数返回真，否则返回假
	 */
	private static boolean isInteger(String s) {
		Pattern pattern = Pattern.compile("\\d+");
		return pattern.matcher(s).matches();
	}

	/**
	 * 阻塞式的从控制台读入一个安全的浮点数
	 * 说明：显式使用Scanner对象
	 *
	 * @param sc Scanner对象
	 * @return 如果是浮点数则返回，如果不是浮点数继续读取，知道读入一个正确的浮点数结束
	 */
	static double getDouble(Scanner sc) {
		String inputs = sc.next();
		while (!isDouble(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.next();
		}
		return Double.parseDouble(inputs);
	}

	/**
	 * 判断是否是浮点数
	 * 方法：利用正则表达式
	 *
	 * @param s 需要判断的字符串
	 * @return 是浮点数返回真，否则返回假
	 */
	private static boolean isDouble(String s) {
		Pattern pattern = Pattern.compile("\\d+(\\.?)\\d*");
		return pattern.matcher(s).matches();
	}

	/**
	 * 显式使用Scanner对象，从控制台读入字符
	 * 添加日期：2012-8-14
	 *
	 * @return 从控制台获取的字符
	 */
	static String getString(Scanner sc) {
		return sc.next();
	}

	static Calendar getCalendar(Scanner sc) {
		Calendar inputCalendar = Calendar.getInstance();
		String inputs = sc.next();
		while (!isCalendar(inputs)) {
			System.out.println("Input wrong, please retype:");
			inputs = sc.next();
		}
		String[] get = inputs.split("-");
		inputCalendar.set(Integer.parseInt(get[0]), Integer.parseInt(get[1]) - 1, Integer.parseInt(get[2]));
		return inputCalendar;
	}

	private static boolean isCalendar(String s) {
		Pattern pattern = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[13579][26])00))-02-29)");
//		Pattern pattern = Pattern.compile("((^((1[8-9]\\d{2})|([2-9]\\d{3}))-(10|12|0?[13578])-(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))-(11|0?[469])-(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))-(0?2)-(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)-(0?2)-(29)$)|(^([3579][26]00)-(0?2)-(29)$)|(^([1][89][0][48])-(0?2)-(29)$)|(^([2-9][0-9][0][48])-(0?2)-(29)$)|(^([1][89][2468][048])-(0?2)-(29)$)|(^([2-9][0-9][2468][048])-(0?2)-(29)$)|(^([1][89][13579][26])-(0?2)-(29)$)|(^([2-9][0-9][13579][26])-(0?2)-(29)$))");
		return pattern.matcher(s).matches();
	}
}