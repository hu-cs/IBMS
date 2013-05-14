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
			// Write the update query

		}
		return 0;

	}

	// updateStock();

	// }

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

			// statement.setInt(0, DataYearId.yearId);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// updateCustomerAccount();
	}

	/**
	 * After the invoice emission, the shopped invoice sold material will be
	 * added to the Sold material list.
	 */
	// It is done

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

						// statement.setInt(0, DataYearId.yearId);
						// // statement.seti

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
		// InvoiceMaterial a = new InvoiceMaterial(name, unit, finishedCost,
		// sellCost, qty)

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

	/**
	 * updates the stock list
	 */
	// It is done
	private void updateStock() {
		String name;
		String unit;
		int qty;
		((DefaultTableModel) stockTable.getModel()).setRowCount(0);
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Name,Unit,Quantity FROM `stock` ");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				qty = result.getInt(3);

				StockMaterial newMaterial = new StockMaterial(name, unit, qty);
				Stock.stockMaterialList.add(newMaterial);
				((DefaultTableModel) stockTable.getModel())
						.addRow(new String[] {
								stockTable.getRowCount() + 1 + "", name,
								qty + "", unit });
			}

		} catch (SQLException e) {
			// TODO exception handling
			e.printStackTrace();
		}

	}

	/**
	 * It updates the sold material list after the invoice emission.
	 */
	// It is done
	private void updateSoldMaterialList() {

		((DefaultTableModel) soldMaterialList.getModel()).setRowCount(0);

		String name;
		String unit;
		int qty;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select Name,Unit,Quantity from sold_material_list");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				qty = result.getInt(3);

				SoldMaterial.soldMaterial
						.add(new SoldMaterial(name, unit, qty));
				((DefaultTableModel) soldMaterialList.getModel())
						.addRow(new String[] {
								soldMaterialList.getRowCount() + 1 + "", name,
								qty + "", unit });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateSellHistoryList() {
		// TODO The material sell history must to be updated at the interface
		// opening time.

		// try {
		// PreparedStatement statement = (PreparedStatement)
		// DBConnection.connection
		// .prepareStatement("SELECT "
		// + "`Quantity`,`Buyer`,`Date_And_Time` "
		// + "FROM `sell_history` where Name = ''");
		//
		// ResultSet result = statement.executeQuery();
		// while(result.next()){
		//
		//
		// }
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		//
	}

	/**
	 * Updates the customer shopping account
	 */
	// It is done
	private void updateCustomerAccount() {

		int invoiceNumber;
		double invoiceCost;
		String dateAndTime;
		((DefaultTableModel) customerShoppingAccount.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Invoice_Numebr,Invoice_Cost,Date_And_Time "
							+ "FROM `customer_shopping_account`");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				invoiceNumber = result.getInt(1);
				invoiceCost = result.getDouble(2);
				dateAndTime = result.getString(3);
				CustomerShoppingAccount.shoppingList
						.add(new CustomerShoppingAccount(invoiceNumber,
								invoiceCost, dateAndTime));
				((DefaultTableModel) customerShoppingAccount.getModel())
						.addRow(new String[] {
								customerShoppingAccount.getRowCount() + 1 + "",
								invoiceNumber + "", invoiceCost + "",
								dateAndTime });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		long time = System.currentTimeMillis();

		Calendar cal = Calendar.getInstance();

		cal.setTimeInMillis(time);

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		System.out.println(time);

		int option = JOptionPane.showConfirmDialog(null, "Ok/NO/Cancel",
				"ttitle", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null);
		if (option == JOptionPane.YES_OPTION)
			;
		else if (option == JOptionPane.NO_OPTION)
			;
		else if (option == JOptionPane.CANCEL_OPTION)
			;

	}

}
