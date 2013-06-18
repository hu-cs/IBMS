package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.CompoBoxActionListener;
import controller.DeleteUsedItem;
import controller.PrintExpenceList;

public class CompanyConsumption extends JFrame {

	ResourceBundle bundle = ResourceBundle.getBundle("bundle.ExpenceText");

	String okText = bundle.getString("ok");
	String addText = bundle.getString("add");
	String editText = bundle.getString("edit");
	String removeText = bundle.getString("remove");
	String totalText = bundle.getString("total");
	String printText = bundle.getString("print");
	String selectText = bundle.getString("selectType");
	String searchText = bundle.getString("search");
	String addtypeText = bundle.getString("addType");
	String selectTypeForEdit = bundle.getString("eidtingSelectRow");
	String selectRowForEdit = bundle.getString("editingSelectType");
	String selectTypeForAdding = bundle.getString("addingSelectType");

	public static JTable table;
	TableModel tableModel = new DefaultTableModel(new Object[][] { { null,
			null, null, null, null }, }, new String[] {
			"\u062A\u0627\u0631\u06CC\u062E", "\u0645\u0628\u0644\u063A ",
			"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636",
			"\u0646\u0648\u0639", "\u0631\u062F\u06CC\u0641" }) {
		boolean[] columnEditables = new boolean[] { false, false, false, false,
				false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
	private JTextField textField;
	JLabel totalValueLabel;
	public static final JComboBox<String> selectTypeCombo = new JComboBox<String>();
	public static ArrayList<String> typeList = new ArrayList<String>();
	String dbTable = "companyconsumptions";

	public CompanyConsumption() {
		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		
		typeList.add(" ");

		JPanel northenPanel = new JPanel();
		getContentPane().add(northenPanel, BorderLayout.NORTH);
		northenPanel.setLayout(new BorderLayout(0, 0));

		JPanel selectTypePanel = new JPanel();
		FlowLayout fl_selectTypePanel = (FlowLayout) selectTypePanel
				.getLayout();
		fl_selectTypePanel.setHgap(10);
		northenPanel.add(selectTypePanel, BorderLayout.EAST);

		selectTypeCombo.addItem("همه");
		selectTypePanel.add(selectTypeCombo);

		JLabel selectTypeLabel = new JLabel(selectText);
		selectTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectTypeLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/data_chooser.png")));
		selectTypePanel.add(selectTypeLabel);

		JPanel addTypePanel = new JPanel();
		FlowLayout fl_addTypePanel = (FlowLayout) addTypePanel.getLayout();
		fl_addTypePanel.setVgap(9);
		fl_addTypePanel.setHgap(20);
		northenPanel.add(addTypePanel, BorderLayout.WEST);

		JButton addTypeButton = new JButton("");
		addTypeButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/add.png")));
		addTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new AddConsumptionTypeUi();
			}
		});

