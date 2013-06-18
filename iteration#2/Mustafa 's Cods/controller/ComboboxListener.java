package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

import model.Invoice;
import model.InvoiceMaterial;
import model.Material;

public class ComboboxListener implements ActionListener {
	JComboBox<Material> mLCombobox;
	JTable invoiceTable;

	public ComboboxListener(JTable inoiceTable, JComboBox<Material> materialList) {
		this.invoiceTable = inoiceTable;
		this.mLCombobox = materialList;
	}

	private void setTableValues() {
		Material selectedMaterial = ((Material) mLCombobox.getSelectedItem());
		if (selectedMaterial == null)
			return;
		String name = selectedMaterial.getName();
		String unit = selectedMaterial.getUnit();
		float finishedCost = selectedMaterial.getFinishedCost();
		float sellCost = selectedMaterial.getSellCost();
		if (selectedMaterial == null)
			return;
		int tableSelectedRow = invoiceTable.getSelectedRow();

		// setting the material cost and unit to the related cells.
		invoiceTable
				.setValueAt(selectedMaterial.getUnit(), tableSelectedRow, 2);
		invoiceTable.setValueAt(selectedMaterial.getSellCost(),
				tableSelectedRow, 1);

		// Creating the invoice material object and add it to the hash map
		InvoiceMaterial newMaterial = new InvoiceMaterial(name, unit, sellCost,
				0);
		Invoice.materils.put(invoiceTable.getRowCount(), newMaterial);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		setTableValues();
		// setNewObjectValues();

	}

}
