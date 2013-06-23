package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;

public class DeleteYearId implements ActionListener {

	JTable selectionYear;
	int selectedYear;

	public DeleteYearId(JTable selectionYear) {
		this.selectionYear = selectionYear;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (deleteRow() != -1)
			updateIdList();
		deleteCashBox();

	}

	private int deleteRow() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		// int selectedYear;
		int selectedRow = selectionYear.getSelectedRow();
		if (selectedRow != -1) {
			selectedYear = Integer.parseInt(selectionYear.getValueAt(
					selectedRow, 1).toString());
			int ask = new JOptionPane()
					.showConfirmDialog(
							null,
							"اگر این حساب سال زا پاک کنید، تمام حسابات مربوط به این سال پاک خواهد شد؛ آیا هنوز مایل به پاک کردن هستید؟");
			if (ask == JOptionPane.YES_OPTION) {

				try {
					PreparedStatement statement = (PreparedStatement) DBConnection.connection
							.prepareStatement("delete from data_year where Year_Id ="
									+ selectedYear);
					statement.execute();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			new JOptionPane().showMessageDialog(null,
					"اطفا برای پاک کردن حساب سال، یک ردیف انتخاب کنید!");
			return -1;
		}

		return 0;
	}

	private void updateIdList() {

		((DefaultTableModel) selectionYear.getModel()).setRowCount(0);

		int yearId;
		boolean isSelected;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT year_Id,is_selected FROM `data_year` ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				yearId = result.getInt(1);
				isSelected = result.getBoolean(2);
				((DefaultTableModel) selectionYear.getModel())
						.addRow(new Object[] { isSelected, yearId,
								selectionYear.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteCashBox() {

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("delete from cash_box where datayearid="
							+ selectedYear);
			statement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
