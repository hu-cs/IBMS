package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;

public class AddUsedTypeListener implements ActionListener {

	JTable table;
	JDialog dialog;
	String dbTable;
	JComboBox<String> typeCombobox;
	ArrayList<String> typeList;

	// JComboBox<String> typeCombo;

	public AddUsedTypeListener(JTable table, JDialog dialog, String dbTable,
			JComboBox<String> typeCombobox, ArrayList<String> typeList) {
		this.table = table;
		this.dialog = dialog;
		this.dbTable = dbTable;
		this.typeCombobox = typeCombobox;
		this.typeList = typeList;
		// this.typeCombo = typeCombo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (saveType() != -1) {
			updateCombobox();
			dialog.dispose();

		}
	}

	private int saveType() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		String type = null;

		// If nothing is entered
		try {
			type = table.getValueAt(0, 0).toString();
		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"اطلاعات وارد شده درست نیست، لطفا دوباره امتحان کنید!");
			return -1;
		}

		if (table.getValueAt(0, 0).toString().trim().isEmpty()) {
			new JOptionPane().showMessageDialog(null,
					"اطلاعات وارد شده درست نیست، لطفا دوباره امتحان کنید!");
			return -1;
		}

		for (String iteratedType : typeList) {
			// if(iteratedType.equalsIgnoreCase(" "))
			if (iteratedType.equalsIgnoreCase(type)) {
				new JOptionPane()
						.showMessageDialog(null,
								"این نوع مصرف قبلا در برنامه تعریف شده است، اطفا دقت کنید!");
				return -1;
			}
		}

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into " + dbTable
							+ "(title,datayearid)values('" + type + "',"
							+ DataYearId.getDataYearId() + ")");

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private void updateCombobox() {

		// make the combobox empty
		typeCombobox.setModel(new DefaultComboBoxModel<String>());
		typeCombobox.addItem("همه");
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM " + dbTable
							+ " where datayearid ="
							+ DataYearId.getDataYearId());

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				typeList.add(type);
				typeCombobox.addItem(type);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
