package view;

import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import javax.swing.JButton;

import controller.AllCustomer;
import controller.CustomerDetailsListner;
import controller.CustomerPaid;
import controller.DataYearId;
import controller.DeleteCustomer;
import controller.DeptCustomer;
import controller.PrintCustomer;
//import model.SELECT;
//import model.from;
import connection.DBConnection;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;

import javax.swing.LayoutStyle.ComponentPlacement;

//import model.SELECT;
//import model.from;

public class CustomersList extends JDialog {
	public static JTable table;

	public static String customerName;
	public static String customerLastName;
	JTextField textField;
	int selectRow = 6;
	static JLabel label_2;
	static JLabel label_3;
	static JLabel label_4;

	static ResourceBundle bundle = ResourceBundle
			.getBundle("bundle.CustomerTextbundle");
	public static String CustomerList = bundle.getString("CustomerList");
	public static String ok = bundle.getString("ok");
	public static String No = bundle.getString("No");
	public static String Cancel = bundle.getString("Cancel");
	public static String CancelButton = bundle.getString("CancelButton");
	public static String yes = bundle.getString("Yes");
	public static String details = bundle.getString("details");
	public static String AddCustomer = bundle.getString("AddCustomer");
	public static String DeleteCustomer = bundle.getString("DeleteCustomer");
	public static String EditCustomer = bundle.getString("EditCustomer");
	public static String Print = bundle.getString("Print");
	public static String All = bundle.getString("All");
	public static String CustomerPaid = bundle.getString("CustomerPaid");
	public static String DeptCustomer = bundle.getString("DeptCustomer");
	public static String Search = bundle.getString("Search");
	public static String Name = bundle.getString("Name");
	public static String LastName = bundle.getString("LastName");
	public static String Total = bundle.getString("Total");
	public static String Information = bundle.getString("Information");
	public static String PleaseSelectOneRow = bundle
			.getString("PleaseSelectOneRow");
	public static String Payment = bundle.getString("Payment");
	public static String TotalPayment = bundle.getString("TotalPayment");
	public static String Giveing = bundle.getString("Giveing");
	public static String TotalGiveing = bundle.getString("TotalGiveing");
	public static String CompanyDemand = bundle.getString("CompanyDemand");
	public static String DeletePayment = bundle.getString("DeletePayment");
	public static String EditPayment = bundle.getString("EditPayment");
	public static String AddToPaymentAccount = bundle
			.getString("AddToPaymentAccount");
	public static String SeeInvoice = bundle.getString("SeeInvoice");
	public static String AddNewCustomer = bundle.getString("AddNewCustomer");
	public static String Date = bundle.getString("Date");
	public static String Cost = bundle.getString("Cost");
	public static String EditCusotmerAccount = bundle
			.getString("EditCusotmerAccount");
	public static String SelectMaterial = bundle.getString("SelectMaterial");
	public static String PrintInvoice = bundle.getString("PrintInvoice");
	public static String Adress = bundle.getString("Adress");
	public static String PhoneNumber = bundle.getString("PhoneNumber");
	public static String Fax = bundle.getString("Fax");
	public static String WebSite = bundle.getString("WebSite");
	public static String Email = bundle.getString("Email");
	public static String InvoiceNumber = bundle.getString("InvoiceNumber");
	public static String Customer = bundle.getString("Customer");
	public static String CustomerRegard = bundle.getString("CustomerRegard");
	public static String PleaseInsertCorrectInformation = bundle
			.getString("PleaseInsertCorrectInformation");
	public static String PleaseEnterAllInformation = bundle
			.getString("PleaseEnterAllInformation");
	public static String IfyouDeleteCustomerFromListItwillBeDeleteAllInformation = bundle
			.getString("IfyouDeleteCustomerFromListItwillBeDeleteAllInformation");
	public static String Alert = bundle.getString("Alert");
	public static String AreYouSure = bundle.getString("AreYouSure");
	public static String Afghani = bundle.getString("Afghani");
	public static String PlaceToStomp = bundle.getString("PlaceToStomp");
	public static String PlaceToAssignOrStomp = bundle
			.getString("PlaceToAssignOrStomp");
	public static String CustomersList = bundle.getString("CustomersList");
	public static String AtYear = bundle.getString("AtYear");
	public static String Hour = bundle.getString("Hour");
	public static String Page = bundle.getString("Page");

