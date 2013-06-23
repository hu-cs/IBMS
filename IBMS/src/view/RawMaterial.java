package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;
import controller.DeleteRawItem;
import controller.PrintRawList;
import controller.RawComboboxListener;

public class RawMaterial extends JDialog {

	public static JTable table;
	private JTextField searchTextField;
	public static ArrayList<String> typeList = new ArrayList<String>();
	JLabel totalValueLabel;
	String dbTable = "raw_material";
	int dataYearId = DataYearId.getDataYearId();
	public static JComboBox<String> selectTypeCombo = new JComboBox<String>();

	public RawMaterial() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		selectTypeCombo.addItem("همه");

		JPanel northenPanel = new JPanel();
		getContentPane().add(northenPanel, BorderLayout.NORTH);
		northenPanel.setLayout(new BorderLayout(0, 0));

		JPanel selectTypePanel = new JPanel();
		FlowLayout fl_selectTypePanel = (FlowLayout) selectTypePanel
				.getLayout();
		fl_selectTypePanel.setHgap(20);
		northenPanel.add(selectTypePanel, BorderLayout.EAST);

		selectTypePanel.add(selectTypeCombo);

		JLabel selectTypeLabel = new JLabel(CompanyUsed.selectText);
		selectTypeLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/data_chooser.png")));
		selectTypePanel.add(selectTypeLabel);

		JPanel addTypePanel = new JPanel();
		FlowLayout fl_addTypePanel = (FlowLayout) addTypePanel.getLayout();
		fl_addTypePanel.setHgap(20);
		northenPanel.add(addTypePanel, BorderLayout.WEST);

		JButton deleteTypeButton = new JButton();
		deleteTypeButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/delete.png")));
		deleteTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteRawTypeUi();

			}
		});
		addTypePanel.add(deleteTypeButton);

		JButton addTypeButton = new JButton();
		addTypeButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/add.png")));
		addTypeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new AddRawType();
			}
		});
		addTypePanel.add(addTypeButton);

		JLabel addTypeLabel = new JLabel(CompanyUsed.addtypeText);
		addTypePanel.add(addTypeLabel);

		JPanel searchPanel = new JPanel();
		FlowLayout fl_searchPanel = (FlowLayout) searchPanel.getLayout();
		fl_searchPanel.setHgap(10);
		fl_searchPanel.setAlignment(FlowLayout.RIGHT);
		northenPanel.add(searchPanel, BorderLayout.CENTER);

		searchTextField = new JTextField();
		searchPanel.add(searchTextField);
		searchTextField.setColumns(12);

		JLabel searchLabel = new JLabel(CompanyUsed.searchText);
		searchLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/search.png")));
		searchPanel.add(searchLabel);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null, null,
						null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A ",
						"\u0645\u0642\u062F\u0627\u0631",
						"\u0641\u06CC \u0648\u0627\u062D\u062F",
						"\u0648\u0627\u062D\u062F",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0646\u0648\u0639", "\u0631\u062F\u06CC\u0641" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false, false, false };

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
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(81);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(122);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(29);
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
				ColumnSpec.decode("131px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"), }));

		JLabel currencyLabel = new JLabel();
		currencyLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/af.png")));

		totalValuePanel.add(currencyLabel, "1, 2, right, center");

		totalValueLabel = new JLabel();
		totalValuePanel.add(totalValueLabel, "2, 2, center, fill");

		JLabel totalLabel = new JLabel(CompanyUsed.totalText);
		totalLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/total_plan_cost.png")));

		totalValuePanel.add(totalLabel, "4, 2, fill, center");

		JPanel buttonPanel = new JPanel();
		southenPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JPanel creationPanel = new JPanel();
		buttonPanel.add(creationPanel, BorderLayout.EAST);

		JButton editButton = new JButton(CompanyUsed.editText);
		editButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectTypeCombo.getSelectedIndex() == 0) {
					new JOptionPane().showMessageDialog(null,
							"please select a type!");

				} else if (table.getSelectedRow() == -1) {
					new JOptionPane().showMessageDialog(null,
							"please select a Row!");
				} else {

					new EditRawItem(table);
				}

			}
		});
		creationPanel.add(editButton);

		JButton deleteButton = new JButton(CompanyUsed.removeText);
		deleteButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));

		deleteButton
				.addActionListener(new DeleteRawItem(table, selectTypeCombo));
		creationPanel.add(deleteButton);

		JButton addButton = new JButton(CompanyUsed.addText);
		addButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (selectTypeCombo.getSelectedIndex() == 0) {

					new JOptionPane().showMessageDialog(null, "select a type");
				} else {
					new AddRawMaterial();
				}

			}
		});

		creationPanel.add(addButton);

		JButton okButton = new JButton(CompanyUsed.okText);
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));

		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectTypeCombo = null;
				selectTypeCombo = new JComboBox<String>();
				dispose();
			}
		});
		creationPanel.add(okButton);

		JPanel printPanel = new JPanel();
		FlowLayout fl_printPanel = (FlowLayout) printPanel.getLayout();
		fl_printPanel.setHgap(10);
		buttonPanel.add(printPanel, BorderLayout.WEST);

		JButton printButton = new JButton(CompanyUsed.printText);
		printButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/print.png")));

		printButton.addActionListener(new PrintRawList(table, selectTypeCombo));
		printPanel.add(printButton);

		selectTypeCombo.addActionListener(new RawComboboxListener(table,
				totalValueLabel, selectTypeCombo));
		updateCombobox(selectTypeCombo);
		setTableValues();
		setTotalCost();
		searchTextField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();
				setTotalCost();

			}
		});
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setModalityType(ModalityType.APPLICATION_MODAL);
		setMinimumSize(new Dimension(900, 346));
		setSize(980, 450);
		setTitle("Raw material");
		setLocationRelativeTo(null);
		setVisible(true);
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
		new RawMaterial();
	}

	private void updateCombobox(JComboBox<String> typeCombo) {

		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT distinct Title FROM " + dbTable
							+ " where datayearid = " + dataYearId);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				typeCombo.addItem(type);
				typeList.add(type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void setTableValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String invoiceNumber;
		float totalCost;
		String dateAndTime;
		String selectedTitle;
		float cost;
		float qty;
		String unit;

		System.err.println("it is done");

		System.err.println(dataYearId);

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select title,invoice_number,quantity,unit,cost,Invoice_Cost,dateAndTime from raw_material where Invoice_Cost>0 and datayearid="
							+ dataYearId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				selectedTitle = result.getString(1);
				invoiceNumber = result.getString(2);
				qty = result.getFloat(3);
				unit = result.getString(4);
				cost = result.getFloat(5);
				totalCost = result.getFloat(6);
				dateAndTime = result.getString(7);
				System.err.println(totalCost);

				System.out.println(invoiceNumber);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						dateAndTime, totalCost, qty, cost, unit, invoiceNumber,
						selectedTitle, table.getRowCount() + 1 });

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
				searchTextField.getText(), 5);

		sorter.setRowFilter(rowFilter);

	}

}
