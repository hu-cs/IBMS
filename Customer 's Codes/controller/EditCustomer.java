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
	String preName;
	String preLastName;

	public EditCustomer(JTable table, JDialog dialog, 
			String preLastName,String preName) {
		super();
		this.table = table;
		this.dialog = dialog;
		this.preLastName = preLastName;
		this.preName = preName;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (validator() != -1) {
			editCustomerList(CustomersList.table);
			editCustomerGiveing(CustomersList.table);
			editCustomerShopping(CustomersList.table);
			update(CustomersList.table);
			changeSellHistory(CustomersList.table);// TODO pass the customer
													// prevoius name
			changeInvoiceList(CustomersList.table);
			changeInvoiceMaterail(CustomersList.table);
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
		String editCustomerName = customer.getValueAt(selectedrow, 6)
				.toString();
		String editLastName = customer.getValueAt(selectedrow, 5).toString();
		String editFatherName = customer.getValueAt(selectedrow, 4).toString();
		String editName = editCustomerName + " " + editLastName;

		String customerName = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();

		String name = customerName + " " + lastname;
		System.out.println("edit name " + editName);
		System.out.println("name " + name);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("Update  customer_giving_account set Name = '"
							+ name
							+ "' where Name = '"
							+ editName
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

	/**
	 * Changes the edited customer name in the sell history for loading them in
	 * the system
	 * 
	 * @param customer
	 */
	private void changeSellHistory(JTable customer) {
		int selectedrow = customer.getSelectedRow();

		String editCustomerName = this.preName;
		String editLastName = preLastName;
		String editName = editCustomerName + " " + editLastName;

		String customerName = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();

		String name = customerName + " " + lastname;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update sell_history set buyer = '"
							+ name + "' where buyer = '" + editName
							+ "' and datayearid = " + dataYearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Changes the edited customer name in the invoice list for loading them in
	 * the system
	 * 
	 * @param customer
	 */
	private void changeInvoiceList(JTable customer) {

		int selectedrow = customer.getSelectedRow();

		String editCustomerName = this.preName;
		String editLastName = preLastName;
		String editName = editCustomerName + " " + editLastName;

		String customerName = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();

		String name = customerName + " " + lastname;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update invoice_list set customer ='"
							+ name + "' where customer = '" + editName
							+ "' and dataYearid =" + dataYearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Changes the edited customer name in the invoice list for loading them in
	 * the system
	 * 
	 * @param customer
	 */
	private void changeInvoiceMaterail(JTable customer) {

		int selectedrow = customer.getSelectedRow();

		String editCustomerName = this.preName;
		String editLastName = preLastName;
		String editName = editCustomerName + " " + editLastName;

		String customerName = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();

		String name = customerName + " " + lastname;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update invoice_materials set customer = '"
							+ name
							+ "' where customer = '"
							+ editName
							+ "' and data_year_id =" + dataYearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
