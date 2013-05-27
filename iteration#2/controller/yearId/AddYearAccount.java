package controller.yearId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AddYearAccount implements ActionListener {

	JTable yearSelectionTable;

	public AddYearAccount(JTable yearSelectionTable) {
		this.yearSelectionTable = yearSelectionTable;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		addRow();

	}

	private void addRow() {
		((DefaultTableModel) yearSelectionTable.getModel())
				.addRow(new String[] {});
	}

}
