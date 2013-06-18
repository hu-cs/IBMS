package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import ui.renderers.TableEditor;

import connection.DBConnection;
import controller.AddToGiveingAccount;
import controller.DoneEditCustomerReciver;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditeCustomerPyament extends JDialog {
	public static JTable table;

	public EditeCustomerPyament() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, }, new String[] {
						CustomersList.Date, CustomersList.Cost }));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CustomersList.CancelButton);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditor());
		JButton button = new JButton(CustomersList.ok);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);
		button.addActionListener(new DoneEditCustomerReciver(table, this));

		load(CustomerDetails.giveing);

		setSize(240, 120);
		setTitle(CustomersList.EditCusotmerAccount);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new EditeCustomerPyament();
	}

	private void load(JTable giveing) {
		// TODO Auto-generated method stub

		int selectedRow = giveing.getSelectedRow();
		float cost = Float.parseFloat(giveing.getValueAt(selectedRow, 1)
				.toString());
		String date = giveing.getValueAt(selectedRow, 0).toString();
		((DefaultTableModel) table.getModel()).setRowCount(0);
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account WHERE Cost = "
							+ cost + "AND Date_And_Time = '" + date + "' ");
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				float costEdit = result.getFloat(1);
				String dateEdit = result.getString(2);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						dateEdit, costEdit });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
