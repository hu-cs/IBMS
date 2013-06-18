package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.CustomersList;
import connection.DBConnection;

public class DeleteCustomer implements ActionListener {

	JTable delettable;
	int dataYearId = DataYearId.getDataYearId();

	public DeleteCustomer(JTable delettable) {
		super();
		this.delettable = delettable;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (delete() != false) {

			update(CustomersList.table);
			CustomersList.setTotalCompanyDemand();
			CustomersList.setTotalResive();
			CustomersList.setTotalShopping();
		}
		// delete();\
	}

	private boolean delete() {
		// TODO Auto-generated method stub

		int selectedrow = delettable.getSelectedRow();
		if (selectedrow != -1) {

			int ask = new JOptionPane()
					.showConfirmDialog(
							null,
							CustomersList.IfyouDeleteCustomerFromListItwillBeDeleteAllInformation,
							CustomersList.Alert, JOptionPane.YES_NO_OPTION,
							JOptionPane.YES_OPTION);

			if (ask == JOptionPane.YES_OPTION) {

				String name = delettable.getValueAt(selectedrow, 6).toString();
				String lastName = delettable.getValueAt(selectedrow, 5)
						.toString();

				try {

					PreparedStatement statement = DBConnection.connection
							.prepareStatement("DELETE FROM customer_list WHERE Name='"
									+ name
									+ "' AND Last_Name = '"
									+ lastName
									+ "' and datayearid = " + dataYearId);

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
					.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where datayearid ="
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
}
