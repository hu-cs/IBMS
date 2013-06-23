package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class DeleteRow implements ActionListener {

	JTable table;
	JFrame frame;
	JLabel totalLabel;

	public DeleteRow(JTable table, JFrame frame, JLabel totalLabel) {
		this.table = table;
		this.frame = frame;
		this.totalLabel = totalLabel;
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		deleteRow();

	}

	private void deleteRow() {
		int selectedRow = table.getSelectedRow();
		System.out.println(selectedRow);
		if (selectedRow != -1) {
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
		} else {
			new JOptionPane().showMessageDialog(null,
					"برای پاک کردن لطفا یک ردیف انتخاب کنید!");
		}

	}

	private void setTableRowNumber() {
		for (int counter = 0; counter < table.getRowCount(); counter++) {
			table.setValueAt(counter + 1, counter, 5);
		}

	}

	private void setInvoiceCost() {
		// totalTabel
		// if (table.getSelectedRow() != -1) {
		float totalValue = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			System.err.println(i);
			try {
				totalValue += Float.parseFloat(table.getValueAt(i, 0)
						.toString());
			} catch (NullPointerException e) {
				totalValue += 0;
			}

		}

		totalLabel.setText(totalValue + "");

		// } else {
		// // new JOptionPane().s
	}

	// }

	private void resize() {
		frame.setSize(((int) frame.getSize().getWidth()), ((int) frame
				.getSize().getHeight() - 24));
	}

}