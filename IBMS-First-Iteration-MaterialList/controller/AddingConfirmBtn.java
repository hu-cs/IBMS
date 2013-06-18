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

import view.AddingNewMaterial;
import view.MaterialList;

import model.Material;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import connection.DBConnection;

public class AddingConfirmBtn implements ActionListener {

	JTable table;
	JDialog dialog;
	int datayearid = DataYearId.getDataYearId();

	public AddingConfirmBtn(JTable table, JDialog dialog) {
		this.table = table;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (validation() != -1) {

			if (save() != -1)
				update(MaterialList.table);
			dialog.dispose();
			insertToStock();
		}
	}

	private int save() {

		String name = table.getValueAt(0, 3).toString();
		String unit = table.getValueAt(0, 2).toString();
		float finishedCost = Float
				.parseFloat(table.getValueAt(0, 1).toString());
		float sellCost = Float.parseFloat(table.getValueAt(0, 0).toString());

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into materiallist (Name,Unit,FinishedCost,SellCost,DataYearId)values('"
							+ name
							+ "','"
							+ unit
							+ "',"
							+ finishedCost
							+ ","
							+ sellCost + "," + datayearid + ")");
			statement.execute();
		} catch (MySQLIntegrityConstraintViolationException e) {
			// new JOptionPane().showMessageDialog(0, " Please Reenter Your ")
			return -1;
		}
		catch (SQLException e) {
			
			UIManager.put("OptionPane.okButtonText", "تایید");
//			new JOptionPane().showMessageDialog(null, "نام محصول قبلآ در سیستم ثبت شده است !");
			JOptionPane.showMessageDialog(null,"نام محصول قبلآ در سیستم ثبت شده است !",  " هشدار  ", JOptionPane.OK_OPTION);
		}
		
		return 0;

	}

	public void update(JTable materiaList) {
		((DefaultTableModel) materiaList.getModel()).setRowCount(0);

		String name;
		String unit;
		float finishedCost;
		float sellCost;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Name, Unit, FinishedCost, SellCost from materiallist where DataYearId="
							+ datayearid);

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

	public void insertToStock() {
		String name = table.getValueAt(0, 3).toString();
		int quantity = 0;
		String unit = table.getValueAt(0, 2).toString();

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("INSERT INTO stock (Name,Quantity,Unit,datayearid) Value ('"
							+ name
							+ "','"
							+ quantity
							+ "','"
							+ unit
							+ "',"
							+ datayearid + ")");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	int validation() {

		String name;
		String unit;
		float finishedCost;
		float sellCost;

		try {

			name = table.getValueAt(0, 3).toString();
			unit = table.getValueAt(0, 2).toString();
			finishedCost = Float.parseFloat(table.getValueAt(0, 1).toString());
			sellCost = Float.parseFloat(table.getValueAt(0, 0).toString());

			if (name.trim().isEmpty() || unit.trim().isEmpty()) {
				new JOptionPane().showMessageDialog(null,
						"please insert a value");
				return -1;

			}

		} catch (NullPointerException e) {

			new JOptionPane().showMessageDialog(null,
					"Please insert correct values");

			return -1;
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null, "please insert a number");
			return -1;
		}

		return 0;
	}

}
