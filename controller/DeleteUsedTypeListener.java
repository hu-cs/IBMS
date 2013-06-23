package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.Option;

import view.MainUi;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;

public class DeleteUsedTypeListener implements ActionListener {

	JTable table;
	JDialog dialog;
	String dbTable;
	JComboBox<String> selectTypeCombo;
	ArrayList<String> typeList;
	int dataYearId = DataYearId.getDataYearId();

	public DeleteUsedTypeListener(JTable table, JDialog dialog, String dbTable,
			JComboBox<String> selectTypeCombo, ArrayList<String> typeList) {
		this.table = table;
		this.dialog = dialog;
		this.dbTable = dbTable;
		this.selectTypeCombo = selectTypeCombo;
		this.typeList = typeList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (table.getSelectedRow() != -1) {
			editCashBox();
			if (delete() != -1) {
				updateCombobox();
				dialog.dispose();
				updateMainUiLabels(MainUi.consumptionValueLbl,
						MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
						MainUi.debtValesLbl);
			}
		}

	}

	private int delete() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		int selectedRow = table.getSelectedRow();

		if (selectedRow != -1) {
			String title = table.getValueAt(selectedRow, 0).toString();
			int option = JOptionPane
					.showConfirmDialog(
							null,
							"درصورت پاک کردن، تمام فاکتور های بخش مربوط پاک میشود، آیا مطمئن هستید؟",
							"هشدار", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null);

			if (option == JOptionPane.YES_OPTION) {
				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete  FROM " + dbTable
									+ " WHERE title='" + title
									+ "' and datayearid = " + dataYearId);
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
					"لطفا برای پاک کردن، یک ردیف را انتخاب کنید!");
			return -1;
		}

		return 0;
	}

	private void updateCombobox() {

		typeList = null;
		typeList = new ArrayList<String>();
		selectTypeCombo.setModel(new DefaultComboBoxModel<String>());
		// CompanyUsed.selectTypeCombo = new JComboBox<String>();
		selectTypeCombo.addItem("همه");
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM " + dbTable
							+ "where datayearid =" + dataYearId);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				typeList.add(type);
				selectTypeCombo.addItem(type);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void editCashBox() {

		float noCashInvoiceCost = 0;
		float cashedInvoiceCost = 0;
		float cashBox = 0;
		float deptBox = 0;
		float consumption = 0;

		// Select the invoice costs which is bought by cash box
		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT invoice_cost FROM `" + dbTable
							+ "` WHERE iscash=1 and datayearid = " + dataYearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				cashedInvoiceCost += result.getFloat(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Select the invoice costs which is'n bought by cash box

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT invoice_cost FROM `" + dbTable
							+ "` WHERE iscash=0 and datayearid = " + dataYearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				noCashInvoiceCost += result.getFloat(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// select the cash box content for changes

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT consumption,current_mony,companyDept FROM `cash_box` where dataYearid ="
							+ DataYearId.getDataYearId());

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				consumption = result.getFloat(1);
				cashBox = result.getFloat(2);
				deptBox = result.getFloat(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Change the cash properties and save it in the Data base
		consumption -= (noCashInvoiceCost + cashedInvoiceCost);
		cashBox += cashedInvoiceCost;
		deptBox -= noCashInvoiceCost;

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("update cash_box set consumption ="
							+ consumption + ",current_mony =" + cashBox
							+ ",companydept=" + deptBox
							+ " where dataYearid = " + dataYearId);
			statement.execute();
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
