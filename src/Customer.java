import java.util.Calendar;

/**
 * Title        Customer.java
 * Description  This class defines a customer.
 */
class Customer {
	/**
	 * The name of this customer.
	 */
	private String name;

	/**
	 * The address of this customer.
	 */
	private String address;

	/**
	 * The birthday of this customer.
	 */
	private Calendar birth;

	/**
	 * The constructor function of the Customer.
	 *
	 * @param name    The name of this customer.
	 * @param address The address of this customer.
	 * @param birth   The birthday of this customer.
	 */
	Customer(String name, String address, Calendar birth) {
		this.name = name;
		this.address = address;
		this.birth = birth;
	}

	/**
	 * This function returns the name of this customer in a string.
	 *
	 * @return The name of this customer.
	 */
	String getName() {
		return name;
	}

	/**
	 * This function returns the address of this customer in a string.
	 *
	 * @return The address of this customer.
	 */
	String getAddress() {
		return address;
	}

	/**
	 * This function returns the birthday of this customer in a calendar.
	 *
	 * @return The birthday of this customer.
	 */
	Calendar getBirth() {
		return birth;
	}

	/**
	 * This function returns the information of this customer.
	 * Name, address and birthday.
	 *
	 * @return The information of this customer.
	 */
	@Override
	public String toString() {
		return "Customer{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", birth=" + birth +
				'}';
	}
}
