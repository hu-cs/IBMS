package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class RawComboboxListener implements ActionListener {

	JTable table;
	JLabel label;
	JComboBox<String> selectedTypeCombo;
	int dataYearId = DataYearId.getDataYearId();

	public RawComboboxListener(JTable table, JLabel label,
			JComboBox<String> selectedTypeCombo) {
		this.table = table;
		this.label = label;
		this.selectedTypeCombo = selectedTypeCombo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setTableValues();
		setTotalCost();

	}

	private void setTableValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String invoiceNumber;
		float totalCost;
		String dateAndTime;
		String selectedTitle;
		float cost;
		float qty;
		String unit;

		if (selectedTypeCombo.getSelectedIndex() == 0) {
			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("select title,invoice_number,quantity,unit,cost,Invoice_Cost,dateAndTime from raw_material where Invoice_Cost>0 and datayearid="
								+ dataYearId);

				ResultSet result = statement.executeQuery();

				while (result.next()) {
					selectedTitle = result.getString(1);
					invoiceNumber = result.getString(2);
					qty = result.getFloat(3);
					unit = result.getString(4);
					cost = result.getFloat(5);
					totalCost = result.getFloat(6);
					dateAndTime = result.getString(7);
					System.err.println(totalCost);

					System.out.println(invoiceNumber);

					((DefaultTableModel) table.getModel()).addRow(new Object[] {
							dateAndTime, totalCost, qty, cost, unit,
							invoiceNumber, selectedTitle,
							table.getRowCount() + 1 });

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			selectedTitle = selectedTypeCombo.getSelectedItem().toString();

			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("select title,invoice_number,quantity,unit,cost,Invoice_Cost,dateAndTime from raw_material where Invoice_Cost>0 and datayearid="
								+ dataYearId + " and title ='" + selectedTitle+"'");

				ResultSet result = statement.executeQuery();

				while (result.next()) {
					selectedTitle = result.getString(1);
					invoiceNumber = result.getString(2);
					qty = result.getFloat(3);
					unit = result.getString(4);
					cost = result.getFloat(5);
					totalCost = result.getFloat(6);
					dateAndTime = result.getString(7);
					System.err.println(totalCost);

					System.out.println(invoiceNumber);

					((DefaultTableModel) table.getModel()).addRow(new Object[] {
							dateAndTime, totalCost, qty, cost, unit,
							invoiceNumber, selectedTitle,
							table.getRowCount() + 1 });

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
