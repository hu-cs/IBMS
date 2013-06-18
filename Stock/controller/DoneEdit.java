package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.Validator;

import view.EditGui;
import view.StockGui;
import view.StoreDate;
import view.soldDate;
import view.storingToStock;
import connection.DBConnection;
import model.StockMaterial;

public class DoneEdit implements ActionListener {

	JDialog dialog;
	JTable editTable;
	// String name;
	int totalQty;
	public static String NumberFormatException = StockGui.bundle
			.getString("NumberFormatException");
	public static String NullPointerException = StockGui.bundle
			.getString("NullPointerException");

	public DoneEdit(JTable table, JDialog dialog) {
		super();
		this.dialog = dialog;
		this.editTable = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (validator() != -1) {

			subtraction(StoreDate.storeTable);
			// stockSubtraction(StockGui.table);
			update(StoreDate.storeTable);
			setTotal(StoreDate.storeTable);
			dialog.dispose();
		}

	}

	private int validator() {
		
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		try {
			int newQty = Integer
					.parseInt(editTable.getValueAt(0, 1).toString());
			String newDate = editTable.getValueAt(0, 0).toString();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, NumberFormatException,
					StockGui.Information, JOptionPane.INFORMATION_MESSAGE);
			return -1;
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, NullPointerException,
					StockGui.Information, JOptionPane.INFORMATION_MESSAGE);
			return -1;
		}

		return 0;
	}

	int datayearid = DataYearId.getDataYearId();

	private void subtraction(JTable storeTable) {

		int selectedrow = storeTable.getSelectedRow();
		int lastQty = Integer.parseInt(storeTable.getValueAt(selectedrow, 1)
				.toString());
		int newQty = Integer.parseInt(editTable.getValueAt(0, 1).toString());
		String newDate = editTable.getValueAt(0, 0).toString();

		totalQty = lastQty - newQty;

		String dateAndTime = storeTable.getValueAt(selectedrow, 0).toString();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("UPDATE store_history set Quantity = "
							+ newQty + ",Date_And_Time = '" + newDate
							+ "' where Quantity = " + lastQty
							+ " And Date_And_Time = '" + dateAndTime
							+ "'and datayearid = " + datayearid + " ");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// private void stockSubtraction(JTable stock) {
	//
	// int selectedRow = stock.getSelectedRow();
	// // int newQty = Integer.parseInt(table.getValueAt(0, 0).toString());
	//
	// int lastQty = Integer.parseInt(stock.getValueAt(selectedRow, 1)
	// .toString());
	// int totalQty = lastQty - this.totalQty;
	// name = stock.getValueAt(selectedRow, 2).toString();
	//
	// try {
	// PreparedStatement statement = DBConnection.connection
	// .prepareStatement("UPDATE  stock SET  Quantity=" + totalQty
	// + " WHERE Name='" + name + "'");
	// statement.execute();
	//
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void update(JTable storeTable) {

		int selectedRow = StockGui.table.getSelectedRow();
		String dateAndTime;
		int quantity;
		String name = StockGui.table.getValueAt(selectedRow, 2).toString();

		((DefaultTableModel) storeTable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT  Quantity, Date_And_Time FROM  store_history Where Name = '"
							+ name + "' and datayearid = " + datayearid + " ");
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				quantity = result.getInt(1);
				dateAndTime = result.getString(2);

				((DefaultTableModel) storeTable.getModel())
						.addRow(new Object[] { dateAndTime, quantity,
								storeTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setTotal(JTable storeDate) {

		int total = 0;

		for (int counter = 0; counter < storeDate.getRowCount(); counter++) {

			total += Integer.parseInt(storeDate.getValueAt(counter, 1)
					.toString());

		}
		String totalStore = total + "";
		StoreDate.label_1.setText(totalStore);

	}

}
