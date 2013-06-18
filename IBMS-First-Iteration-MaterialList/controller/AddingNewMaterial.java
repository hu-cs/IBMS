package controller;
import view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTable;

import connection.DBConnection;

public class AddingNewMaterial implements ActionListener {
 
	JTable table;
	public AddingNewMaterial(JTable table){
		this.table= table;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		displayUI();
//		addingNewMaterial(AddingNewMaterial);
	}

	void displayUI() {
		new view.AddingNewMaterial();

	}

	

}
