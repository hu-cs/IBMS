package controller;

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

import connection.DBConnection;

public class CompoBoxActionListener implements ActionListener {

	JComboBox<String> typeList;
	JTable table;
	JLabel label;
	String dbTable;

	public CompoBoxActionListener(JComboBox<String> typeList, JTable table,
			JLabel label, String dbTable) {
		this.typeList = typeList;
		this.table = table;
		this.label = label;
		this.dbTable = dbTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setTableValues();
		setTotalCost();
		System.out.println(typeList.getSelectedItem().toString());

	}

	private void setTableValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;
		if (typeList.getSelectedIndex() == 0) {

			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `"
								+ dbTable
								+ "` WHERE Invoice_Cost>0 and datayearid = "
								+ DataYearId.getDataYearId());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					selectedTitle = result.getString(1);
					invoiceNumber = result.getString(2);
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
						.prepareStatement("SELECT Invoice_Number,Invoice_Cost,Date_And_Time FROM `"
								+ dbTable
								+ "` WHERE title='"
								+ selectedTitle
								+ "' and Invoice_Cost>0 and datayearid ="
								+ DataYearId.getDataYearId());
				ResultSet result = statement.executeQuery();
				while (result.next()) {
					invoiceNumber = result.getString(1);
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
