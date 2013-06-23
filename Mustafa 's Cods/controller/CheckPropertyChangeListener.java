package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CheckPropertyChangeListener implements PropertyChangeListener {

	JTable table;

	public CheckPropertyChangeListener(JTable table) {
		this.table = table;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getOldValue() == null)
			return;

		unCheck();
	}

	private void unCheck() {
		int selectedRow = table.getSelectedRow();

		if (selectedRow == -1)
			return;

		if (Boolean.TRUE.equals(table.getValueAt(selectedRow, 0)))
			for (int counter = 0; counter < table.getRowCount(); counter++) {
				if (counter != selectedRow) {
					table.setValueAt(false, counter, 0);
				}
			}
		else if (Boolean.FALSE.equals(table.getValueAt(selectedRow, 0))) {

			for (int i = 0; i < table.getRowCount(); i++)
				if (Boolean.TRUE.equals(table.getValueAt(i, 0)))
					return;

			table.setValueAt(true, selectedRow, 0);
		}
	}
}
