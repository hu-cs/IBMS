package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import view.MainUi;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import model.*;

public class InvoiceChanges implements ActionListener {

	JTable stockTable;
	JTable soldMaterialList;
	JTable selHistoryTable;
	JTable customerShoppingAccount;
	ArrayList<InvoiceMaterial> invoiceMaterialList;
	JLabel invoiceNumber;
	JXDatePicker dateAndTime;
	JLabel invoiceTotalCost;
	JComboBox<Customer> customers;
	JTable invoiceTable;
	JFrame invoiceFrame;

	public InvoiceChanges(JLabel invoiceNumber, JXDatePicker dateAndTime,
			JLabel invoiceTotalCost, JComboBox<Customer> customers,
			JTable invoiceTable, JFrame invoiceFrame) {
		this.invoiceTotalCost = invoiceTotalCost;
		this.dateAndTime = dateAndTime;
		this.invoiceNumber = invoiceNumber;
		this.customers = customers;
		this.invoiceTable = invoiceTable;
		this.invoiceFrame = invoiceFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (interfaceDataValidator() == true) {
			loadStockQuantity(Stock.stockMaterialList);
			createInvoiceObjects();
			if (stockValidator() == true) {
				if (invoiceDate() != null) {
					stockSubtraction();
					addToCustomerAccount();
					addToSellHistory();
					changeTheSoldMaterialList();
					saveInvoice();
					addToInvoiceMaterialList();
					changeTheCachBox();
					setLablesValues(MainUi.consumptionValueLbl,
							MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
							MainUi.debtValesLbl);
					invoiceFrame.setVisible(false);
					changeCustomerShoppingAccount();
					updateMainUiTable(MainUi.table);
				}
			}
		}
	}

