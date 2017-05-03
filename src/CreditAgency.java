/**
 * Title        CreditAgency.java
 * Description  This class pretends to check the user's credit history.
 */
class CreditAgency {
	/**
	 * This function gives 10% false and gives 90% true.
	 * False: Credit history is good.
	 * True: Credit history is bad.
	 *
	 * @param customer The user.
	 * @return Boolean variable.
	 */
	static boolean checkCreditHistory(Customer customer) {
		return (Math.random() < 0.1);
	}
}
