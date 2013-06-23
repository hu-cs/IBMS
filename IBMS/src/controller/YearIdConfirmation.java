package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.MainUi;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class YearIdConfirmation implements ActionListener {

	JTable yearIdTable;
	JDialog dialog;

	public YearIdConfirmation(JTable yearIdTable, JDialog dialog) {
		this.yearIdTable = yearIdTable;
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (yearUpdate() != -1)
			loadValues(MainUi.table);
		setLablesValues(MainUi.consumptionValueLbl, MainUi.saleValuesLbl,
				MainUi.currentMonyValueLbl, MainUi.debtValesLbl);
		dialog.dispose();

	}

	private int yearUpdate() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		int yearId = 0;

		for (int counter = 0; counter < yearIdTable.getRowCount(); counter++) {
			if (Boolean.parseBoolean(yearIdTable.getValueAt(counter, 0)
					.toString())) {

				yearId = Integer.parseInt(yearIdTable.getValueAt(counter, 1)
						.toString());
				break;
			}
		}

		if (yearId == 0) {
			new JOptionPane()
					.showMessageDialog(null,
							"اطفا برای ادامه دادن در انجام حسابات، یک سال مالی زا انتخاب کنید!");
			return -1;
		}

		try {
			PreparedStatement updateQuery = (PreparedStatement) DBConnection.connection
					.prepareStatement("update data_year set is_selected=0 where is_selected =" + 1);

			updateQuery.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update data_year set is_selected=1 where year_id = "
							+ yearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	private void setLablesValues(JLabel consumptionValueLbl,
			JLabel saleValuesLbl, JLabel currentMonyValueLbl,
			JLabel debtValesLbl) {

		float consumption = 0;
		float sale = 0;
		float currentMony = 0;
		float dept = 0;
		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT consumption,sale,current_mony,companyDept FROM `cash_box` WHERE datayearId ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				consumption = result.getFloat(1);
				sale = result.getFloat(2);
				currentMony = result.getFloat(3);
				dept = result.getFloat(4);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consumptionValueLbl.setText(consumption + "");
		saleValuesLbl.setText(sale + "");
		currentMonyValueLbl.setText(currentMony + "");
		debtValesLbl.setText(dept + "");

	}

	private void loadValues(JTable mainTable) {
		((DefaultTableModel) mainTable.getModel()).setRowCount(0);

		String name = "";
		String unit = "";
		int qty = 0;
		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT name,quantity,unit FROM `stock` WHERE datayearid ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				qty = result.getInt(2);
				unit = result.getString(3);

				((DefaultTableModel) mainTable.getModel()).addRow(new Object[] {
						unit, qty, name, mainTable.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
