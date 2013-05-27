package controler.expenses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import newViewes.CompanyUsed;

import com.mysql.jdbc.PreparedStatement;

import dataBase.DBConnection;

public class AddUsedTypeListener implements ActionListener {

	JTable table;
	JDialog dialog;

	// JComboBox<String> typeCombo;

	public AddUsedTypeListener(JTable table, JDialog dialog) {
		this.table = table;
		this.dialog = dialog;
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

		String type = null;

		try {
			type = table.getValueAt(0, 0).toString();
		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"اطلاعات وارد شده درست نیست، لطفا دوباره امتحان کنید!");
			return -1;
		}

		System.out.println("the lenght is " + type.length());

		if (type == " ") {
			System.out.println("the inserted type is not valid");
			new JOptionPane().showMessageDialog(null,
					"اطلاعات وارد شده درست نیست، لطفا دوباره امتحان کنید!");
			return -1;
		}

		for (String iteratedType : CompanyUsed.typeList) {
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
					.prepareStatement("insert into corruptionconsumption (title)values('"
							+ type + "')");

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	private void updateCombobox() {

		// make the combobox empty
		CompanyUsed.selectTypeCombo
				.setModel(new DefaultComboBoxModel<String>());
		CompanyUsed.selectTypeCombo.addItem("همه");
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM `corruptionconsumption`");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				CompanyUsed.typeList.add(type);
				CompanyUsed.selectTypeCombo.addItem(type);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
