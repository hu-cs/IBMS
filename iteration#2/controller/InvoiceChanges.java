package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

import model.*;

public class InvoiceChanges implements ActionListener {

	JTable stockTable;
	JTable soldMaterialList;
	JTable selHistoryTable;
	JTable customerShoppingAccount;
	// JTable invoiceTable;
	ArrayList<InvoiceMaterial> invoiceMaterialList;
	JLabel invoiceNumber;
	JXDatePicker dateAndTime;
	JLabel invoiceTotalCost;
	JComboBox<Customer> customers;
	JTable invoiceTable;
	JFrame invoiceFrame;

	// TODO I must to set a change listener to the table for adding the
	// materials to the array list.

	// TODO How to pass the objects

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
		createInvoiceObjects();
		stockSubtraction();
		if (stockSubtraction() == -1)
			return;
		addToCustomerAccount();
		addToSellHistory();
		changeTheSoldMaterialList();
		saveInvoice();
		invoiceFrame.setVisible(false);
		// invoiceFrame.disable();TODO close the window
	}

	/**
	 * It subtracts the sold materials from the stock account
	 */
	// It is done
	private int stockSubtraction() {

		float lastQty;
		float soldQty;
		float newQty;

		// temp part
		Stock.stockMaterialList.add(new StockMaterial("colman", "jin", 50));
		Stock.stockMaterialList.add(new StockMaterial("sabad", "jin", 30));
		Stock.stockMaterialList.add(new StockMaterial("sabad picnick", "jin",
				200));

		int dataYearId = DataYearId.yearId;
		InvoiceMaterial tempMaterial;
		for (Integer mapCounter : Invoice.materils.keySet()) {
			tempMaterial = Invoice.materils.get(mapCounter);
			soldQty = tempMaterial.getQty();
			for (StockMaterial tempM : Stock.stockMaterialList) {
				if (tempMaterial.getName().equalsIgnoreCase(tempM.getName())) {
					lastQty = tempM.getQty();
					if (soldQty > lastQty) {
						// we have no enough material in the stock
						new JOptionPane().showMessageDialog(null,
								"کافی در انبار وجود ندارد!لطفا انبار را چک کنید!"
										+ tempMaterial.getName());

						return -1;

					} else {
						// update.
						newQty = lastQty - soldQty;
						try {
							PreparedStatement statement = (PreparedStatement) DBConnection.connection
									.prepareStatement("update stock set Quantity="
											+ newQty
											+ "where Name = '"
											+ tempMaterial.getName()
											+ "' and DataYearId =" + dataYearId);
							statement.execute();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

		}
		return 0;

	}



	/**
	 * After the invoice emission, the shopped invoice Materials will be added
	 * to the Each material sell history list.
	 */
	// It is done
	private void addToSellHistory() {

		// HashMap<Integer, InvoiceMaterial> invoiceMaterial = Invoice.materils;
		// for (Integer currentMaterialIndex : invoiceMaterial.keySet()) {
		//
		// }

		String customerName = ((Customer) customers.getSelectedItem())
				.getName();
		this.dateAndTime.getDate().setTime(System.currentTimeMillis());
		String dateAndTime = this.dateAndTime.getDate().toLocaleString();
		int qty;
		String materialName;
		// String name = inv

		for (Integer listCounter : Invoice.materils.keySet()) {
			System.out.println(Invoice.materils.get(listCounter).getName());
			System.out.println(Invoice.materils.get(listCounter).getSellCost());
			System.out.println(Invoice.materils.get(listCounter).getQty());
			System.out.println(Invoice.materils.get(listCounter).getTotal());
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
								+ DataYearId.yearId + ")");
				// statement.setInt(1, DataYearId.yearId);

				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * After the invoice emission, the shopped invoice cost will be added to the
	 * customer shopping account.
	 */
	private void addToCustomerAccount() {// TODO We must to take the invoice
											// number,cost and dateAndTime from
											// the interface
		int invoiceNum = Integer.parseInt(invoiceNumber.getText());
		double invoiceCost = Double.parseDouble(invoiceTotalCost.getText());
		this.dateAndTime.getDate().setTime(System.currentTimeMillis());
		String dateAndTime = this.dateAndTime.getDate().toLocaleString();
		String customerName = customers.getSelectedItem().toString();
		// TODO how to calculate date and time.

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into customer_shopping_account "
							+ "(Invoice_Numebr,Invoice_Cost,Date_And_Time,DataYearId,Customer_Name) Values("
							+ invoiceNum + "," + invoiceCost + ",'"
							+ dateAndTime + "'" + "," + DataYearId.yearId
							+ ",'" + customerName + "')");

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * After the invoice emission, the shopped invoice sold material will be
	 * added to the Sold material list.
	 */

	private void changeTheSoldMaterialList() {

		SoldMaterial.soldMaterial.add(new SoldMaterial("colman", "jin", 20));
		SoldMaterial.soldMaterial.add(new SoldMaterial("sabad", "jin", 20));
		SoldMaterial.soldMaterial.add(new SoldMaterial("sabad picnick", "jin",
				20));

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
										+ "' and DataYearId ="
										+ DataYearId.yearId);


						statement.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	private void createInvoiceObjects() {
		String materialName;
		String unit;
		float qty;
		float sellCost;

		for (int rowCounter = 0; rowCounter < invoiceTable.getRowCount(); rowCounter++) {
			materialName = invoiceTable.getValueAt(rowCounter, 4).toString();
			unit = invoiceTable.getValueAt(rowCounter, 2).toString();
			qty = Float.parseFloat(invoiceTable.getValueAt(rowCounter, 3)
					.toString());
			sellCost = Float.parseFloat(invoiceTable.getValueAt(rowCounter, 1)
					.toString());

			InvoiceMaterial newMaterial = new InvoiceMaterial(materialName,
					unit, sellCost, qty);
			Invoice.materils.put(rowCounter + 1, newMaterial);

		}

	}

	private void saveInvoice() {
		dateAndTime.getDate().setTime(System.currentTimeMillis());
		String date = dateAndTime.getDate().toLocaleString();
		String customer = customers.getSelectedItem().toString();
		float cost = Float.parseFloat(invoiceTotalCost.getText());
		int dataYearId = DataYearId.yearId;
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
		}

	}


	

}
