package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connection.DBConnection;

public class passwordPerform implements ActionListener {

	JTextField textField;
	JPasswordField passwordField;
	String lastPassword;

	public passwordPerform(JTextField textField, JPasswordField passwordField) {
		super();
		this.textField = textField;
		this.passwordField = passwordField;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (moqayse() != 0) {
			System.out.println("enter the password");

		} else
			System.out.println("your password is writ");
	}

	private int moqayse() {
		String user_name = textField.getText().toString();
		String newPassword = passwordField.getText().toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT password FROM `log_in` WHERE user_name = '"
							+ user_name + "'");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				lastPassword = result.getString(1);

			}

			if (newPassword != lastPassword) {
				System.out.println("this password is not true");
				return -1;
			} else {
				System.out.println("this password is true");
				return 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
