package controler.expenses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.Option;

import newViewes.CompanyUsed;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

public class DeleteUsedTypeListener implements ActionListener {

	JTable table;
	JDialog dialog;

	public DeleteUsedTypeListener(JTable table, JDialog dialog) {
		this.table = table;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (delete() != -1) {
			updateCombobox();
			dialog.dispose();
		}

	}

	private int delete() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) {
			String title = table.getValueAt(selectedRow, 0).toString();
			int option = JOptionPane
					.showConfirmDialog(
							null,
							"درصورت پاک کردن، تمام فاکتور های بخش مربوط پاک میشود، آیا مطمئن هستید؟",
							"هشدار", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null);

			if (option == JOptionPane.YES_OPTION) {
				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete  FROM `corruptionconsumption` WHERE title='"
									+ title + "'");
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				return -1;
			}
		} else {
			new JOptionPane().showMessageDialog(null,
					"لطفا برای پاک کردن، یک ردیف را انتخاب کنید!");
			return -1;
		}

		return 0;
	}

	private void updateCombobox() {

		CompanyUsed.typeList = null;
		CompanyUsed.typeList = new ArrayList<String>();
		CompanyUsed.selectTypeCombo
				.setModel(new DefaultComboBoxModel<String>());
		// CompanyUsed.selectTypeCombo = new JComboBox<String>();
		CompanyUsed.selectTypeCombo.addItem("همه");
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM `corruptionconsumption`");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				CompanyUsed.typeList.add(type);
				CompanyUsed.selectTypeCombo.addItem(type);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
