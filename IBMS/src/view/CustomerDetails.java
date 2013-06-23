package view;

import javax.swing.JDialog;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;

import java.awt.GridLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connection.DBConnection;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import controller.DataYearId;
import controller.DeletePayment;
import controller.EditeCustomerReciver;
import controller.PrintDetails;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.FlowLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDetails extends JDialog {

	public static JTable giveing;
	public static JTable table;
	public static JLabel label_5;
	public static JLabel label_2;
	public static JLabel label_7;
	String name;
	String lastName;

	public CustomerDetails(String name, String lastName) {

		this.name = name;
		this.lastName = lastName;

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel label_3 = new JLabel(CustomersList.Payment);
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_3, BorderLayout.NORTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);

		JSeparator seperator = new JSeparator(JSeparator.VERTICAL);
		panel_3.add(seperator, BorderLayout.EAST);

		giveing = new JTable();
		giveing.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0628\u0644\u063A", "\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		giveing.getTableHeader().setReorderingAllowed(false);
		giveing.getColumnModel().getColumn(0).setResizable(false);
		giveing.getColumnModel().getColumn(2).setResizable(false);
		giveing.getColumnModel().getColumn(2).setPreferredWidth(44);

		giveing.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});
		giveing.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		scrollPane_1.setViewportView(giveing);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("77px"), ColumnSpec.decode("93px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("64px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("7px"), }));

		label_5 = new JLabel("0");
		panel_5.add(label_5, "2, 2, center, top");

		JLabel label_4 = new JLabel(CustomersList.TotalPayment);
		panel_5.add(label_4, "4, 2, left, top");

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel(CustomersList.Giveing);
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		table.getColumnModel().getColumn(3).setPreferredWidth(53);
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("85px"), ColumnSpec.decode("93px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("69px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("7px"), }));

		label_2 = new JLabel("0");
		panel_2.add(label_2, "2, 2, center, top");

		JLabel label_1 = new JLabel(CustomersList.TotalGiveing);
		panel_2.add(label_1, "4, 2, left, top");

		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("278px"), ColumnSpec.decode("87px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("62px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"), }));

		label_7 = new JLabel("0");
		panel_7.add(label_7, "2, 2, center, top");

		JLabel label_6 = new JLabel(CustomersList.CompanyDemand);
		panel_7.add(label_6, "4, 2, center, top");

		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_8.add(panel_4, BorderLayout.EAST);

		JButton button_4 = new JButton(CustomersList.DeletePayment);
		button_4.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));
		panel_4.add(button_4);
		button_4.addActionListener(new DeletePayment(giveing));

		JButton button = new JButton(CustomersList.EditPayment);
		button.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		panel_4.add(button);
		button.addActionListener(new EditeCustomerReciver());

		JButton button_3 = new JButton(CustomersList.AddToPaymentAccount);
		button_3.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));

		panel_4.add(button_3);
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new CutomerMonyReception();
			}
		});

		JButton button_2 = new JButton(CustomersList.SeeInvoice);
		button_2.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_info16.png")));

		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null,
							CustomersList.PleaseSelectOneRow,
							CustomersList.Information,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int invoiceNumber = Integer.parseInt(table.getValueAt(
							table.getSelectedRow(), 2).toString());
					new LoadInvoice(invoiceNumber);
				}
			}
		});
		panel_4.add(button_2);

		JButton button_1 = new JButton(CustomersList.ok);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));
		panel_4.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				update();
				load(CustomersList.table);
				CustomersList.setTotalCompanyDemand();
				CustomersList.setTotalResive();
				CustomersList.setTotalShopping();
				CustomerPaidCheck();
				dispose();

			}

			private void load(JTable CustomerTable) {
				// TODO Auto-generated method stub

				String name;
				String lastName;
				String fatherName;
				String address;
				float shopping;
				float payment;
				float companyDemand;

				((DefaultTableModel) CustomerTable.getModel()).setRowCount(0);

				try {
					PreparedStatement statement = DBConnection.connection
							.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where datayearid = "
									+ DataYearId.getDataYearId());

					ResultSet result = statement.executeQuery();

					while (result.next()) {

						name = result.getString(1);
						lastName = result.getString(2);
						fatherName = result.getString(3);
						address = result.getString(4);
						shopping = result.getFloat(5);
						payment = result.getFloat(6);
						companyDemand = result.getFloat(7);

						((DefaultTableModel) CustomerTable.getModel())
								.addRow(new Object[] {

								companyDemand, payment, shopping, address,
										fatherName, lastName, name,
										CustomerTable.getRowCount() + 1 });

					}

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}

		});

		JPanel panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.WEST);

		JButton button_5 = new JButton(CustomersList.Print);
		button_5.addActionListener(new PrintDetails(table, giveing));
		button_5.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));
		panel_9.add(button_5);

		load(CustomersList.table);
		setTotal();

		loadShopping(CustomersList.table);
		setShoppingTotal();

		setCompanyDemand();

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());

		setSize(770, 600);
		setLocationRelativeTo(null);
		setTitle("حساب" + "  " + CustomersList.customerName + " "
				+ CustomersList.customerLastName);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	private void load(JTable customerTable) {
		// TODO Auto-generated method stub

		int SelectedRow = customerTable.getSelectedRow();
		String name = customerTable.getValueAt(SelectedRow, 6).toString();
		String lastName = customerTable.getValueAt(SelectedRow, 5).toString();

		String totalname = name + " " + lastName;

		float cost;
		String DateAndTime;

		((DefaultTableModel) giveing.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Cost,Date_And_Time from customer_giving_account  WHERE Name = '"
							+ totalname + "'");
			// + "' and Last_Name='" + lastName + "'"

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				cost = result.getInt(1);

				DateAndTime = result.getString(2);

				((DefaultTableModel) giveing.getModel()).addRow(new Object[] {

				DateAndTime, cost, giveing.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setTotal() {

		float total = 0;
		for (int conter = 0; conter < giveing.getRowCount(); conter++) {
			// if (giveing.getValueAt(0,0) == null)

			total += Float.parseFloat(giveing.getValueAt(conter, 1).toString());

		}

		label_5.setText(total + "");
	}

	private void loadShopping(JTable customerList) {
		// TODO Auto-generated method stub

		int SelectedRow = customerList.getSelectedRow();
		String name = customerList.getValueAt(SelectedRow, 6).toString();
		String last_name = customerList.getValueAt(SelectedRow, 5).toString();
		String customerName = name + " " + last_name;
		int InvoiceNumber;
		float InvoiceCost;
		String DateAndTime;

		((DefaultTableModel) table.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Invoice_Numebr,Invoice_Cost,Date_And_Time from customer_shopping_account WHERE Customer_Name = '"
							+ customerName + "'");

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				InvoiceNumber = result.getInt(1);
				InvoiceCost = result.getFloat(2);
				DateAndTime = result.getString(3);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {

				DateAndTime, InvoiceCost, InvoiceNumber,
						table.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setShoppingTotal() {

		float total = 0;
		for (int conter = 0; conter < table.getRowCount(); conter++) {
			// if (giveing.getValueAt(0,0) == null)

			total += Float.parseFloat(table.getValueAt(conter, 1).toString());

		}

		label_2.setText(total + "");
	}

	private void setCompanyDemand() {

		// TODO Auto-generated method stub

		float shopping = Float.parseFloat(label_2.getText().toString());
		float giveing = Float.parseFloat(label_5.getText().toString());

		float companyDemand = shopping - giveing;

		label_7.setText(companyDemand + "");

	}

	private void update() {
		// TODO Auto-generated method stub

		// String name = CustomersList.customerName;
		// String lastName = CustomersList.customerLastName;

		float shopping = Float.parseFloat(label_2.getText());
		float giveing = Float.parseFloat(label_5.getText());
		float companyDemand = Float.parseFloat(label_7.getText());

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("update  customer_list  set Shopping_Account = "
							+ shopping
							+ " , Giving_Money_Account = "
							+ giveing
							+ ", Company_demand = "
							+ companyDemand
							+ " where Name = '"
							+ name
							+ "' And Last_Name = '"
							+ lastName + "' ");
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	private void CustomerPaidCheck() {
		// TODO Auto-generated method stub
		float CompanyDemand = Float.parseFloat(label_7.getText());
		if (CompanyDemand > 0) {

			try {
				PreparedStatement statement = DBConnection.connection
						.prepareStatement("update  customer_list set `Check` = 0 where Name = '"
								+ name + "'And Last_Name = '" + lastName + "' ");
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement statement = DBConnection.connection
						.prepareStatement("update  customer_list set `Check` = 1 where Name = '"
								+ name + "'And Last_Name = '" + lastName + "' ");
				statement.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// new CustomerDetails();
	}

}
