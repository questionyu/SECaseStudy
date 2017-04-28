import javax.swing.*;

/**
 * Title        UserInterface.java
 * Description  This class provide a command line tool to user.
 */
public class UserInterface {
	private UserInterface() {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> (new UserInterface()).init());
	}

	private void init() {
		try {
			new JuniorAccount();
		} catch (IllegalAgeException e) {
			e.printStackTrace();
		}
	}

}
