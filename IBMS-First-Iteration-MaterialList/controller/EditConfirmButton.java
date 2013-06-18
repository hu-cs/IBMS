package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.MaterialList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import connection.DBConnection;

public class EditConfirmButton implements ActionListener {

	JTable table;
	JDialog dialog;
	int selectedRow;
	String name;
	int dataYearId = DataYearId.getDataYearId();

	public EditConfirmButton(JTable table, JDialog dialog, String name) {
		super();
		this.table = table;
		this.dialog = dialog;
		this.name = name;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		if (validator() != -1) {
			edit(MaterialList.table);
			update(MaterialList.table);
			insertToStock(MaterialList.table);
			dialog.dispose();
			
			UIManager.put("OptionPane.okButtonText", "تایید");
			JOptionPane.showMessageDialog(null,"انجام شد !                  ",  " هشدار  ", JOptionPane.INFORMATION_MESSAGE);
//			new JOptionPane().showMessageDialog(null, "Done!");
		}

	}

	public void insertToStock(JTable materialTable) {
		String name = table.getValueAt(0, 0).toString();
		int quantity = 0;
		String unit = table.getValueAt(0, 1).toString();

		selectedRow = materialTable.getSelectedRow();
		String materialName = this.name;// materialTable.getValueAt(selectedRow,
										// 3)
		// .toString();
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("UPDATE stock SET Name='" + name
							+ "', Quantity=" + quantity + ", Unit ='" + unit
							+ "' WHERE Name='" + materialName
							+ "' and datayearid =" + dataYearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int edit(JTable materialTable) {

		// System.err.println("edit is done");

		String name = table.getValueAt(0, 0).toString();
		String unit = table.getValueAt(0, 1).toString();
		float finishedCost = Float
				.parseFloat(table.getValueAt(0, 2).toString());
		float sellCost = Float.parseFloat(table.getValueAt(0, 3).toString());

		selectedRow = materialTable.getSelectedRow();
		String materialName = this.name;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("UPDATE materiallist SET Name='" + name
							+ "',Unit='" + unit + "', FinishedCost="
							+ finishedCost + ", SellCost=" + sellCost
							+ " WHERE Name='" + materialName
							+ "' and datayearid =" + dataYearId);
			statement.execute();
		} catch (MySQLIntegrityConstraintViolationException e) {
			// new JOptionPane().showMessageDialog(0, " Please Reenter Your ")
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public void update(JTable materiaList) {

		// System.err.println("edit is done");

		((DefaultTableModel) materiaList.getModel()).setRowCount(0);

		String name;
		String unit;
		float finishedCost;
		float sellCost;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Name, Unit, FinishedCost, SellCost from materiallist where DataYearId="
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				finishedCost = result.getFloat(3);
				sellCost = result.getFloat(4);

				// Materials newMaterial = new Materials();
				//
				// Materials materialObject = new Materials(name, unit,
				// finishedCost, sellCost);
				// Materials.materialList.add(materialObject);

				((DefaultTableModel) materiaList.getModel())
						.addRow(new Object[] { sellCost, finishedCost, unit,
								name, materiaList.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int validator() {

		String name;
		String unit;
		Float FinishedCost;
		Float sellCost;

		try {

			name = table.getValueAt(0, 0).toString();
			unit = table.getValueAt(0, 1).toString();
			FinishedCost = Float.parseFloat(table.getValueAt(0, 2).toString());
			sellCost = Float.parseFloat(table.getValueAt(0, 3).toString());

			if (name.trim().isEmpty() || unit.trim().isEmpty()) {
				new JOptionPane().showMessageDialog(null, "Please Edit a Row");
				return -1;

			}

		} catch (NullPointerException e) {

			new JOptionPane().showMessageDialog(null,
					"Please Enter Complete value");

			return -1;
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null, "Please Enter a Number");
			return -1;
		}

		return 0;
	}
}
