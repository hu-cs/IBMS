package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import model.Invoice;
import model.InvoiceMaterial;

import org.jdesktop.swingx.util.Resize;

public class AddRowButtonListener implements ActionListener {

	JTable table;
	JFrame invoiceFrame;

	public AddRowButtonListener(JTable table, JFrame invoiceFrame) {
		this.table = table;
		this.invoiceFrame = invoiceFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addRowToTable();
		// createObject();
	}

	private void addRowToTable() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		if (table.getValueAt(table.getRowCount() - 1, 0) != null) {
			resize();
			((DefaultTableModel) table.getModel()).addRow(new Object[] {});
			table.setValueAt(table.getRowCount(), table.getRowCount() - 1, 5);

		} else {
			new JOptionPane().showMessageDialog(null,
					"لطفا  اطلاعات را در ردیف اخیر وارد کنید!");
		}

	}

	private void resize() {
		invoiceFrame.setSize(new Dimension(((int) invoiceFrame.getSize()
				.getWidth()), ((int) invoiceFrame.getSize().getHeight() + 24)));

	}

	private void createObject() {
		InvoiceMaterial newMaterial = new InvoiceMaterial("", "", 0, 0);
		Invoice.materils.put(Invoice.materils.size() + 1, newMaterial);

	}

}
