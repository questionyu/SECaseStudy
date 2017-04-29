/**
 * Title        CreditAgency.java
 * Description  This class pretends to check the user's credit history.
 */
class CreditAgency {
	/**
	 * This function gives 10% false and gives 90% true.
	 * False: Credit history is bad.
	 * True: Credit history is good.
	 *
	 * @param accountName The user's name.
	 * @return Boolean variable.
	 */
	static boolean checkCreditHistory(String accountName) {
		return (Math.random() < 0.1);
	}
}