	public CustomersList() {

		UIManager.put("OptionPane.okButtonText", ok);
		UIManager.put("OptionPane.noButtonText", No);
		UIManager.put("OptionPane.cancelButtonText", Cancel);
		UIManager.put("OptionPane.yesButtonText", yes);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null, null, null, null, null }, }, new String[] {
				"\u0637\u0644\u0628 \u0634\u0631\u06A9\u062A",
				"\u06A9\u0644 \u0631\u0633\u06CC\u062F",
				"\u06A9\u0644 \u0628\u0631\u062F\u06AF\u06CC",
				"\u0622\u062F\u0631\u0633",
				"\u0627\u0633\u0645 \u067E\u062F\u0631",
				"\u062A\u062E\u0644\u0635", "\u0627\u0633\u0645",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(195);
		table.getColumnModel().getColumn(4).setPreferredWidth(86);
		table.getColumnModel().getColumn(5).setPreferredWidth(96);
		table.getColumnModel().getColumn(6).setPreferredWidth(94);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(46);

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
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		JLabel label_1 = new JLabel(Total);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);

		label_2 = new JLabel("0");

		label_3 = new JLabel("0");

		label_4 = new JLabel("0");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING).addGroup(
				Alignment.TRAILING,
				gl_panel_1
						.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 85,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 81,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 77,
								GroupLayout.PREFERRED_SIZE).addGap(180)
						.addComponent(label_1).addGap(94)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel_1
								.createSequentialGroup()
								.addGroup(
										gl_panel_1
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(label_1)
												.addComponent(label_2)
												.addComponent(label_4)
												.addComponent(label_3))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_2.add(panel_6, BorderLayout.EAST);

		JButton button_5 = new JButton(EditCustomer);
		button_5.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		panel_6.add(button_5);
		button_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, PleaseSelectOneRow,
							Information, JOptionPane.INFORMATION_MESSAGE);
				} else {

					new EditCustomer(table
							.getValueAt(table.getSelectedRow(), 5).toString(),
							table.getValueAt(table.getSelectedRow(), 6)
									.toString());
				}
			}
		});

		JButton button = new JButton(DeleteCustomer);

		button.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));
		panel_1.add(button);
		button.addActionListener(new DeleteCustomer(table));

		panel_6.add(button);

		JButton button_1 = new JButton(AddCustomer);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));
		panel_1.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new CustomerCreation();
			}
		});

		panel_6.add(button_1);

		JButton button_2 = new JButton(details);
		button_2.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/file_info16.png")));

		panel_1.add(button_2);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() == -1) {

					JOptionPane.showMessageDialog(null, PleaseSelectOneRow,
							Information, JOptionPane.INFORMATION_MESSAGE);

				} else {

					customerName = table.getValueAt(table.getSelectedRow(), 6)
							.toString();
					customerLastName = table.getValueAt(table.getSelectedRow(),
							5).toString();
					new CustomerDetails(table.getValueAt(
							table.getSelectedRow(), 6).toString(), table
							.getValueAt(table.getSelectedRow(), 5).toString());

				}
			}
		});

		panel_6.add(button_2);

		JButton button_3 = new JButton(ok);

		button_3.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));

		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				dispose();
			}
		});

		panel_6.add(button_3);

		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_7.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_2.add(panel_7, BorderLayout.WEST);

		JButton button_4 = new JButton(Print);
		button_4.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));
		panel_7.add(button_4);

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.WEST);

		JToggleButton toggleButtonDept = new JToggleButton(DeptCustomer);
		toggleButtonDept.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/group_delete.png")));
		panel_4.add(toggleButtonDept);

		toggleButtonDept.addActionListener(new DeptCustomer());

		JToggleButton toggleButtonPaid = new JToggleButton(CustomerPaid);
		toggleButtonPaid.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/group_add.png")));
		panel_4.add(toggleButtonPaid);
		toggleButtonPaid.addActionListener(new CustomerPaid());

		JToggleButton toggleButtonAll = new JToggleButton(All);
		toggleButtonAll.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/group.png")));
		toggleButtonAll.setSelected(true);
		panel_4.add(toggleButtonAll);
		toggleButtonAll.addActionListener(new AllCustomer());

		ButtonGroup taglButton = new ButtonGroup();
		taglButton.add(toggleButtonDept);
		taglButton.add(toggleButtonPaid);
		taglButton.add(toggleButtonAll);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5, BorderLayout.EAST);

		textField = new JTextField();
		textField.setColumns(10);
		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();
				setTotalCompanyDemand();
				setTotalResive();
				setTotalShopping();
			}
		});

		button_4.addActionListener(new PrintCustomer(table, toggleButtonAll,
				toggleButtonDept, toggleButtonPaid));

		panel_5.add(textField);

		final JComboBox<String> searchCombobox = new JComboBox<String>();
		searchCombobox.addItem(Name);
		searchCombobox.addItem(LastName);
		searchCombobox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (searchCombobox.getSelectedIndex() == 0) {
					selectRow = 6;
				} else {
					selectRow = 5;
				}

			}
		});
		panel_5.add(searchCombobox);

		JLabel label = new JLabel(Search);
		label.setIcon(new ImageIcon(getClass().getResource("/Icon/search.png")));

		panel_5.add(label);
		load();
		setTotalCompanyDemand();
		setTotalResive();
		setTotalShopping();

		
		setSize(800, 600);
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setTitle(CustomerList);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		// setResizable(false);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
	}

	private void load() {
		// TODO Auto-generated method stub
		int dataYearId = DataYearId.getDataYearId();
		String name;
		String lastName;
		String fatherName;
		String address;
		float shopping;
		float payment;
		float companyDemand;

		((DefaultTableModel) table.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name,Last_Name,Father_Name,Address,Shopping_Account,Giving_Money_Account,Company_demand from customer_list where datayearid = "
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				lastName = result.getString(2);
				fatherName = result.getString(3);
				address = result.getString(4);
				shopping = result.getFloat(5);
				payment = result.getFloat(6);
				companyDemand = result.getFloat(7);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {

				companyDemand, payment, shopping, address, fatherName,
						lastName, name, table.getRowCount() + 1 });

			}

		} catch (SQLException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	public static void setTotalCompanyDemand() {

		float total = 0;

		for (int counter = 0; counter < table.getRowCount(); counter++) {

			total += Float.parseFloat(table.getValueAt(counter, 0).toString());
		}
		label_4.setText(total + "");
	}

	public static void setTotalResive() {

		float total = 0;

		for (int counter = 0; counter < table.getRowCount(); counter++) {

			total += Float.parseFloat(table.getValueAt(counter, 1).toString());
		}
		label_3.setText(total + "");
	}

	public static void setTotalShopping() {
		float total = 0;

		for (int counter = 0; counter < table.getRowCount(); counter++) {

			total += Float.parseFloat(table.getValueAt(counter, 2).toString());
		}
		label_2.setText(total + ""); // TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new CustomersList();

	}

	private void rowFiltering() {

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);

		RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter(
				textField.getText(), selectRow);

		sorter.setRowFilter(rowFilter);

	}
}