	/**
	 * It subtracts the sold materials from the stock account
	 */
	public boolean stockSubtraction() {

		float lastQty = 0;
		float soldQty = 0;
		float newQty = 0;

		int dataYearId = DataYearId.getDataYearId();
		InvoiceMaterial tempMaterial;
		for (Integer mapCounter : Invoice.materils.keySet()) {
			tempMaterial = Invoice.materils.get(mapCounter);

			soldQty = tempMaterial.getQty();
			for (StockMaterial tempM : Stock.stockMaterialList) {
				if (tempMaterial.getName().equalsIgnoreCase(tempM.getName())) {
					lastQty = tempM.getQty();
					newQty = lastQty - soldQty;
					try {
						PreparedStatement statement = (PreparedStatement) DBConnection.connection
								.prepareStatement("update stock set Quantity="
										+ newQty + "where Name = '"
										+ tempMaterial.getName()
										+ "' and DataYearId =" + dataYearId);
						statement.execute();

					} catch (SQLException e) {
						e.printStackTrace();
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * After the invoice emission, the shopped invoice Materials will be added
	 * to the Each material sell history list.
	 */
	public boolean addToSellHistory() {

		int dataYearId = DataYearId.getDataYearId();
		String customerName = ((Customer) customers.getSelectedItem())
				.getName()
				+ " "
				+ ((Customer) customers.getSelectedItem()).getLastName();

		String dateAndTime = invoiceDate();
		int qty;
		String materialName;

		for (Integer listCounter : Invoice.materils.keySet()) {
			materialName = Invoice.materils.get(listCounter).getName();
			qty = (int) Invoice.materils.get(listCounter).getQty();
			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("insert into sell_history (Name,Quantity,Buyer,Date_And_Time,DataYearId) Values('"
								+ materialName
								+ "',"
								+ qty
								+ ",'"
								+ customerName
								+ "','"
								+ dateAndTime
								+ "',"
								+ dataYearId + ")");

				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	/**
	 * After the invoice emission, the shopped invoice cost will be added to the
	 * customer shopping account.
	 */
	public boolean addToCustomerAccount() {

		int dataYearId = DataYearId.getDataYearId();
		int invoiceNum = Integer.parseInt(invoiceNumber.getText());
		double invoiceCost = Double.parseDouble(invoiceTotalCost.getText());
		String dateAndTime = invoiceDate();
		String customerName = customers.getSelectedItem().toString();
		// TODO how to calculate date and time.

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into customer_shopping_account "
							+ "(Invoice_Numebr,Invoice_Cost,Date_And_Time,DataYearId,Customer_Name) Values("
							+ invoiceNum + "," + invoiceCost + ",'"
							+ dateAndTime + "'" + "," + dataYearId + ",'"
							+ customerName + "')");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}

	/**
	 * After the invoice emission, the shopped invoice sold material will be
	 * added to the Sold material list.
	 */

	public boolean changeTheSoldMaterialList() {
		int dataYearId = DataYearId.getDataYearId();

		// Loading the previous sold material list to be updated

		try {
			java.sql.PreparedStatement loadStatemnet = DBConnection.connection
					.prepareStatement("SELECT name,unit,quantity FROM `sold_material_list` WHERE dataYearId = 2013");
			ResultSet result = loadStatemnet.executeQuery();
			while (result.next()) {
				SoldMaterial loadedMaterial = new SoldMaterial(
						result.getString(1), result.getString(2),
						result.getInt(3));
				SoldMaterial.soldMaterial.add(loadedMaterial);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String materialName;
		int lastSoldQty;
		int newSoldQty;
		int currentSoldQty;
		for (Integer counter : Invoice.materils.keySet()) {
			for (SoldMaterial tempMaterial : SoldMaterial.soldMaterial) {
				if (tempMaterial.getName().equalsIgnoreCase(
						Invoice.materils.get(counter).getName())) {
					lastSoldQty = tempMaterial.getQty();
					newSoldQty = (int) Invoice.materils.get(counter).getQty();
					currentSoldQty = lastSoldQty + newSoldQty;
					materialName = tempMaterial.getName();

					try {
						PreparedStatement statement = (PreparedStatement) DBConnection.connection
								.prepareStatement("update sold_material_list "
										+ "set Quantity=" + currentSoldQty
										+ " where Name = '" + materialName
										+ "' and DataYearId =" + dataYearId);

						statement.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
					break;
				}
			}
		}
		return true;
	}

	public void createInvoiceObjects() {

		for (int counter = 0; counter < Invoice.materils.size(); counter++) {
			Invoice.materils.remove(counter);
		}

		String materialName;
		String unit;
		int qty;
		float sellCost;

		for (int rowCounter = 0; rowCounter < invoiceTable.getRowCount(); rowCounter++) {
			materialName = invoiceTable.getValueAt(rowCounter, 4).toString();
			unit = invoiceTable.getValueAt(rowCounter, 2).toString();
			qty = Integer.parseInt(invoiceTable.getValueAt(rowCounter, 3)
					.toString());
			sellCost = Float.parseFloat(invoiceTable.getValueAt(rowCounter, 1)
					.toString());

			InvoiceMaterial newMaterial = new InvoiceMaterial(materialName,
					unit, sellCost, qty);
			Invoice.materils.put(rowCounter + 1, newMaterial);

		}

	}

	public boolean saveInvoice() {
		String date = invoiceDate();
		String customer = customers.getSelectedItem().toString();
		float cost = Float.parseFloat(invoiceTotalCost.getText());
		int dataYearId = DataYearId.getDataYearId();
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into invoice_list (Cost,Customer,DateAndTime,DataYearId)values("
							+ cost
							+ ",'"
							+ customer
							+ "','"
							+ date
							+ "',"
							+ dataYearId + ")");

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public boolean interfaceDataValidator() {

		for (int rowCounter = 0; rowCounter < invoiceTable.getRowCount(); rowCounter++) {
			if (invoiceTable.getValueAt(rowCounter, 0) == null) {
				new JOptionPane()
						.showMessageDialog(
								null,
								"اطلاعات وارد شده در داخل فاکتور کامل نیست،لطفا اطلاعات را جک نموده دوباره امتحان کنید!");
				return false;

			}

		}

		return true;
	}

	private String invoiceDate() {
		String date;

		try {
			this.dateAndTime.getDate().setTime(System.currentTimeMillis());
			date = dateAndTime.getDate().toLocaleString();
			return date;
		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"اطفا تاریخ را انتخاب کنید!");
		}

		return null;

	}

	public boolean loadStockQuantity(ArrayList<StockMaterial> stockQuantity) {
		int dataYearId = DataYearId.getDataYearId();
		String name;
		String unit;
		int qty;

		for (int i = 0; i < stockQuantity.size(); i++) {
			stockQuantity.remove(i);
		}

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT name,unit,quantity FROM `stock` WHERE datayearid="
							+ dataYearId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				name = result.getString(1);
				unit = result.getString(2);
				qty = result.getInt(3);

				StockMaterial newStockM = new StockMaterial(name, unit, qty);
				stockQuantity.add(newStockM);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;

	}

	public boolean stockValidator() {
		float lastQty;
		float soldQty;
		InvoiceMaterial tempMaterial;

		for (Integer mapCounter : Invoice.materils.keySet()) {
			tempMaterial = Invoice.materils.get(mapCounter);
			soldQty = tempMaterial.getQty();
			for (StockMaterial tempM : Stock.stockMaterialList) {
				if (tempMaterial.getName().equalsIgnoreCase(tempM.getName())) {
					lastQty = tempM.getQty();
					if (soldQty > lastQty) {
						// we have no enough material in the stock
						new JOptionPane()
								.showMessageDialog(
										null,
										tempMaterial.getName()
												+ " کافی در انبار وجود ندارد!لطفا انبار را چک کنید!");

						return false;
					}
				}
			}
		}
		return true;
	}

	private void addToInvoiceMaterialList() {
		String customer = customers.getSelectedItem().toString();
		int invoiceNumber = Integer.parseInt(this.invoiceNumber.getText());
		int dataYearId = DataYearId.getDataYearId();
		String date = dateAndTime.getDate().toLocaleString();
		String name;
		String unit;
		int qty;
		float cost;

		for (Integer tempKey : Invoice.materils.keySet()) {

			name = Invoice.materils.get(tempKey).getName();
			unit = Invoice.materils.get(tempKey).getUnit();
			qty = Invoice.materils.get(tempKey).getQty();
			cost = Invoice.materils.get(tempKey).getSellCost();

			try {
				PreparedStatement statement = (PreparedStatement) DBConnection.connection
						.prepareStatement("insert into invoice_materials values('"
								+ name
								+ "','"
								+ unit
								+ "',"
								+ qty
								+ ","
								+ cost
								+ ","
								+ invoiceNumber
								+ ",'"
								+ customer
								+ "','"
								+ date + "'," + dataYearId + ")");
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public boolean changeTheCachBox() {

		int dataYearId = DataYearId.getDataYearId();
		float sale = 0;
		float current_mony = 0;
		try {
			PreparedStatement loadStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT sale,Current_Mony FROM `cash_box` WHERE datayearid="
							+ dataYearId);
			ResultSet result = loadStatement.executeQuery();
			while (result.next()) {
				sale = result.getFloat(1);
				current_mony = result.getFloat(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		for (Integer tmepInvoice : Invoice.materils.keySet()) {

			sale += Invoice.materils.get(tmepInvoice).getTotal();

		}
		current_mony += sale;

		try {
			PreparedStatement savaStatement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update cash_box set sale=" + sale
							+ ",Current_Mony=" + current_mony
							+ " where datayearid=" + dataYearId);
			savaStatement.execute();
		} catch (SQLException e) {

			return false;
		}

		return true;

	}

	public void setLablesValues(JLabel consumptionValueLbl,
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

	/**
	 * Directly sets the customer shopping account and company demand to be set
	 * in the customer list
	 */
	public boolean changeCustomerShoppingAccount() {

		float sale = 0;

		Customer customer = ((Customer) customers.getSelectedItem());
		String name = customer.getName();
		String lastName = customer.getLastName();
		float shopping = customer.getShopping();
		float demand = customer.getCompanyDemand();

		for (Integer tmepInvoice : Invoice.materils.keySet()) {

			sale += Invoice.materils.get(tmepInvoice).getTotal();

		}

		shopping += sale;
		demand += sale;

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("update customer_list set shopping_account = "
							+ shopping
							+ ",company_demand = "
							+ demand
							+ " where name = '"
							+ name
							+ "' and last_name = '"
							+ lastName
							+ "' and dataYearid ="
							+ DataYearId.getDataYearId());
			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private void updateMainUiTable(JTable table) {
		((DefaultTableModel) table.getModel()).setRowCount(0);

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

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						unit, qty, name, table.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
