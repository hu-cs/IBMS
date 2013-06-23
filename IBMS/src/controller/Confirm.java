package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class Confirm implements ActionListener {

	
	JDialog close;
	public Confirm (JDialog close){
		this.close = close;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		close.dispose();
	}

	void confirm() {

	}

}
