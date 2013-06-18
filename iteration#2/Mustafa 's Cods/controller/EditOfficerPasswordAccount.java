package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import connection.DBConnection;

import model.Login;

public class EditOfficerPasswordAccount implements ActionListener {

	JPasswordField lastPassword;
	JPasswordField newPassword;
	JDialog dialog;

	public EditOfficerPasswordAccount(JPasswordField lastPassword,
			JPasswordField newPassword, JDialog dialog) {

		this.lastPassword = lastPassword;
		this.newPassword = newPassword;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (validator() != -1) {
			changePassword();
		}

	}

	private void changePassword() {

		// Temp cods
		String status = "";
		String userName = "";
		String password = "";
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("select status,user_name,Password from user_account");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				status = result.getString(1);
				userName = result.getString(2);
				password = result.getString(3);

				Login loadedAccount = new Login(status, userName, password);
				Login.userAccountList.add(loadedAccount);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (lastPassword.getText().equalsIgnoreCase(
				Login.userAccountList.get(0).getPassWord())) {

			try {
				PreparedStatement statement = DBConnection.connection
						.prepareStatement("update user_account set password = '"
								+ newPassword.getText()
								+ "' where status = 'officer'");
				statement.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			updateAccountList();
			new JOptionPane().showMessageDialog(null, "Done!");
			dialog.dispose();

		} else {
			new JOptionPane().showMessageDialog(null,
					"کلمه عبور قبلی درست نیست، لطفا دقت کنید!");
		}
	}

	private void updateAccountList() {
		String status = "";
		String userName = "";
		String password = "";

		for (int counter = 0; counter < Login.userAccountList.size(); counter++) {
			Login.userAccountList.remove(counter);
		}

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("select status,user_name,Password from user_account");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				status = result.getString(1);
				userName = result.getString(2);
				password = result.getString(3);

				Login loadedAccount = new Login(status, userName, password);
				Login.userAccountList.add(loadedAccount);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int validator() {

		if (lastPassword.getText().trim().isEmpty()) {
			new JOptionPane().showMessageDialog(null,
					"please insert the last password");
			return -1;
		} else if (newPassword.getText().trim().isEmpty()) {
			new JOptionPane().showMessageDialog(null,
					"please insert the new password");
			return -1;
		}

		return 0;
	}

}
