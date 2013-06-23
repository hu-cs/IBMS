package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.DuplicateFormatFlagsException;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import connection.DBConnection;

public class YearCreationConfirmation implements ActionListener {

	JTable creationTable;
	JDialog dialog;
	JTable selectionTable;

	public YearCreationConfirmation(JTable creationTable,
			JTable selectionTable, JDialog dialog) {
		this.creationTable = creationTable;
		this.dialog = dialog;
		this.selectionTable = selectionTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// createId();

		if (createId() > -1)
			updateIdList();
		cashBoxCreation();
		// setSize();

	}

	private int createId() {
		
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");


		int yearId = Integer
				.parseInt(creationTable.getValueAt(0, 0).toString());

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into Data_Year (Year_Id)values("
							+ yearId + ")");
			statement.execute();
			dialog.dispose();

		} catch (MySQLIntegrityConstraintViolationException e) {
			new JOptionPane()
					.showMessageDialog(null,
							"سال وارد شده در سیستم موجود است، لطفا سال جدید وارد کنید!");
			return -1;
		} catch (NumberFormatException e) {
			new JOptionPane().showMessageDialog(null,
					"سال مالی وارد شده درست نیست، لطفا دوباره امتحان کنید!");
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			// new JOptionPane().showMessageDialog(null,
			// "اطلاعات وارد شده درست نیست اطفا دوباره تلاش کنید!");
			return -1;

		}

		return 0;

	}

	private void updateIdList() {

		((DefaultTableModel) selectionTable.getModel()).setRowCount(0);

		int yearId;
		boolean isSelected;
		// int rowCounter = 0;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT year_Id,is_selected FROM `data_year` ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				// rowCounter++;
				yearId = result.getInt(1);
				isSelected = result.getBoolean(2);
				((DefaultTableModel) selectionTable.getModel())
						.addRow(new Object[] { isSelected, yearId,
								selectionTable.getRowCount() + 1 });

				// creationTable
				// .setValueAt(yearId, rowCounter, 1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void cashBoxCreation() {
		int yearId = Integer
				.parseInt(creationTable.getValueAt(0, 0).toString());

		try {
			PreparedStatement statemnet = (PreparedStatement) DBConnection.connection
					.prepareStatement("insert into cash_box values(0,0,0,0,"
							+ yearId + ")");
			statemnet.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setSize() {
		dialog.setSize(((int) dialog.getSize().getWidth()), ((int) dialog
				.getSize().getHeight() + 30));

	}

}
