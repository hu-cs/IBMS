package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.portable.CustomValue;

import DataBase.DBConnection;
import view.CustomerDetails;
import view.CustomersList;
import view.EditeCustomerPyament;

public class EditeCustomerReciver implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (CustomerDetails.giveing.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, CustomersList.PleaseSelectOneRow ,
					CustomersList.Information, JOptionPane.INFORMATION_MESSAGE);

		} else
			new EditeCustomerPyament();

	}


}
