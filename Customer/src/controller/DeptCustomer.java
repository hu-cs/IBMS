package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.CustomersList;
import DataBase.DBConnection;

public class DeptCustomer implements ActionListener {

	
	int dataYearId = DataYearId.getDataYearId();
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		load(CustomersList.table);
		CustomersList.setTotalCompanyDemand();
		CustomersList.setTotalResive();
		CustomersList.setTotalShopping();
		
	}

	private void load(JTable CustomerTable) {
		// TODO Auto-generated method stub

		String name;
		String lastName;
		String fatherName;
		String address;
		float shopping;
		float payment;
		float companyDemand;

		((DefaultTableModel) CustomerTable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT `Name`,`Last_Name`,`Father_Name`,`Address`,`Shopping_Account`,`Giving_Money_Account`,`Company_demand` FROM customer_list where `Check` = "
							+ 0 + " And datayearid = "+dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				lastName = result.getString(2);
				fatherName = result.getString(3);
				address = result.getString(4);
				shopping = result.getFloat(5);
				payment = result.getFloat(6);
				companyDemand = result.getFloat(7);

				((DefaultTableModel) CustomerTable.getModel())
						.addRow(new Object[] {

						companyDemand, payment, shopping, address, fatherName,
								lastName, name, CustomerTable.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
