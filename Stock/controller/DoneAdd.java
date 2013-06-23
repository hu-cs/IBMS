package controller;

import connection.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.StockGui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.IllegalFormatException;

public class DoneAdd implements ActionListener {

	JDialog dialog;
	JTable storingTable;
	int datayearid = DataYearId.getDataYearId();

	public DoneAdd(JTable table, JDialog dialog) {
		super();
		this.dialog = dialog;
		this.storingTable = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (validator() != -1) {
			store(StockGui.table);
			Update(StockGui.table);
			insertToStoredate(StockGui.table);
			dialog.dispose();
		}

	}

	private int validator() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		try {
			int newQty = Integer.parseInt(storingTable.getValueAt(0, 1)
					.toString());
			String dateAndTime = storingTable.getValueAt(0, 0).toString();
		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					DoneEdit.NullPointerException, StockGui.Information,
					JOptionPane.INFORMATION_MESSAGE);
			return -1;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, DoneEdit.NumberFormatException,
					StockGui.Information, JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}
		return 0;

	}

	private void store(JTable stockTable) {

		int selectedrow = stockTable.getSelectedRow();
		int newQty = Integer.parseInt(storingTable.getValueAt(0, 1).toString());
		int lastQty = Integer.parseInt(stockTable.getValueAt(selectedrow, 1)
				.toString());
		int totalQty = newQty + lastQty;

		String name = stockTable.getValueAt(selectedrow, 2).toString();
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("UPDATE  stock SET  Quantity=" + totalQty
							+ " WHERE Name='" + name + "' And datayearid = "
							+ datayearid + "");
			statement.execute();

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void Update(JTable stockTable) {

		String name;
		int quantity;
		String unit;
		// StockMaterial loadedMaterial;

		((DefaultTableModel) stockTable.getModel()).setRowCount(0);

		PreparedStatement statement;
		try {
			statement = DBConnection.connection
					.prepareStatement("SELECT Name, Quantity, Unit FROM  stock Where datayearid = "
							+ datayearid + "");
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				quantity = result.getInt(2);
				unit = result.getString(3);

				((DefaultTableModel) stockTable.getModel())
						.addRow(new Object[] { unit, quantity, name,
								stockTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void insertToStoredate(JTable stocktable) {

		String name = storingTable.getValueAt(0, 2).toString();
		int quantity = Integer.parseInt(storingTable.getValueAt(0, 1)
				.toString());
		String dateAndTime = storingTable.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("INSERT INTO store_history(Name, Quantity,Date_And_Time, datayearid) VALUES ('"
							+ name
							+ "', '"
							+ quantity
							+ "', '"
							+ dateAndTime
							+ "'," + datayearid + ")");
			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
