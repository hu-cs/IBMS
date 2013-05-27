package controller.yearId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import view.DataYearIdCreation;

public class AddYearId implements ActionListener {

	JTable selectionTable;

	public AddYearId(JTable selectionTable) {
		this.selectionTable = selectionTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new DataYearIdCreation(selectionTable);

	}

}
