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
import javax.swing.table.DefaultTableModel;

import view.MainUi;
import view.RawMaterial;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;

public class EditRawMaterial implements ActionListener {

	String title;
	JTable table;
	String invoiceLastNumber;
	JDialog dialog;

	public EditRawMaterial(String title, JTable table,
			String invoiceLastNumber, JDialog dialog) {
		this.title = title;
		this.dialog = dialog;
		this.table = table;
		this.invoiceLastNumber = invoiceLastNumber;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (validator() != -1) {
			if (editCashBox() != -1) {
				edit();
				update(RawMaterial.table);
				dialog.dispose();
				setCombobox(RawMaterial.selectTypeCombo);
				updateMainUiLabels(MainUi.consumptionValueLbl,
						MainUi.saleValuesLbl, MainUi.currentMonyValueLbl,
						MainUi.debtValesLbl);
			}

		}

	}

	private boolean edit() {

		System.err.println(title);
		String invoiceNumber = table.getValueAt(0, 4).toString();
		System.err.println("invoce number " + invoiceNumber);

		float cost = Float.parseFloat(table.getValueAt(0, 2).toString());

		String date = table.getValueAt(0, 0).toString();

		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		String unit = table.getValueAt(0, 3).toString();
		float totalCost = qty * cost;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("update raw_material set invoice_number = '"
							+ invoiceNumber
							+ "',quantity="
							+ qty
							+ ", unit = '"
							+ unit
							+ "',cost ="
							+ cost
							+ ",Invoice_Cost="
							+ totalCost
							+ ",dateAndTime = '"
							+ date
							+ "' where invoice_number = '"
							+ invoiceLastNumber
							+ "' and title = '"
							+ title
							+ "'");
			statement.execute();
			System.out.println("edit is done");

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private void update(JTable mainTble) {
		((DefaultTableModel) mainTble.getModel()).setRowCount(0);

		String invoiceNumber;
		String title;
		float qty;
		String unit;
		float cost;
		float total;
		String dateAndTime;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,invoice_number,quantity,unit,cost,Invoice_Cost,dateandtime FROM `raw_material` where datayearid = 2013");

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				title = result.getString(1);
				invoiceNumber = result.getString(2);
				qty = result.getFloat(3);
				unit = result.getString(4);
				cost = result.getFloat(5);
				total = result.getFloat(6);
				dateAndTime = result.getString(7);

				((DefaultTableModel) mainTble.getModel()).addRow(new Object[] {
						dateAndTime, total, qty, cost, unit, invoiceNumber,
						title, mainTble.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private int validator() {

		String invoiceNumber;
		float cost;
		String date;
		float qty;
		String unit;
		try {
			invoiceNumber = table.getValueAt(0, 4).toString();
			cost = Float.parseFloat(table.getValueAt(0, 2).toString());
			date = table.getValueAt(0, 0).toString();
			qty = Float.parseFloat(table.getValueAt(0, 1).toString());
			unit = table.getValueAt(0, 3).toString();

			if (date.trim().isEmpty() || invoiceNumber.trim().isEmpty()
					|| unit.trim().isEmpty()) {
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

	private void setCombobox(JComboBox<String> typeCombo) {
		typeCombo.setSelectedIndex(0);

	}

	private int editCashBox() {

		boolean isCash = true;
		float invoiceLastCost = 0;
		float qty = Float.parseFloat(table.getValueAt(0, 1).toString());
		float cost = Float.parseFloat(table.getValueAt(0, 2).toString());
		float invoiceNewCost = qty * cost;
		float lastCashBox = 0;
		float newCashBox = 0;
		float lastConsuption = 0;
		float newConsuption = 0;
		float lastDept = 0;
		float newDept = 0;

		System.err.println(invoiceLastNumber);
		System.err.println(title);

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Invoice_Cost,iscash FROM `raw_material` WHERE invoice_number = '"
							+ invoiceLastNumber
							+ "' and title = '"
							+ title
							+ "'");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				invoiceLastCost = result.getFloat(1);
				isCash = result.getBoolean(2);

				System.out.println("invoce last cost " + invoiceLastCost);
				System.out.println("invoce is cash" + isCash);
				System.out.println("invoce is not cash" + !isCash);

				System.err
						.println("--------------------------------------------------------------------------");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT consumption,current_mony,companyDept FROM `cash_box` where datayearid=" + 2013);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				lastConsuption = result.getFloat(1);
				lastCashBox = result.getFloat(2);
				lastDept = result.getFloat(3);

				System.out.println("consumption " + lastConsuption);
				System.out.println("last cach box " + lastCashBox);
				System.out.println("last dept " + lastDept);
				System.err
						.println("-----------------------------------------------------------------------------");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (isCash) {
			// 1-
			// add the last invoice cost to the cash box
			// minus the last invoice cost from the last consumption
			// 2-
			// checks the new invoice cost if is greater than the cash if no?
			// if was add to dept account
			// else minus from cash box

			float tempCashBox = lastCashBox + invoiceLastCost;
			float tempConsumption = lastConsuption - invoiceLastCost;

			newConsuption = tempConsumption + invoiceNewCost;
			if (tempCashBox < invoiceNewCost) {
				int ask = new JOptionPane().showConfirmDialog(null,
						"The cash is empty, are u sure?", "Allert",
						JOptionPane.YES_NO_OPTION);
				if (ask == JOptionPane.YES_OPTION) {

					// add to dept account

					newDept = lastDept + invoiceNewCost;
					try {
						java.sql.PreparedStatement statement = DBConnection.connection
								.prepareStatement("update cash_box set consumption ="
										+ newConsuption
										+ ", current_mony = "
										+ tempCashBox
										+ ",companyDept ="
										+ newDept + " where datayearid = 2013");
						statement.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					try {
						java.sql.PreparedStatement statement = DBConnection.connection
								.prepareStatement("update raw_material set iscash =0 where invoice_number = '"
										+ invoiceLastNumber
										+ "' and title = '"
										+ title + "'");
						statement.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					return -1;
				}

			} else {
				// minus from cash box

				newCashBox = tempCashBox - invoiceNewCost;
				try {
					java.sql.PreparedStatement statement = DBConnection.connection
							.prepareStatement("update cash_box set current_mony="
									+ newCashBox
									+ ", consumption ="
									+ newConsuption
									+ " where datayearid = 2013");

					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// set the iscash to true

			}

		} else if (!isCash) {

			// 1-
			// minus the last invoice number from the dept account
			// minus the last invoice number from the consumption account
			// 2-
			// checks the new invoice cost if is greater than the cash or no?
			// if was add to dept account
			// else minus from cash box

			float tempDept = lastDept - invoiceLastCost;
			float tempConsumption = lastConsuption - invoiceLastCost;

			newConsuption = tempConsumption + invoiceNewCost;

			if (lastCashBox < invoiceNewCost) {
				int ask = new JOptionPane().showConfirmDialog(null,
						"The cash is empty, are u sure?", "Allert",
						JOptionPane.YES_NO_OPTION);
				if (ask == JOptionPane.YES_OPTION) {
					// add to dept account
					newDept = tempDept + invoiceNewCost;
					try {
						java.sql.PreparedStatement statment = DBConnection.connection
								.prepareStatement("update cash_box set companyDept="
										+ newDept
										+ ", consumption ="
										+ newConsuption
										+ " where datayearid = 2013");

						statment.execute();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					return -1;
				}

			} else {
				// minus from cash box
				newCashBox = lastCashBox - invoiceNewCost;

				try {
					java.sql.PreparedStatement statement = DBConnection.connection
							.prepareStatement("update cash_box set consumption ="
									+ newConsuption
									+ ", current_mony = "
									+ newCashBox
									+ ",companyDept ="
									+ tempDept
									+ " where datayearid = 2013");
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					java.sql.PreparedStatement statement = DBConnection.connection
							.prepareStatement("update raw_material set iscash =1 where invoice_number = '"
									+ invoiceLastNumber
									+ "' and title = '"
									+ title + "'");
					statement.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		return 0;
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
