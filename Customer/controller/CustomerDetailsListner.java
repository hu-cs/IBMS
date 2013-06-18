package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connection.DBConnection;
import view.CustomerDetails;

public class CustomerDetailsListner implements ActionListener {

	JTable table;
	int dataYearId = DataYearId.getDataYearId();
	
	public CustomerDetailsListner(JTable table) {
		super();
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void load(JTable shoppingtable) {
		// TODO Auto-generated method stub
		
		int SelectedRow = table.getSelectedRow();
		String name = table.getValueAt(SelectedRow,6).toString();
		int InvoiceNumber;
		float InvoiceCost;
		String DateAndTime;
		
		

		((DefaultTableModel) shoppingtable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Invoice_Numebr,Invoice_Cost,Date_And_Time from customer_shopping_account WHERE Customer_Name = '"+name+"' and datayearid = "+dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				InvoiceNumber = result.getInt(1);
				InvoiceCost = result.getFloat(2);
				DateAndTime = result.getString(3);
				

				((DefaultTableModel) shoppingtable.getModel())
						.addRow(new Object[] {

						 DateAndTime, InvoiceCost,InvoiceNumber,shoppingtable.getRowCount() + 1 });
								

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private void loadrecive(JTable paymenttable) {
		// TODO Auto-generated method stub
		
		int SelectedRow = table.getSelectedRow();
		String name = table.getValueAt(SelectedRow,6).toString();
		
		float Cost;
		String DateAndTime;
		
		

		((DefaultTableModel) paymenttable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account WHERE Name = '"+name+"' and datayearid = "+dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				Cost = result.getFloat(1);
				DateAndTime = result.getString(2);
				
				

				((DefaultTableModel) paymenttable.getModel())
						.addRow(new Object[] {

								DateAndTime,Cost,paymenttable.getRowCount() + 1 });
								

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	}
}