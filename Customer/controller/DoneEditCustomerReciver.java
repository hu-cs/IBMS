package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.CustomerDetails;
import view.CustomersList;
import DataBase.DBConnection;

public class DoneEditCustomerReciver implements ActionListener {

	JTable editCustomerPaymentTable;
	JDialog dialog;
	int dataYearId = DataYearId.getDataYearId();
	public DoneEditCustomerReciver(JTable editCustomerPayment, JDialog dialog) {
		super();
		this.editCustomerPaymentTable = editCustomerPayment;
		this.dialog = dialog;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(validator()!= -1 ){
		insertToGiveingAccuount(CustomerDetails.giveing);
		load(CustomerDetails.giveing);
		setTotal();
		companyDemand();
		dialog.dispose();
		}
	}

	public int validator() {
		try {
			float editCost = Float.parseFloat(editCustomerPaymentTable
					.getValueAt(0, 1).toString());
			String editDate = editCustomerPaymentTable.getValueAt(0, 0).toString();
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,
					CustomersList.PleaseEnterAllInformation , CustomersList.Information,
					JOptionPane.INFORMATION_MESSAGE);

			return -1;
		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null,
					CustomersList.PleaseInsertCorrectInformation, CustomersList.Information,
					JOptionPane.INFORMATION_MESSAGE);

			return -1;
		}
		return 0;
	}

	private void insertToGiveingAccuount(JTable giveingTable) {
		// TODO Auto-generated method stub
		int selectedRow = giveingTable.getSelectedRow();
		float giveingCost = Float.parseFloat(giveingTable.getValueAt(
				selectedRow, 1).toString());
		String giveingDate = giveingTable.getValueAt(selectedRow, 0).toString();
		float editCost = Float.parseFloat(editCustomerPaymentTable.getValueAt(
				0, 1).toString());
		String editDate = editCustomerPaymentTable.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("UPDATE customer_giving_account SET Cost="
							+ editCost
							+ ", Date_And_Time='"
							+ editDate
							+ "' WHERE Cost="
							+ giveingCost
							+ " AND Date_And_Time= '" + giveingDate + "' And datayearid = "+dataYearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void load(JTable giveingAccount) {
		// TODO Auto-generated method stub

		// int SelectedRow = customerTable.getSelectedRow();
		// String name = customerTable.getValueAt(SelectedRow, 6).toString();

		String name = CustomersList.customerName;
		String lastName = CustomersList.customerLastName;
		float Cost;
		String DateAndTime;

		((DefaultTableModel) giveingAccount.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account  WHERE Name = '"
							+ name + "' And last_name = '" + lastName + "' And datayearid = "+dataYearId);

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
