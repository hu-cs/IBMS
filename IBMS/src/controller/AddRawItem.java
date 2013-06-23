package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import view.MainUi;
import view.RawMaterial;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class AddRawItem implements ActionListener {

	JTable table;
	JComboBox<String> typeCombo;
	JComboBox<String> editorComboBox;
	JDialog dialog;
	float totalCost;
	int dataYearId = DataYearId.getDataYearId();

	public AddRawItem(JTable table, JDialog dialog) {

		this.table = table;
		this.typeCombo = RawMaterial.selectTypeCombo;
		this.dialog = dialog;
		editorComboBox = new JComboBox<String>();
		for (String type : RawMaterial.typeList) {
			editorComboBox.addItem(type);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (validator() != -1) {
			if (cashBoxValidator() != -1) {
				save();
				update(RawMaterial.table);
				dialog.dispose();
				setCombobox(typeCombo);
				updateStockLabels(MainUi.consumptionValueLbl,
						MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
						MainUi.debtValesLbl);

			}
		}

	}

	private boolean save() {

		String title = typeCombo.getSelectedItem().toString();
		String date = table.getValueAt(0, 0).toString();
		String invoiceNumber = table.getValueAt(0, 4).toString();
		String unit = table.getValueAt(0, 3).toString();
		float costPerUnit = Float.parseFloat(table.getValueAt(0, 2).toString());
		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		float totalCost = qty * costPerUnit;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into raw_material (title,invoice_number,quantity,unit,cost,Invoice_Cost,DateAndTime,dataYearId)values('"
							+ title
							+ "','"
							+ invoiceNumber
							+ "',"
							+ qty
							+ ",'"
							+ unit
							+ "',"
							+ costPerUnit
							+ ","
							+ totalCost
							+ ",'" + date + "'," + dataYearId + ")");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}

	private void update(JTable mainTble) {

		((DefaultTableModel) mainTble.getModel()).setRowCount(0);

		String invoiceNumber;
		float totalCost;
		String dateAndTime;
		String selectedTitle;
		float cost;
		float qty;
		String unit;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select title,invoice_number,quantity,unit,cost,Invoice_Cost,dateAndTime from raw_material where Invoice_Cost>0 and datayearid="
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getString(2);
				qty = result.getFloat(3);
				unit = result.getString(4);
				cost = result.getFloat(5);
				totalCost = result.getFloat(6);
				dateAndTime = result.getString(7);

				System.out.println(invoiceNumber);

				((DefaultTableModel) mainTble.getModel()).addRow(new Object[] {
						dateAndTime, totalCost, qty, cost, unit, invoiceNumber,
						selectedTitle, mainTble.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setCombobox(JComboBox<String> typeCombo) {
		typeCombo.setSelectedIndex(0);

	}

	private int validator() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		String invoiceNumber;
		String date;
		int dataYearId = DataYearId.getDataYearId();
		String title;
		String unit;
		float costPerUnit;
		float qty;
		try {

			title = typeCombo.getSelectedItem().toString();
			date = table.getValueAt(0, 0).toString();
			invoiceNumber = table.getValueAt(0, 4).toString();
			unit = table.getValueAt(0, 3).toString();
			costPerUnit = Float.parseFloat(table.getValueAt(0, 2).toString());
			qty = Float.parseFloat(table.getValueAt(0, 1).toString());

		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"لطفا اطلاعات را در جدول فوق وارد کنید!");
			return -1;
		} catch (NumberFormatException e) {
			System.err.println("it is failed");
			new JOptionPane().showMessageDialog(null,
					"لطفا فی واحد را درست وارد کنید!");
			return -1;
		}

		return 0;

	}

	/**
	 * validates if does the cash box has enough money
	 * 
	 * @return
	 */
	// new method
	private int cashBoxValidator() {
		float costPerUnit = Float.parseFloat(table.getValueAt(0, 2).toString());
		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		totalCost = qty * costPerUnit;

		int datayearId = DataYearId.getDataYearId();
		float currentMony = 0;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select Current_Mony from cash_box where"
							+ " DataYearId=" + datayearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				currentMony = result.getFloat(1);
				System.err.println(currentMony);
				System.err.println(totalCost);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if invoice cost is greater than current money
		if (currentMony < totalCost) {
			System.err.println("firsr if");
			int ask = new JOptionPane()
					.showConfirmDialog(
							null,
							"شما در صندوق موجودی کافی برای ثبت این فاکتور ندارید! در صورت ثنت، به حساب بدهکاری شرکت افزوده خواهد شد؛ آیا مایل به ثبت فاکتور هستید؟",
							"هشدار", JOptionPane.YES_NO_OPTION,
							JOptionPane.YES_OPTION);
			if (ask == JOptionPane.YES_OPTION) {
				addToDeptBox();
				return 0;
			}
			return -1;

			// if invoice cost is equal to current money
		} else if (currentMony == totalCost) {
			System.err.println("second if");
			int ask = new JOptionPane()
					.showConfirmDialog(
							null,
							"با ثبت این فاکتور موجودی شما صفر خواهد شد؛ آیا مایل به  ثبت فاکتور هستید؟",
							"آگهی", JOptionPane.YES_NO_OPTION,
							JOptionPane.YES_OPTION);
			if (ask == JOptionPane.YES_OPTION) {
				changeCashBox();
				return 0;
			}
			// if invoice cost is less than current money
		} else {
			System.err.println("else");
			changeCashBox();
			return 0;
		}

		return -1;
	}

	/**
	 * Sums the new invoice cost with the previous consumption and subtracts
	 * from the cash box
	 */
	// new method
	private boolean changeCashBox() {
		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		float cost = Float.parseFloat(table.getValueAt(0, 2).toString());
		totalCost = qty * cost;
		int dataYearId = DataYearId.getDataYearId();
		float cunsuption = 0;
		float currentMoney = 0;

		try {
			PreparedStatement loadStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select Consumption,Current_Mony from cash_box where DataYearId="
							+ dataYearId);
			ResultSet result = loadStatement.executeQuery();
			while (result.next()) {
				cunsuption = result.getFloat(1);
				currentMoney = result.getFloat(2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		// Changes the values
		cunsuption += totalCost;
		currentMoney -= totalCost;

		// save values to DB
		try {
			PreparedStatement saveStatememnt = (PreparedStatement) DBConnection.connection
					.prepareStatement("update cash_box set Consumption ="
							+ cunsuption + ", Current_Mony=" + currentMoney
							+ " where DataYearId=" + dataYearId);
			saveStatememnt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Sums the invoice cost on the company dept account.
	 */

	private void addToDeptBox() {
		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		float cost = Float.parseFloat(table.getValueAt(0, 2).toString());
		totalCost = qty * cost;
		int dataYearId = DataYearId.getDataYearId();
		float consumption = 0;
		float dept = 0;
		try {
			PreparedStatement loadStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select CompanyDept,Consumption from cash_box where DataYearId = "
							+ dataYearId);
			ResultSet result = loadStatement.executeQuery();
			while (result.next()) {
				dept = result.getFloat(1);
				consumption = result.getFloat(2);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Set new dept value
		dept += totalCost;
		consumption += totalCost;

		try {
			PreparedStatement saveStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update cash_box set CompanyDept=" + dept
							+ ", Consumption =" + consumption
							+ " where DataYearId =" + dataYearId);
			saveStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateStockLabels(JLabel consumptionValueLbl,
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