		JButton deleteTypeButton = new JButton("");
		deleteTypeButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/delete.png")));
		deleteTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new DeleteConsumptionTypeUi();
			}
		});

		addTypePanel.add(deleteTypeButton);
		addTypePanel.add(addTypeButton);

		JLabel addTypeLabel = new JLabel(addtypeText);
		addTypePanel.add(addTypeLabel);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		northenPanel.add(panel, BorderLayout.CENTER);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(12);

		JLabel label = new JLabel(searchText);
		label.setIcon(new ImageIcon(getClass().getResource("/Icon/search.png")));
		panel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A ",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0646\u0648\u0639", "\u0631\u062F\u06CC\u0641" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(81);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(122);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(39);
		table.getColumnModel().getColumn(4).setMaxWidth(50);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});
		scrollPane.setViewportView(table);

		JPanel southenPanel = new JPanel();
		southenPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		getContentPane().add(southenPanel, BorderLayout.SOUTH);
		southenPanel.setLayout(new BorderLayout(0, 0));

		JPanel totalValuePanel = new JPanel();
		totalValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		southenPanel.add(totalValuePanel, BorderLayout.NORTH);
		totalValuePanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("68px"), ColumnSpec.decode("162px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("128px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("84px"), }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC,
						RowSpec.decode("max(20dlu;default)"),
						RowSpec.decode("2px"), }));

		JLabel currencyLabel = new JLabel("");
		currencyLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/af.png")));
		totalValuePanel.add(currencyLabel, "1, 2, right, center");

		totalValueLabel = new JLabel("0");
		totalValuePanel.add(totalValueLabel, "2, 2, center, fill");

		JLabel totalLabel = new JLabel(totalText);
		totalLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/total_plan_cost.png")));
		totalValuePanel.add(totalLabel, "4, 2, fill, fill");

		JPanel buttonPanel = new JPanel();
		southenPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JPanel creationPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) creationPanel.getLayout();
		buttonPanel.add(creationPanel, BorderLayout.EAST);

		JButton editButton = new JButton(CompanyUsed.editText);
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectTypeCombo.getSelectedIndex() == 0) {
					new JOptionPane().showMessageDialog(null, selectRowForEdit);
				} else if (table.getSelectedRow() == -1) {
					new JOptionPane()
							.showMessageDialog(null, selectTypeForEdit);
				} else {
					new EditConsumptionItem(table, selectTypeCombo
							.getSelectedItem().toString(), table.getValueAt(
							table.getSelectedRow(), 2).toString());
				}

			}
		});

		editButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		creationPanel.add(editButton);

		JButton deleteButton = new JButton(removeText);
		deleteButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));
		deleteButton.addActionListener(new DeleteUsedItem(table,
				selectTypeCombo, dbTable));
		creationPanel.add(deleteButton);

		JButton addButton = new JButton(addText);
		addButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectTypeCombo.getSelectedIndex() == 0) {
					new JOptionPane().showMessageDialog(null,
							selectTypeForAdding);
				} else {

					new CompanyConsumptionCreation();
				}

			}
		});
		creationPanel.add(addButton);

		JButton okButton = new JButton(okText);
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		creationPanel.add(okButton);

		JPanel printPanel = new JPanel();
		FlowLayout fl_printPanel = (FlowLayout) printPanel.getLayout();
		fl_printPanel.setHgap(10);
		buttonPanel.add(printPanel, BorderLayout.WEST);

		JButton printButton = new JButton(printText);
		printButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/print.png")));
		// Calling methods

		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();
				setTotalCost();

			}
		});

		selectTypeCombo.addActionListener(new CompoBoxActionListener(
				selectTypeCombo, table, totalValueLabel, dbTable));

		setIconImage(new ImageIcon(getClass().getResource("/Icon/flower.png"))
				.getImage());

		printPanel.add(printButton);
		updateCombobox(selectTypeCombo);

		setTableValues();
		setTotalCost();

		setMinimumSize(new Dimension(721, 346));
		setSize(930, 450);
		setTitle("مصارف شرکت");
		setLocationRelativeTo(null);
		setVisible(true);

		printButton.addActionListener(new PrintExpenceList(table,
				selectTypeCombo, "لیست مصارف شرکت "));

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {

		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		new CompanyConsumption();
	}

	private void updateCombobox(JComboBox<String> typeCombo) {

		System.err.println("update combobox");
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM `companyconsumptions`");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				typeCombo.addItem(type);
				typeList.add(type);
				System.out.println("the types are  " + type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setTableValues() {
		System.err.println("set table values");
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `companyconsumptions` WHERE Invoice_Cost>0");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getString(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);
				System.out.println("invoice number =" + invoiceNumber);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						dateAndTime, invoiceCost, invoiceNumber, selectedTitle,
						table.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setTotalCost() {
		float totalCost = 0;
		for (int counter = 0; counter < table.getRowCount(); counter++) {
			totalCost += Float.parseFloat(table.getValueAt(counter, 1)
					.toString());
		}

		totalValueLabel.setText(totalCost + "");

	}

	private void rowFiltering() {
		// RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
		// tableModel);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);

		RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter(
				textField.getText(), 2);

		sorter.setRowFilter(rowFilter);

	}

}
