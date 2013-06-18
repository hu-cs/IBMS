package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;
import view.CompanyUsed;
import view.MainUi;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class AddUsedItem implements ActionListener {

	JTable updatable;
	JTable table;
	JComboBox<String> typeCombo;
	JComboBox<String> editorComboBox;
	JDialog dialog;
	float invoiceCost;
	boolean isCash = true;
	String dbTable;

	public AddUsedItem(JTable table, JDialog dialog, String dbTable,
			JComboBox<String> selectTypeCombo, JTable updatable) {
		this.table = table;
		this.updatable = updatable;
		this.dbTable = dbTable;
		this.typeCombo = selectTypeCombo;
		this.dialog = dialog;
		editorComboBox = new JComboBox<String>();
		for (String type : CompanyUsed.typeList) {
			editorComboBox.addItem(type);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (validator() != -1) {
			if (cashBoxValidator() != -1) {
				save(isCash);
				update(updatable);
				dialog.dispose();
				setCombobox(typeCombo);
				updateMainUiLabels(MainUi.consumptionValueLbl,
						MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
						MainUi.debtValesLbl);

			}
		}
	}

	private int save(boolean isCash) {
		String invoiceNum = null;

		invoiceNum = table.getValueAt(0, 2).toString();
		invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());

		String title = typeCombo.getSelectedItem().toString();

		String date = table.getValueAt(0, 0).toString();

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into "
							+ dbTable
							+ " (Title,Invoice_Number,Invoice_Cost,Date_and_time,iscash)values('"
							+ title + "','" + invoiceNum + "'," + invoiceCost
							+ ",'" + date + "'," + isCash + ")");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	private void update(JTable mainTble) {
		((DefaultTableModel) mainTble.getModel()).setRowCount(0);

		String invoiceNumber;
		float invoiceCost;
		String dateAndTime;
		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `"
							+ dbTable + "` WHERE Invoice_Cost>0");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getString(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);
				System.out.println(invoiceNumber);

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

	private int validator() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		String invoiceNumber;
		float invoiceCost;
		String date;

		try {

			invoiceNumber = table.getValueAt(0, 2).toString();
			invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());
			date = table.getValueAt(0, 0).toString();
			if (date.trim().isEmpty() || invoiceCost == 0
					|| invoiceNumber.trim().isEmpty()) {
				new JOptionPane().showMessageDialog(null,
						"Insert values correctly!");
				return -1;
			}

		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"لطفا اطلاعات را در جدول فوق وارد کنید!");
			return -1;
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null,
					"لطفا قیمت فاکتور را درست وارد کنید!");
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
		invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());
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
				System.err.println(invoiceCost);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if invoice cost is greater than current money
		if (currentMony < invoiceCost) {
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
		} else if (currentMony == invoiceCost) {
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
	private void changeCashBox() {
		invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());
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
		}

		// Changes the values
		cunsuption += invoiceCost;
		currentMoney -= invoiceCost;

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
		}

	}

	/**
	 * Sums the invoice cost on the company dept account.
	 */

	private void addToDeptBox() {
		invoiceCost = Float.parseFloat(table.getValueAt(0, 1).toString());
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
		dept += invoiceCost;
		consumption += invoiceCost;

		try {
			PreparedStatement saveStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update cash_box set CompanyDept=" + dept
							+ ", Consumption =" + consumption
							+ " where DataYearId =" + dataYearId);
			saveStatement.execute();
			isCash = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
