package view;

import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import javax.swing.JButton;

import model.Material;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import connection.DBConnection;
import controller.AddingNewMaterial;
import controller.Confirm;
import controller.DataYearId;
import controller.DeleteConfirmBtn;
import controller.DeletingMaterial;
import controller.PrintList;
import model.Material;

public class MaterialList extends JDialog {
	static ResourceBundle bundle = ResourceBundle
			.getBundle("bundle.MaterialBundle");
	public static String ok = bundle.getString("ok");
	public static String cancel = bundle.getString("cancel");
	public static String addMaterial = bundle.getString("addMaterial");
	public static String deleteMaterial = bundle.getString("deleteMaterial");
	public static String editMaterial = bundle.getString("editMaterial");
	public static String print = bundle.getString("print");
	public static String search = bundle.getString("search");
	public static String askForDelete = bundle.getString("askForDelete");
	public static String enterNumber = bundle.getString("enterNumber");
	public static String enterCorrect = bundle.getString("enterCorrect");
	// private static final ActionListener = null;
	public static JTable table;

	JTextField textField;

	public static void update() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String name;
		String unit;
		float finishedCost;
		float sellCost;
		int datayearid = DataYearId.getDataYearId();

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name, Unit, FinishedCost, SellCost from materiallist where DataYearId="
							+ datayearid);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				finishedCost = result.getFloat(3);
				sellCost = result.getFloat(4);

				Material materialObject = new Material(name, unit,
						finishedCost, sellCost);
				Material.materialArray.add(materialObject);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						sellCost, finishedCost, unit, name,
						table.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public MaterialList() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

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

		table.getTableHeader().setReorderingAllowed(false);

		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(130);
		table.getColumnModel().getColumn(4).setMaxWidth(65);
		table.getColumnModel().getColumn(4).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setMinWidth(40);
		// table.getColumnModel().getColumn(columnIndex)

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
		panel.add(panel_1, BorderLayout.EAST);

		JButton editBtn = new JButton(editMaterial);
		editBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {

					UIManager.put("OptionPane.okButtonText", "تایید");
					JOptionPane
							.showMessageDialog(
									null,
									"جهت ویراش نمودن محصـــول لطفـــــــــآ یک ردیف را انتخاب نمایید ! ",
									" هشدار  ", JOptionPane.OK_OPTION);
					// new JOptionPane().showMessageDialog(null,
					// "select a row");
				} else {
					new EditingMaterial(table, table.getValueAt(
							table.getSelectedRow(), 3).toString());
				}

			}
		});
		panel_1.add(editBtn);

		JButton deleteBtn = new JButton(deleteMaterial);
		deleteBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));
		deleteBtn.addActionListener(new DeletingMaterial(table));
		panel_1.add(deleteBtn);

		JButton addBtn = new JButton(addMaterial);
		addBtn.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new view.AddingNewMaterial();

			}
		});
		panel_1.add(addBtn);

		JButton okBtn = new JButton(ok);
		okBtn.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		panel_1.add(okBtn);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.WEST);

		JButton printBtn = new JButton(print);
		printBtn.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));
		printBtn.addActionListener(new PrintList(table));
		panel_2.add(printBtn);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(12);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_3, BorderLayout.NORTH);

		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(12);
		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent arg0) {

				rowFiltering();
			}
		});

		JLabel lblSearch = new JLabel(search);
		lblSearch.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/search.png")));
		panel_3.add(lblSearch);

		update();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setMinimumSize(new Dimension(600, 450));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(940, 450);
		setLocationRelativeTo(null);
		setTitle("لیست محصولات شرکت");
		setVisible(true);
		// setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new MaterialList();
	}

	private void rowFiltering() {
		// RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
		// tableModel);
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);

		RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter(
				textField.getText(), 3);

		sorter.setRowFilter(rowFilter);

	}

}
