package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Customer;

import connection.DBConnection;
import view.CustomerDetails;
import view.CustomersList;

public class DeletePayment implements ActionListener {
	JTable giveingAccountTable;
	int dataYearId = DataYearId.getDataYearId();

	public DeletePayment(JTable giveingAccountTable) {
		super();
		this.giveingAccountTable = giveingAccountTable;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (delete() != false) {
			load(CustomerDetails.giveing);
			setTotal();
			companyDemand();
		}
	}

	private boolean delete() {
		// TODO Auto-generated method stub

		int selectedrow = giveingAccountTable.getSelectedRow();
		if (selectedrow != -1) {

			int ask = new JOptionPane().showConfirmDialog(null,
					CustomersList.AreYouSure, CustomersList.Alert,
					JOptionPane.YES_NO_OPTION, JOptionPane.YES_OPTION);

			if (ask == JOptionPane.YES_OPTION) {

				String date = giveingAccountTable.getValueAt(selectedrow, 0)
						.toString();
				float cost = Float.parseFloat(giveingAccountTable.getValueAt(
						selectedrow, 1).toString());
				String customerName = CustomersList.customerName;
				String lastName = CustomersList.customerLastName;
				String name = customerName + " " + lastName;

				try {

					PreparedStatement statement = DBConnection.connection
							.prepareStatement("DELETE FROM customer_giving_account WHERE Cost='"
									+ cost
									+ "' AND name = '"
									+ name
									+ "' and Date_And_Time = '"
									+ date
									+ "'And datayearid = " + dataYearId);

					statement.execute();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return true;
				}
				return true;
			} else {
				return false;
			}

		} else {

			new JOptionPane().showMessageDialog(null,
					CustomersList.PleaseSelectOneRow,
					CustomersList.Information, JOptionPane.INFORMATION_MESSAGE);

		}

		return true;
	}

	private void load(JTable giveingAccount) {
		// TODO Auto-generated method stub

		String customerName = CustomersList.customerName;
		String lastName = CustomersList.customerLastName;
		String name = customerName + " " + lastName;

		float Cost;
		String DateAndTime;

		((DefaultTableModel) giveingAccount.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account WHERE Name = '"
							+ name + "' And datayearid = " + dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Cost = result.getFloat(1);
				DateAndTime = result.getString(2);

				((DefaultTableModel) giveingAccount.getModel())
						.addRow(new Object[] {

						DateAndTime, Cost, giveingAccount.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setTotal() {

		float total = 0;

		for (int counter = 0; counter < CustomerDetails.giveing.getRowCount(); counter++) {

			total += Float.parseFloat(CustomerDetails.giveing.getValueAt(
					counter, 1).toString());

		}

		CustomerDetails.label_5.setText(total + "");

	}

	private void companyDemand() {
		float giveing = Float.parseFloat(CustomerDetails.label_5.getText());
		float shopping = Float.parseFloat(CustomerDetails.label_2.getText());

		float companyDemand = shopping - giveing;

		CustomerDetails.label_7.setText(companyDemand + "");

	}

}
