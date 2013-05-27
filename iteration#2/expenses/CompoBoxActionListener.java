package controler.expenses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

public class CompoBoxActionListener implements ActionListener {

	JComboBox<String> typeList;
	JTable table;
	JLabel label;

	public CompoBoxActionListener(JComboBox<String> typeList, JTable table,
			JLabel label) {
		this.typeList = typeList;
		this.table = table;
		this.label = label;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setTableValues();
		setTotalCost();

	}

	private void setTableValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		int invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;
		if (typeList.getSelectedIndex() == 0) {

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
							dateAndTime, invoiceCost, invoiceNumber,
							selectedTitle, table.getRowCount() + 1 });

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

			selectedTitle = typeList.getSelectedItem().toString();
			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("SELECT Invoice_Number,Invoice_Cost,Date_And_Time FROM `corruptionconsumption` WHERE title='"
								+ selectedTitle + "' and Invoice_Number>0");
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					invoiceNumber = result.getInt(1);
					invoiceCost = result.getFloat(2);
					dateAndTime = result.getString(3);
					((DefaultTableModel) table.getModel()).addRow(new Object[] {
							dateAndTime, invoiceCost, invoiceNumber,
							selectedTitle, table.getRowCount() + 1 });
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void setTotalCost() {
		float totalCost = 0;
		for (int counter = 0; counter < table.getRowCount(); counter++) {
			totalCost += Float.parseFloat(table.getValueAt(counter, 1)
					.toString());
		}
		label.setText(totalCost + "");

	}

}
