package controler.expenses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

public class DeleteUsedItem implements ActionListener {

	JTable table;

	public DeleteUsedItem(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (delete() != -1) {
			update();
		}

	}

	private int delete() {
		int selecteRow = table.getSelectedRow();

		if (selecteRow != -1) {

			int ask = JOptionPane.showConfirmDialog(null,
					"<html><body><p style =\"font-size:17\""
							+ ">آیا مطمدن هسنید؟</p></body></html>", "هشدار",
					JOptionPane.YES_NO_OPTION, JOptionPane.YES_OPTION, null);

			if (ask == JOptionPane.YES_OPTION) {
				String title = table.getValueAt(selecteRow, 3).toString();
				int invoiceNumber = Integer.parseInt(table.getValueAt(
						selecteRow, 2).toString());
				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete from corruptionconsumption where title='"
									+ title
									+ "' and invoice_Number ="
									+ invoiceNumber);
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			new JOptionPane().showMessageDialog(null,
					"برای پاک کردن، لطفا یک ردیف را انتخاب کنید!");
			return -1;
		}

		return 0;
	}

	private void update() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		int invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `corruptionconsumption` WHERE Invoice_Number>0");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getInt(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						dateAndTime, invoiceCost, invoiceNumber, selectedTitle,
						table.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
