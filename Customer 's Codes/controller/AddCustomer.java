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
import view.CustomersList;

public class AddCustomer implements ActionListener {

	JTable table;
	JDialog dialog;
	int dataYearId = DataYearId.getDataYearId();

	public AddCustomer(JTable table, JDialog dialog) {
		super();
		this.table = table;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (validator() != -1) {
			insert();
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
			
			if (name.trim().isEmpty()|| lastname.trim().isEmpty()||fathername.trim().isEmpty() || address.trim().isEmpty()){
				JOptionPane.showMessageDialog(null,
						CustomersList.PleaseInsertCorrectInformation, CustomersList.Information,
						JOptionPane.INFORMATION_MESSAGE);
			return -1;	
			}
			
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,
					CustomersList.PleaseEnterAllInformation, CustomersList.Information,
					JOptionPane.INFORMATION_MESSAGE);
			return -1;

		}
		return 0;
	}

	private void insert() {
		// TODO Auto-generated method stub

		String name = table.getValueAt(0, 3).toString();
		String lastname = table.getValueAt(0, 2).toString();
		String fathername = table.getValueAt(0, 1).toString();
		String address = table.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("INSERT INTO customer_list  (Name,Last_Name,Father_Name,Address, datayearid) VALUES ('"
							+ name
							+ "','"
							+ lastname
							+ "','"
							+ fathername
							+ "','" + address + "'," + dataYearId + ")");

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
					.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where datayearid ="+dataYearId);

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
}
