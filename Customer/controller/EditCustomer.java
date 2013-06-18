package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.CustomersList;
import connection.DBConnection;

public class EditCustomer implements ActionListener {
	JTable table;
	JDialog dialog;
	int dataYearId = DataYearId.getDataYearId();

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (validator() != -1) {
			editCustomerList(CustomersList.table);
			editCustomerGiveing(CustomersList.table);
			editCustomerShopping(CustomersList.table);
			update(CustomersList.table);
			dialog.dispose();
		}
	}

	public int validator() {
		try {
			String name = table.getValueAt(0, 3).toString();
			String lastname = table.getValueAt(0, 2).toString();
			String fathername = table.getValueAt(0, 1).toString();
			String address = table.getValueAt(0, 0).toString();
		} catch (NullPointerException e) {

			return -1;
		}
		return 0;
	}

	private void editCustomerList(JTable customer) {
		// TODO Auto-generated method stub
		int selectedrow = customer.getSelectedRow();
		String editName = customer.getValueAt(selectedrow, 6).toString();
		String editLastName = customer.getValueAt(selectedrow, 5).toString();
		String editFatherName = customer.getValueAt(selectedrow, 4).toString();
		String name = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();
		String fathername = table.getValueAt(0, 1).toString();
		String address = table.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("Update  customer_list set Name = '"
							+ name + "',Last_Name = '" + lastname
							+ "',Father_Name = '" + fathername
							+ "',Address = '" + address + "' where Name = '"
							+ editName + "' And Last_Name = '" + editLastName
							+ "' And Father_Name = '" + editFatherName
							+ "' And dataYearid = " + dataYearId);

			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editCustomerGiveing(JTable customer) {
		// TODO Auto-generated method stub
		int selectedrow = customer.getSelectedRow();
		String editName = customer.getValueAt(selectedrow, 6).toString();
		String editLastName = customer.getValueAt(selectedrow, 5).toString();
		String editFatherName = customer.getValueAt(selectedrow, 4).toString();
		String name = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("Update  customer_giving_account set Name = '"
							+ name
							+ "',Last_Name = '"
							+ lastname
							+ "' where Name = '"
							+ editName
							+ "' And Last_Name = '"
							+ editLastName
							+ "' And dataYearid = " + dataYearId);

			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editCustomerShopping(JTable customer) {
		// TODO Auto-generated method stub

		int selectedrow = customer.getSelectedRow();
		String editName = customer.getValueAt(selectedrow, 6).toString();
		String editLastName = customer.getValueAt(selectedrow, 5).toString();
		String editFatherName = customer.getValueAt(selectedrow, 4).toString();
		String editCustomerName = editName + " " + editLastName;
		String name = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();
		String customerName = name + " " + lastname;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("Update  customer_shopping_account set Customer_Name = '"
							+ customerName
							+ "' where Customer_Name = '"
							+ editCustomerName
							+ "' And dataYearid = "
							+ dataYearId);

			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void update(JTable Customerlisttable) {
		// TODO Auto-generated method stub

		String name;
		String lastName;
		String fatherName;
		String address;
		float shopping;
		float payment;
		float companyDemand;

		((DefaultTableModel) Customerlisttable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where dataYearid = "
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				lastName = result.getString(2);
				fatherName = result.getString(3);
				address = result.getString(4);
				shopping = result.getFloat(5);
				payment = result.getFloat(6);
				companyDemand = result.getFloat(7);

				((DefaultTableModel) Customerlisttable.getModel())
						.addRow(new Object[] {

						companyDemand, payment, shopping, address, fatherName,
								lastName, name,
								Customerlisttable.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public EditCustomer(JTable table, JDialog dialog) {
		super();
		this.table = table;
		this.dialog = dialog;
	}
}
