package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import connection.DBConnection;
import view.MainUi;
import model.Login;

public class LogingConfirmBtn implements ActionListener {

	JTextField userName;
	JPasswordField password;
	JComboBox<String> dataYearId;
	JFrame frame;

	public LogingConfirmBtn(JTextField userName, JPasswordField password,
			JComboBox<String> dataYearId, JFrame frame) {
		this.userName = userName;
		this.password = password;
		this.dataYearId = dataYearId;
		this.frame = frame;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		isTrue();

	}

	private int isTrue() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		if ((userName.getText().equalsIgnoreCase(Login.userAccountList.get(0)
				.getUserName()))
				&& (password.getText().equalsIgnoreCase(Login.userAccountList
						.get(0).getPassWord()))) {

			setDataYearId();
			new MainUi();
			frame.dispose();
			frame.setVisible(false);

		} else {
			new JOptionPane().showMessageDialog(null, "Try agin");
			return -1;
		}

		System.out.println("textssssss");
		System.out.println(userName.getText());
		System.out.println(password.getText());

		System.out.println("databaseeeeeeee");
		System.out.println(Login.userAccountList.get(0).getUserName());
		System.out.println(Login.userAccountList.get(0).getPassWord());

		return 0;

	}

	// sets the program values according to the selected data year id
	private void setDataYearId() {

		int selectedYear = Integer.parseInt(dataYearId.getSelectedItem()
				.toString());
		System.out.println("setlectd year " + selectedYear);

		// deselect the previous data year id

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update data_year set is_selected = 0 where is_selected = 1");
			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// select the new data year id
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update data_year set is_selected = 1 where year_id ="
							+ selectedYear);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
