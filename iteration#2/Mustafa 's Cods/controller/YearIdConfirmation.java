package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class YearIdConfirmation implements ActionListener {

	JTable yearIdTable;
	JDialog dialog;

	public YearIdConfirmation(JTable yearIdTable, JDialog dialog) {
		this.yearIdTable = yearIdTable;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (yearUpdate() != -1)
			dialog.dispose();

	}

	private int yearUpdate() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		int yearId = 0;

		for (int counter = 0; counter < yearIdTable.getRowCount(); counter++) {
			if (Boolean.parseBoolean(yearIdTable.getValueAt(counter, 0)
					.toString())) {

				yearId = Integer.parseInt(yearIdTable.getValueAt(counter, 1)
						.toString());
				break;
			}
		}

		if (yearId == 0) {
			new JOptionPane()
					.showMessageDialog(null,
							"اطفا برای ادامه دادن در انجام حسابات، یک سال مالی زا انتخاب کنید!");
			return -1;
		}

		try {
			PreparedStatement updateQuery = (PreparedStatement) DBConnection.connection
					.prepareStatement("update data_year set is_selected=0 where is_selected =" + 1);

			updateQuery.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update data_year set is_selected=1 where year_id = "
							+ yearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

}
