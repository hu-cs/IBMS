package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class mainUi extends JFrame {

	JTable table;

	public mainUi() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setJMenuBar(menuBar);

		JMenu menu_1 = new JMenu("انتخاب حساب");
		menu_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menu_1);

		JMenuItem menuItem_7 = new JMenuItem("ایجاد حساب سال جدید");
		menuItem_7.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu_1.add(menuItem_7);

		JMenuItem menuItem_6 = new JMenuItem("حسابات سال 2013 (پیش فرض)");
		menuItem_6.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu_1.add(menuItem_6);

		JMenu stockMenu = new JMenu(
				"\u062D\u0633\u0627\u0628 \u0627\u0646\u0628\u0627\u0631 \u0648 \u0627\u062C\u0646\u0627\u0633");
		stockMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
		stockMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(stockMenu);

		JMenuItem materialListMenuItem = new JMenuItem(
				"\u0644\u06CC\u0633\u062A \u06A9\u0644 \u0627\u062C\u0646\u0627\u0633");
		materialListMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(materialListMenuItem);

		JSeparator separator_12 = new JSeparator();
		stockMenu.add(separator_12);

		JMenuItem menuItem_8 = new JMenuItem("فاکتور فروش");
		menuItem_8.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(menuItem_8);

		JSeparator separator = new JSeparator();
		stockMenu.add(separator);

		JMenuItem existingMaterialInStockMenuItem = new JMenuItem(
				"\u0645\u0648\u062C\u0648\u062F\u06CC \u0627\u062C\u0646\u0627\u0633 \u0627\u0646\u0628\u0627\u0631");
		existingMaterialInStockMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(existingMaterialInStockMenuItem);

		JSeparator separator_1 = new JSeparator();
		stockMenu.add(separator_1);

		JMenuItem sellingListMenuItem = new JMenuItem(
				"\u0644\u06CC\u0633\u062A \u0641\u0631\u0648\u0634\u0627\u062A(\u0631\u0628\u0639\u0648\u0627\u0631)");
		sellingListMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(sellingListMenuItem);

		JSeparator separator_10 = new JSeparator();
		stockMenu.add(separator_10);

		JMenuItem menuItem_4 = new JMenuItem("اضافه نمودن جنس جدید");
		menuItem_4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(menuItem_4);

		JSeparator separator_11 = new JSeparator();
		stockMenu.add(separator_11);

		JMenuItem menuItem_5 = new JMenuItem("پاک کردن جنس");
		menuItem_5.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(menuItem_5);

		JMenu customersAccountMenu = new JMenu(
				"\u062D\u0633\u0627\u0628 \u0645\u0634\u062A\u0631\u06CC\u0627\u0646");
		customersAccountMenu
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(customersAccountMenu);

		JMenuItem customersListMenuItem = new JMenuItem(
				"\u0644\u06CC\u0633\u062A \u0645\u0634\u062A\u0631\u06CC\u0627\u0646");
		customersListMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		customersAccountMenu.add(customersListMenuItem);

		JSeparator separator_2 = new JSeparator();
		customersAccountMenu.add(separator_2);

		JMenuItem customerAccountLookingMenuItem = new JMenuItem(
				"\u062F\u06CC\u062F\u0646 \u062D\u0633\u0627\u0628 \u0645\u0634\u062A\u0631\u06CC");
		customerAccountLookingMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		customersAccountMenu.add(customerAccountLookingMenuItem);

		JSeparator separator_3 = new JSeparator();
		customersAccountMenu.add(separator_3);

		JMenuItem addingNewCustomerMenuItem = new JMenuItem(
				"\u0627\u0636\u0627\u0641\u0647 \u0646\u0645\u0648\u062F\u0646 \u0645\u0634\u062A\u0631\u06CC \u062C\u062F\u06CC\u062F");
		addingNewCustomerMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		customersAccountMenu.add(addingNewCustomerMenuItem);

		JSeparator separator_4 = new JSeparator();
		customersAccountMenu.add(separator_4);

		JMenuItem removeCustomerMenuItem = new JMenuItem(
				"\u067E\u0627\u06A9 \u06A9\u0631\u062F\u0646 \u0645\u0634\u062A\u0631\u06CC");
		removeCustomerMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		customersAccountMenu.add(removeCustomerMenuItem);

		JMenu compantExpenditureMenu = new JMenu("حساب مصارف");
		compantExpenditureMenu
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(compantExpenditureMenu);

		JMenuItem buyingRawMaterialMenuItem = new JMenuItem(
				"\u062E\u0631\u06CC\u062F \u0645\u0648\u0627\u062F \u0627\u0648\u0644\u06CC\u0647");
		buyingRawMaterialMenuItem
				.setHorizontalTextPosition(SwingConstants.RIGHT);
		buyingRawMaterialMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(buyingRawMaterialMenuItem);

		JSeparator separator_5 = new JSeparator();
		compantExpenditureMenu.add(separator_5);

		JMenuItem buyingNeededMaterialMenuItem = new JMenuItem(
				"\u062E\u0631\u06CC\u062F \u0644\u0648\u0627\u0632\u0645 \u0645\u0648\u0631\u062F \u0646\u06CC\u0627\u0632");
		buyingNeededMaterialMenuItem
				.setHorizontalTextPosition(SwingConstants.RIGHT);
		buyingNeededMaterialMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(buyingNeededMaterialMenuItem);

		JSeparator separator_6 = new JSeparator();
		compantExpenditureMenu.add(separator_6);

		JMenu corruprionExpenditureMenu = new JMenu("مصارف استهلاک");
		corruprionExpenditureMenu
				.setHorizontalTextPosition(SwingConstants.RIGHT);
		corruprionExpenditureMenu
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(corruprionExpenditureMenu);

		JMenuItem machinesMenuItem = new JMenuItem("ماشین آلات");
		machinesMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		machinesMenuItem.setHorizontalTextPosition(SwingConstants.RIGHT);
		corruprionExpenditureMenu.add(machinesMenuItem);

		JSeparator separator_7 = new JSeparator();
		corruprionExpenditureMenu.add(separator_7);

		JMenuItem gardenMenuItem = new JMenuItem("محوطه");
		gardenMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		gardenMenuItem.setHorizontalTextPosition(SwingConstants.RIGHT);
		corruprionExpenditureMenu.add(gardenMenuItem);

		JSeparator separator_8 = new JSeparator();
		corruprionExpenditureMenu.add(separator_8);

		JMenuItem buildingMenuItem = new JMenuItem("ساختمانی");
		buildingMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		corruprionExpenditureMenu.add(buildingMenuItem);

		JSeparator separator_9 = new JSeparator();
		corruprionExpenditureMenu.add(separator_9);

		JMenuItem variuosMenuItem = new JMenuItem("متفرقه");
		variuosMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		corruprionExpenditureMenu.add(variuosMenuItem);

		JSeparator separator_13 = new JSeparator();
		compantExpenditureMenu.add(separator_13);

		JMenuItem companyExpenditureMenuItem = new JMenuItem(
				"مصارف شرکت(آب،برق،گاز و غیره)");
		companyExpenditureMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(companyExpenditureMenuItem);

		JSeparator separator_14 = new JSeparator();
		compantExpenditureMenu.add(separator_14);

		JMenuItem addedMaterialMenuItem = new JMenuItem("موارد اضافه شده");
		addedMaterialMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(addedMaterialMenuItem);

		// =================================================
		JPanel mainPanel = new JPanel(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, },
				new String[] {
						"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634",
						"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
						"\u0648\u0627\u062D\u062F \u067E\u06CC\u0645\u0627\u06CC\u0634",
						"\u0646\u0627\u0645 \u062C\u0646\u0633",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(4).setPreferredWidth(52);

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
		mainPanel.add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("پاک کردن نوع جنس");
		panel.add(button_1);

		JButton button = new JButton("اضافه نمودن جنس جدید");
		panel.add(button);

		getContentPane().add(mainPanel);

		setSize(450, 367);
		setLocationRelativeTo(null);
		setTitle("حسابداری صنعتکاران");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new mainUi();
	}

}
