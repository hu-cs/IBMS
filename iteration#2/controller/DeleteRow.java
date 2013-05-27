package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DeleteRow implements ActionListener {

	JTable table;
	JFrame frame;
	JLabel totalLabel;

	public DeleteRow(JTable table, JFrame frame, JLabel totalLabel) {
		this.table = table;
		this.frame = frame;
		this.totalLabel = totalLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteRow();

	}

	private void deleteRow() {
		int selectedRow = table.getSelectedRow();
		if (table.getRowCount() == 1) {
			new JOptionPane().showMessageDialog(null,
					"شما نمیتوانید آخرین ردیف جدول را حذف کنید!");
			return;
		}

		try {
			((DefaultTableModel) table.getModel()).removeRow(selectedRow);
			setTableRowNumber();
			setInvoiceCost();
			resize();

		} catch (ArrayIndexOutOfBoundsException e) {
			new JOptionPane().showMessageDialog(null,
					"لطفا یک ردیف برای پاک کردن انتخاب کنید!");

		}

	}

	private void setTableRowNumber() {
		for (int counter = 0; counter < table.getRowCount(); counter++) {
			table.setValueAt(counter + 1, counter, 5);
		}

	}

	private void setInvoiceCost() {
		// totalTabel
		float totalValue = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			totalValue += Float.parseFloat(table.getValueAt(i, 0).toString());
		}

		totalLabel.setText(totalValue + "");
	}

	private void resize() {
		frame.setSize(((int) frame.getSize().getWidth()), ((int) frame
				.getSize().getHeight() - 24));
	}
}
