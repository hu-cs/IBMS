package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Invoice;

public class QuantityChangeListener implements PropertyChangeListener {

	JLabel totalValue;
	JTable invoiceTable;

	public QuantityChangeListener(JLabel totalValue, JTable invoiceTable) {
		this.totalValue = totalValue;
		this.invoiceTable = invoiceTable;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		setMaterialTotalPrice();
		setInvoiceTotalValue();
//		setMateriaCost();

	}

	private void setInvoiceTotalValue() {

		int selectedRow = invoiceTable.getSelectedRow();

		if (selectedRow < 0)
			return;
		if (invoiceTable.getValueAt(selectedRow, 0) == null)
			return;

		if (invoiceTable.getValueAt(selectedRow, 3) == null)
			return;
		float totalPrice = 0;
		for (int tableCounter = 0; tableCounter < invoiceTable.getRowCount(); tableCounter++) {
			if (invoiceTable.getValueAt(tableCounter, 0) == null)
				return;
			totalPrice += Float.parseFloat(invoiceTable.getValueAt(
					tableCounter, 0).toString());
		}

		totalValue.setText(totalPrice + "");

	}

	private void setMaterialTotalPrice() {
		float price = 0;
		float qty = 0;
		int selectedRow = invoiceTable.getSelectedRow();
		if (selectedRow < 0)
			return;

		// if (invoiceTable.getRowCount() <= 0)
		// return;
		if (invoiceTable.getValueAt(selectedRow, 3) == null)
			return;

		try {
			qty = Float.parseFloat(invoiceTable.getValueAt(selectedRow, 3)
					.toString());
		} catch (java.lang.NumberFormatException e) {
			new JOptionPane().showMessageDialog(null,
					"رفم وارد کرده درست نیست،لطفا دوباره امتحان کنید.");
		}

		if (invoiceTable.getValueAt(selectedRow, 1) == null)
			return;
		try {
			price = Float.parseFloat(invoiceTable.getValueAt(selectedRow, 1)
					.toString());
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null,
					"قیمت وارد شده درست نیستر،لطفا دوباره وارد کنید.");
		}

		invoiceTable.setValueAt(qty * price, selectedRow, 0);

	}

	private void setMateriaCost() {
		int selectedRow = invoiceTable.getSelectedRow() - 1;
		if (selectedRow < 0)
			return;

		if (invoiceTable.getValueAt(selectedRow, 3) == null)
			return;

		float qty = Float.parseFloat(invoiceTable.getValueAt(selectedRow, 3)
				.toString());
		if (Invoice.materils.get(selectedRow) == null)
			return;

		Invoice.materils.get(selectedRow).setQty(qty);

	}

}
