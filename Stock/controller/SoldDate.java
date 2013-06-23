package controller;

import view.StockGui;
import view.soldDate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import connection.DBConnection;

public class SoldDate implements ActionListener {
	JTable table;

	public SoldDate(JTable table) {
		super();
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		int selectedrow = table.getSelectedRow();
		if (selectedrow == -1)
			JOptionPane.showMessageDialog(null, StockGui.Selection,
					StockGui.Information, JOptionPane.INFORMATION_MESSAGE);
		else
			new soldDate();

	}

}
