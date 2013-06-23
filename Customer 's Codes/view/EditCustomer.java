package view;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import connection.DBConnection;
import controller.AddCustomer;
import controller.DataYearId;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditCustomer extends JDialog {
	private JTable table;
	int dataYearId = DataYearId.getDataYearId();
	String preLastName;
	String preName;

	public EditCustomer(String preLastName, String preName) {
		this.preLastName = preLastName;
		this.preName = preName;

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] { "\u0622\u062F\u0631\u0633",
				"\u0627\u0633\u0645 \u067E\u062F\u0631",
				"\u0634\u0647\u0631\u062A", "\u0627\u0633\u0645" }));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);

		table.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CustomersList.CancelButton);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Cancel.png")));
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
		});

		JButton button = new JButton(CustomersList.ok);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);
		button.addActionListener(new controller.EditCustomer(table, this,
				preLastName, preName));
		load(CustomersList.table);
		setSize(485, 120);
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setTitle(CustomersList.EditCustomer);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);
	}

	private void load(JTable customer) {

		int selectedrow = customer.getSelectedRow();
		String name = customer.getValueAt(selectedrow, 6).toString();
		String lastName = customer.getValueAt(selectedrow, 5).toString();
		String fatherName = customer.getValueAt(selectedrow, 4).toString();
		String editName;
		String editLastName;
		String editFatherName;
		String editAdress;

		((DefaultTableModel) table.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT `Name`,`Last_Name`,`Father_Name`,`Address` FROM `customer_list` WHERE Name = '"
							+ name
							+ "' And last_Name =  '"
							+ lastName
							+ "' And Father_Name = '"
							+ fatherName
							+ "' And dataYearid = " + dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				editName = result.getString(1);
				editLastName = result.getString(2);
				editFatherName = result.getString(3);
				editAdress = result.getString(4);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						editAdress, editFatherName, editLastName, editName });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// public static void main(String[] args) {
	// new EditCustomer();
	// }

}
