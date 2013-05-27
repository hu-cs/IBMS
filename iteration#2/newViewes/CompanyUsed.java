package newViewes;

import java.awt.BorderLayout;
import javax.swing.UIManager.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.alee.laf.WebLookAndFeel;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jtattoo.plaf.JTattooUtilities;
import com.mysql.jdbc.PreparedStatement;

import controler.expenses.CompoBoxActionListener;
import controler.expenses.DeleteUsedItem;
import controller.ComboboxListener;

import dataBase.DBConnection;

public class CompanyUsed extends JFrame {
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
	public static final JComboBox<String> selectTypeCombo = new JComboBox<String>();;
	public static ArrayList<String> typeList = new ArrayList<String>();

	public CompanyUsed() {

		typeList.add(" ");

		JPanel northenPanel = new JPanel();
		getContentPane().add(northenPanel, BorderLayout.NORTH);
		northenPanel.setLayout(new BorderLayout(0, 0));

		JPanel selectTypePanel = new JPanel();
		FlowLayout fl_selectTypePanel = (FlowLayout) selectTypePanel
				.getLayout();
		fl_selectTypePanel.setHgap(20);
		northenPanel.add(selectTypePanel, BorderLayout.EAST);

		selectTypeCombo.addItem("همه");
		selectTypePanel.add(selectTypeCombo);

		JLabel selectTypeLabel = new JLabel("انتخاب نوع مصرف  ");
		selectTypePanel.add(selectTypeLabel);

		JPanel addTypePanel = new JPanel();
		FlowLayout fl_addTypePanel = (FlowLayout) addTypePanel.getLayout();
		fl_addTypePanel.setHgap(20);
		northenPanel.add(addTypePanel, BorderLayout.WEST);

		JButton addTypeButton = new JButton("+");
		addTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddExpenseTypeUi();

			}
		});

		JButton deleteTypeButton = new JButton("-");
		deleteTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteUsedTypeUi();

			}
		});
		addTypePanel.add(deleteTypeButton);
		addTypePanel.add(addTypeButton);

		JLabel addTypeLabel = new JLabel("ایجاد نوع مصرف");
		addTypePanel.add(addTypeLabel);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		northenPanel.add(panel, BorderLayout.CENTER);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(12);

		JLabel label = new JLabel("جستجوی فاکتور");
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(81);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(122);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(29);
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
		totalValuePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("68px"), ColumnSpec.decode("162px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("97px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"), }));

		JLabel currencyLabel = new JLabel("افغانی");
		totalValuePanel.add(currencyLabel, "1, 2, right, top");

		totalValueLabel = new JLabel("0");
		totalValuePanel.add(totalValueLabel, "2, 2, center, fill");

		JLabel totalLabel = new JLabel("کل مبلغ تادیه شده");
		totalValuePanel.add(totalLabel, "4, 2, fill, fill");

		JPanel buttonPanel = new JPanel();
		southenPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JPanel creationPanel = new JPanel();
		buttonPanel.add(creationPanel, BorderLayout.EAST);

		JButton editButton = new JButton("ویرایش مورد");
		creationPanel.add(editButton);

		JButton deleteButton = new JButton("پاک کردن مورد");
		deleteButton.addActionListener(new DeleteUsedItem(table));
		creationPanel.add(deleteButton);

		JButton addButton = new JButton("افزودن مورد");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectTypeCombo.getSelectedIndex() == 0) {
					new JOptionPane()
							.showMessageDialog(null,
									"برای افزودن مورد، لطفا بک نوع مصرف را انتخاب کنید.");
				} else {
					new ComExpCreation();
				}

			}
		});
		creationPanel.add(addButton);

		JButton okButton = new JButton("تایید");
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

		JButton printButton = new JButton("چاپ لیست");
		// Calling methods

		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();
				setTotalCost();

			}
		});
		printPanel.add(printButton);
		updateCombobox(selectTypeCombo);
		setTableValues();
		setTotalCost();
		selectTypeCombo.addActionListener(new CompoBoxActionListener(
				selectTypeCombo, table, totalValueLabel));

		setMinimumSize(new Dimension(721, 346));
		setSize(721, 346);
		setTitle("مصارف استهلاک");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new CompanyUsed();
	}

	private void updateCombobox(JComboBox<String> typeCombo) {

		// typeCombo = null;
		// typeCombo = new JComboBox<String>();
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM `corruptionconsumption`");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				typeCombo.addItem(type);
				typeList.add(type);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setTableValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		int invoiceNumber;
		float invoiceCost;
		String dateAndTime;

		String selectedTitle;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT title,Invoice_Number,Invoice_Cost,Date_And_Time FROM `corruptionconsumption` WHERE Invoice_Number>0");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getInt(2);
				invoiceCost = result.getFloat(3);
				dateAndTime = result.getString(4);

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
