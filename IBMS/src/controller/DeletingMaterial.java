package controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executor;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.MainUi;
import view.MaterialList;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;

public class DeletingMaterial implements ActionListener {

	JTable table;
	String name;
	int dataYearId = DataYearId.getDataYearId();

	public DeletingMaterial(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (deletingMaterial() != -1) {
			update(MaterialList.table);
			deletingFromStock();
			updateMainUiTable(MainUi.table);
		}

		// if ( deletingFromStock() != -1) {
		// update();
	}

	private void update(JTable materialList) {
		((DefaultTableModel) materialList.getModel()).setRowCount(0);
		String name;
		String unit;
		float finishedCost;
		float sellCost;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT NAME,UNIT,FINISHEDCOST,SELLCOST from materialList where DataYearId="
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				finishedCost = result.getFloat(3);
				sellCost = result.getFloat(4);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						sellCost, finishedCost, unit, name,
						materialList.getRowCount() + 1 });

				// name,unit,finishedCost,sellCost,
				// materialList.getRowCount() +1

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	int deletingMaterial() {

		int selctedRow = table.getSelectedRow();
		if (selctedRow != -1) {
			name = table.getValueAt(selctedRow, 3).toString();

			UIManager.put("OptionPane.yesButtonText", "بله");
			UIManager.put("OptionPane.noButtonText", "نخیر");

			int ask = JOptionPane
					.showConfirmDialog(
							null,
							"در صورت پاک کردن،جنس مذکور از لیست اجناس و انبار پاک خواهد گردید، آیا هنوز مطمون هستید؟",
							"هشدار", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null);

			if (ask == JOptionPane.YES_OPTION) {
				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete from materiallist where name = '"
									+ name + "' and datayearid =" + dataYearId);

					statement.execute();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} else {

			UIManager.put("OptionPane.okButtonText", "تایید");
			JOptionPane
					.showMessageDialog(
							null,
							"جهت پاک نمودن نام محصول لطفـــــــــآ یک ردیف را انتخاب نمایید ! ",
							" هشدار  ", JOptionPane.OK_OPTION);
			// new JOptionPane().showMessageDialog(null,
			// "Please select a row!");
			return -1;
		}
		return 0;
	}

	void deletingFromStock() {
		int selctedRow = table.getSelectedRow();

		// String name = table.getValueAt(selctedRow, 3).toString();

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("delete from stock where name = '" + name
							+ "' and datayearid = " + dataYearId);

			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateMainUiTable(JTable mainTable) {
		((DefaultTableModel) mainTable.getModel()).setRowCount(0);
		String name;
		int quantity;
		String unit;

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name, Quantity, Unit FROM  stock");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				quantity = result.getInt(2);
				unit = result.getString(3);
				((DefaultTableModel) mainTable.getModel()).addRow(new Object[] {
						unit, quantity, name, mainTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
