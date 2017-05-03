import java.util.Calendar;

/**
 * Title        Customer.java
 * Description
 */
class Customer {
	private String name;
	private String address;
	private Calendar birth;

	Customer(String name, String address, Calendar birth) {
		this.name = name;
		this.address = address;
		this.birth = birth;
	}

	String getName() {
		return name;
	}

	String getAddress() {
		return address;
	}

	Calendar getBirth() {
		return birth;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				", birth=" + birth +
				'}';
	}
}
