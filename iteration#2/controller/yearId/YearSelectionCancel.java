package controller.yearId;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class YearSelectionCancel implements ActionListener {

	JDialog selectionDialog;

	public YearSelectionCancel(JDialog selectionDialog) {
		this.selectionDialog = selectionDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectionDialog.dispose();

	}

}
