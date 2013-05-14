package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class CancelButtonListener implements ActionListener {

	JFrame invoice;

	public CancelButtonListener(JFrame invoice) {
		this.invoice = invoice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		invoice.setVisible(false);

	}

}
