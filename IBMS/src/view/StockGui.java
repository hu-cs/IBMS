package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
import model.StockMaterial;
import connection.DBConnection;
import controller.DataYearId;
import controller.PrintStock;
import controller.SoldDate;
import controller.StoredDate;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StockGui extends JDialog {
	public static JTable table;
	private JTextField textField;
	int datayearid = DataYearId.getDataYearId();

	public static ResourceBundle bundle = ResourceBundle
			.getBundle("bundle.StockText");
	public static String ok = bundle.getString("ok");
	public static String storeToStock = bundle.getString("StoreToStock");
	public static String SoldDate = bundle.getString("SoldDate");
	public static String StoreDate = bundle.getString("StoreDate");
	public static String PringList = bundle.getString("PringList");
	public static String Search = bundle.getString("search");
	public static String Title = bundle.getString("Title");
	public static String Selection = bundle.getString("Please select a row");
	public static String Information = bundle.getString("Information");

	public StockGui() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] { "\u0648\u0627\u062D\u062F",
				"\u0645\u0642\u062F\u0627\u0631",
				"\u0646\u0627\u0645 \u062C\u0646\u0633",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		scrollPane.setViewportView(table);
		// table.getColumnModel().getColumn(1).setCellEditor(new TableEditor());
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);

		JButton button_2 = new JButton(StoreDate);
		button_2.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_info16.png")));
		panel_1.add(button_2);
		StoredDate store = new StoredDate(table);
		button_2.addActionListener(store);

		JButton button_3 = new JButton(SoldDate);
		button_3.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Login_in16.png")));
		panel_1.add(button_3);
		SoldDate sold = new SoldDate(table);
		button_3.addActionListener(sold);

		JButton button_1 = new JButton(storeToStock);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (table.getSelectedRow() == -1)
					JOptionPane.showMessageDialog(null, Selection, Information,
							JOptionPane.INFORMATION_MESSAGE);
				else
					new storingToStock(table.getValueAt(table.getSelectedRow(),
							2).toString());

			}
		});

		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));

		panel_1.add(button_1);
		JButton button = new JButton(ok);
		panel_1.add(button);

		// button_2.setIcon(new ImageIcon(getClass().getResource(
		// "/Icon/Accept.png")));
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.WEST);

		JButton button_4 = new JButton(PringList);

		button_4.addActionListener(new PrintStock(table));
		panel_2.add(button_4);
		button_4.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));

		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();

			}
		});

		table.getTableHeader().setReorderingAllowed(false);

		JLabel label = new JLabel(Search);
		panel_3.add(label);
		label.setIcon(new ImageIcon(getClass().getResource("/Icon/search.png")));

		Update();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setMinimumSize(new Dimension(700, 450));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(900, 450);
		setLocationRelativeTo(null);
		setTitle(Title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		// setResizable(false);
	}

	private void Update() {

		String name;
		int quantity;
		String unit;
		StockMaterial loadedMaterial;

		((DefaultTableModel) table.getModel()).setRowCount(0);

		PreparedStatement statement;
		try {
			statement = DBConnection.connection
					.prepareStatement("SELECT Name, Quantity, Unit FROM  stock where datayearid = "
							+ datayearid + "");
			ResultSet result = statement.executeQuery();

			while (result.next()) {

				name = result.getString(1);
				quantity = result.getInt(2);
				unit = result.getString(3);

				loadedMaterial = new StockMaterial(name, unit, quantity);
				StockMaterial.stockList.add(loadedMaterial);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						unit, quantity, name, table.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void rowFiltering() {

		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				table.getModel());
		table.setRowSorter(sorter);

		RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter(
				textField.getText(), 2);

		sorter.setRowFilter(rowFilter);

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new StockGui();

	}

}
