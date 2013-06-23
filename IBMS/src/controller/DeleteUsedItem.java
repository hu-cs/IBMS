package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.MainUi;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class DeleteUsedItem implements ActionListener {

	JTable table;
	JComboBox<String> typeComboBox;
	int dataYearId = DataYearId.getDataYearId();
	float invoiceCost;
	float lastConsumption;
	float lastDept;
	float lastCash;
	boolean isCash;
	String dbTable;

	public DeleteUsedItem(JTable table, JComboBox<String> typeComboBox,
			String dbTable) {
		this.table = table;
		this.typeComboBox = typeComboBox;
		this.dbTable = dbTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (table.getSelectedRow() != -1) {
			invoiceCost = Float.parseFloat(leadData().get(0).toString());
			lastConsumption = Float.parseFloat(leadData().get(3).toString());
			lastDept = Float.parseFloat(leadData().get(2).toString());
			lastCash = Float.parseFloat(leadData().get(1).toString());
			isCash = Boolean.parseBoolean(leadData().get(4).toString());
		}

		if (delete() != -1) {
			editCashBox();
			update();
			setCombobox(typeComboBox);
			updateMainUiLabels(MainUi.consumptionValueLbl,
					MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
					MainUi.debtValesLbl);
		}

	}

	private int delete() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		int selecteRow = table.getSelectedRow();

		if (selecteRow != -1) {

			int ask = JOptionPane.showConfirmDialog(null,
					"<html><body><p style =\"font-size:17\""
							+ ">آیا مطمدن هسنید؟</p></body></html>", "هشدار",
					JOptionPane.YES_NO_OPTION, JOptionPane.YES_OPTION, null);

			if (ask == JOptionPane.YES_OPTION) {
				String title = table.getValueAt(selecteRow, 3).toString();
				String invoiceNumber = table.getValueAt(selecteRow, 2)
						.toString();
				float invoiceCost = Float.parseFloat(table.getValueAt(
						selecteRow, 1).toString());
				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete from " + dbTable
									+ " where title='" + title
									+ "' and invoice_Number ='" + invoiceNumber
									+ "' and Invoice_Cost =" + invoiceCost
									+ " and datayearid = " + dataYearId);
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			} else {
				return -1;
			}

		} else {
			new JOptionPane().showMessageDialog(null,
					"برای پاک کردن، لطفا یک ردیف را انتخاب کنید!");
			return -1;
		}

		return 0;
	}

	private void update() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `"
							+ dbTable
							+ "` WHERE Invoice_Cost>0 and datayearid ="
							+ dataYearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getString(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						dateAndTime, invoiceCost, invoiceNumber, selectedTitle,
						table.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setCombobox(JComboBox<String> typeCombo) {
		typeCombo.setSelectedIndex(0);

	}

	private void editCashBox() {

		// float invoiceCost = Float.parseFloat(leadData().get(0).toString());
		// float lastConsumption =
		// Float.parseFloat(leadData().get(3).toString());
		// float lastDept = Float.parseFloat(leadData().get(2).toString());
		// float lastCash = Float.parseFloat(leadData().get(1).toString());
		// boolean isCash = Boolean.parseBoolean(leadData().get(4).toString());
		// System.out.println(isCash);

		lastConsumption -= invoiceCost;

		if (isCash) {

			System.err.println("first if");

			lastCash += invoiceCost;

			try {
				java.sql.PreparedStatement statement = DBConnection.connection
						.prepareStatement("update cash_box set consumption ="
								+ lastConsumption + ", current_mony ="
								+ lastCash + " where datayearid =" + dataYearId);

				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (!isCash) {

			System.err.println("second if");
			lastDept -= invoiceCost;
			try {
				java.sql.PreparedStatement statement = DBConnection.connection
						.prepareStatement("update cash_box set consumption ="
								+ lastConsumption + ", CompanyDept=" + lastDept
								+ " where datayearid =" + dataYearId);
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public ArrayList<Object> leadData() {

		ArrayList<Object> values = new ArrayList<Object>();

		int selecteRow = table.getSelectedRow();
		String title = table.getValueAt(selecteRow, 3).toString();
		String invoiceNumber = table.getValueAt(selecteRow, 2).toString();
		float invoiceCost = Float.parseFloat(table.getValueAt(selecteRow, 1)
				.toString());

		float lastConsumption = 0;
		float lastDept = 0;
		float lastCash = 0;
		boolean isCash = false;

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT iscash FROM `" + dbTable
							+ "` WHERE Invoice_Number = '" + invoiceNumber
							+ "' and Title = '" + title + "' and datayearid ="
							+ dataYearId);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				isCash = result.getBoolean(1);
				// System.out.println(isCash);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT consumption,current_mony,companyDept FROM `cash_box` WHERE dataYearId ="
							+ dataYearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				lastConsumption = result.getFloat(1);
				lastCash = result.getFloat(2);
				lastDept = result.getFloat(3);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		values.add(invoiceCost);
		values.add(lastCash);
		values.add(lastDept);
		values.add(lastConsumption);
		values.add(isCash);

		return values;
	}

	private void updateMainUiLabels(JLabel consumptionValueLbl,
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

}
