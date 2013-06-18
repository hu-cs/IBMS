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

import connection.DBConnection;
import view.CustomerDetails;
import view.CustomersList;

public class AddToGiveingAccount implements ActionListener {

	JTable AddAccoutn;
	JDialog dialog;
	int dataYearId = DataYearId.getDataYearId();

	public AddToGiveingAccount(JTable addAccoutn, JDialog dialog) {
		super();
		AddAccoutn = addAccoutn;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (validator() != -1){
		insert(CustomersList.table);
		load(CustomerDetails.giveing);
		setTotal();
		companyDemand();

		// insertToCustomerList(CustomersList.table);
		// loadCustomer(CustomersList.table);
		dialog.dispose();
	
		}
	}

	public int validator(){
		try{
		String date = AddAccoutn.getValueAt(0, 0).toString();
		float cost = Float.parseFloat(AddAccoutn.getValueAt(0, 1).toString());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,
					CustomersList.PleaseEnterAllInformation, CustomersList.Information,
					JOptionPane.INFORMATION_MESSAGE);
		
			return -1;
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null,
					CustomersList.PleaseInsertCorrectInformation, CustomersList.Information,
					JOptionPane.INFORMATION_MESSAGE);
		
			return -1;
		}
		return 0;
	}
	private void insert(JTable customerTable) {
		// TODO Auto-generated method stub
		String name = CustomersList.customerName;
		String lastName = CustomersList.customerLastName;
		String date = AddAccoutn.getValueAt(0, 0).toString();
		float cost = Float.parseFloat(AddAccoutn.getValueAt(0, 1).toString());
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("INSERT INTO customer_giving_account (Name,Last_Name,Cost,Date_And_Time, datayearid) VALUES ('"
							+ name
							+ "','"
							+ lastName
							+ "',"
							+ cost
							+ ",'"
							+ date + "'," + dataYearId + ")");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void load(JTable giveingAccount) {
		// TODO Auto-generated method stub

		String name = CustomersList.customerName;
		String lastName = CustomersList.customerLastName;

		float Cost;
		String DateAndTime;

		((DefaultTableModel) giveingAccount.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account  WHERE Name = '"
							+ name + "'and last_name = '" + lastName + "' and dataYearId = '"+dataYearId+"'");

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

	private void insertToCustomerList(JTable customerTable) {
		// TODO Auto-generated method stub
		int selectedrow = customerTable.getSelectedRow();
		String name = customerTable.getValueAt(selectedrow, 6).toString();
		String lastName = customerTable.getValueAt(selectedrow, 5).toString();
		// String date = AddAccoutn.getValueAt(0, 0).toString();
		float giveingAccount = Float.parseFloat(customerTable.getValueAt(
				selectedrow, 1).toString());
		int cost = Integer.parseInt(AddAccoutn.getValueAt(0, 1).toString());

		float sum = giveingAccount + cost;
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("UPDATE customer_list SET Giving_Money_Account="
							+ sum
							+ " WHERE Name='"
							+ name
							+ "' And Last_Name = '" + lastName + "'");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadCustomer(JTable customerTabel) {
		// TODO Auto-generated method stub

		String name;
		String lastName;
		String fatherName;
		String address;
		float shopping;
		float payment;
		float companyDemand;

		((DefaultTableModel) customerTabel.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where datayearid = "+dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				lastName = result.getString(2);
				fatherName = result.getString(3);
				address = result.getString(4);
				shopping = result.getFloat(5);
				payment = result.getFloat(6);
				companyDemand = result.getFloat(7);

				((DefaultTableModel) customerTabel.getModel())
						.addRow(new Object[] {

						companyDemand, payment, shopping, address, fatherName,
								lastName, name, customerTabel.getRowCount() + 1 });

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
