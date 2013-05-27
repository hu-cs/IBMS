package controler.expenses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

import newViewes.CompanyUsed;

public class AddUsedItem implements ActionListener {

	JTable table;
	JComboBox<String> typeCombo;
	JComboBox<String> editorComboBox;
	JDialog dialog;

	public AddUsedItem(JTable table, JDialog dialog) {
		this.table = table;
		this.typeCombo = CompanyUsed.selectTypeCombo;
		this.dialog = dialog;
		editorComboBox = new JComboBox<String>();
		for (String type : CompanyUsed.typeList) {
			editorComboBox.addItem(type);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (save() != -1) {
			update(CompanyUsed.table);
			dialog.dispose();
			setCombobox(typeCombo);
		}

	}

	private int save() {
		int invoiceNum = 0;
		float invoiceCost = 0;
		try {
			invoiceNum = Integer.parseInt(table.getValueAt(0, 2).toString());
			invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());
		} catch (NumberFormatException e) {

		}
		String title = typeCombo.getSelectedItem().toString();

		String date = table.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into corruptionconsumption (Title,Invoice_Number,Invoice_Cost,Date_and_time)values('"
							+ title
							+ "',"
							+ invoiceNum
							+ ","
							+ invoiceCost
							+ ",'" + date + "')");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	private void update(JTable mainTble) {
		((DefaultTableModel) mainTble.getModel()).setRowCount(0);

		int invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `corruptionconsumption` WHERE Invoice_Number>0");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getInt(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);

				((DefaultTableModel) mainTble.getModel()).addRow(new Object[] {
						dateAndTime, invoiceCost, invoiceNumber, selectedTitle,
						mainTble.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setCombobox(JComboBox<String> typeCombo) {
		typeCombo.setSelectedIndex(0);

	}

}
