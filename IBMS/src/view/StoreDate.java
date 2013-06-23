package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders.InternalFrameBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import connection.DBConnection;
import controller.DataYearId;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class StoreDate extends JDialog {

	public static JTable storeTable = new JTable();
	private String title;
	public static JLabel label_1;
	public static String edit = StockGui.bundle.getString("edit");
	public static String TotalStore = StockGui.bundle.getString("TotalStore");

	public StoreDate() {

		UIManager.put("OptionPane.okButtonText", "تایید");
		UIManager.put("OptionPane.cancelButtonText", "لعو");
		UIManager.put("OptionPane.yesButtonText", "بله");
		UIManager.put("OptionPane.noButtonText", "خیر");

		setResizable(false);
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// storeTable = new JTable();
		storeTable.setModel(new DefaultTableModel(new Object[][] { { null,
				null, null }, }, new String[] {
				"\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0642\u062F\u0627\u0631",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		storeTable.getColumnModel().getColumn(0).setPreferredWidth(149);
		storeTable.getColumnModel().getColumn(1).setPreferredWidth(98);
		storeTable.getColumnModel().getColumn(2).setPreferredWidth(47);
		scrollPane.setViewportView(storeTable);

		storeTable.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});
		storeTable.getTableHeader().setReorderingAllowed(false);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("114px"), ColumnSpec.decode("89px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("101px"), FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("6px"), }));

		label_1 = new JLabel("0");
		panel_1.add(label_1, "2, 2, center, center");
		JLabel label = new JLabel(TotalStore);
		panel_1.add(label, "4, 2, center, top");

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_2, BorderLayout.SOUTH);

		JButton button_1 = new JButton(edit);
		panel_2.add(button_1);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_edit.png")));

		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int selectedrow = storeTable.getSelectedRow();
				if (selectedrow == -1) {
					JOptionPane.showMessageDialog(null, StockGui.Selection,
							StockGui.Information,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					new EditGui();
				}

			}
		});

		JButton button = new JButton(StockGui.ok);
		panel_2.add(button);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
				load(StockGui.table);
				updateMainUiTable(MainUi.table);
				dispose();

			}

			int datayearid = DataYearId.getDataYearId();

			private void update() {

				int selectedRow = StockGui.table.getSelectedRow();
				String name = StockGui.table.getValueAt(selectedRow, 2)
						.toString();
				int quantity = Integer.parseInt(label_1.getText());

				// Getting the sell history quantity to be subtracted from stock
				// store history

				int soldQty = 0;

				try {
					PreparedStatement soldStatement = DBConnection.connection
							.prepareStatement("SELECT quantity FROM `sell_history` WHERE name = '"
									+ name
									+ "' and datayearid ="
									+ StoreDate.this.datayearid);
					ResultSet result = soldStatement.executeQuery();
					while (result.next()) {
						soldQty += result.getInt(1);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				quantity -= soldQty;

				try {
					PreparedStatement statement = DBConnection.connection
							.prepareStatement("update stock set Quantity= "
									+ quantity + " where Name = '" + name
									+ "' and datayearid = " + datayearid + " ");
					statement.execute();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			public void load(JTable stockTable) {

				String name;
				int quantity;
				String unit;

				((DefaultTableModel) stockTable.getModel()).setRowCount(0);

				PreparedStatement statement;
				try {
					statement = DBConnection.connection
							.prepareStatement("SELECT Name, Quantity, Unit FROM  stock where datayearid = "
									+ datayearid + " ");
					ResultSet result = statement.executeQuery();

					while (result.next()) {

						name = result.getString(1);
						quantity = result.getInt(2);
						unit = result.getString(3);

						((DefaultTableModel) stockTable.getModel())
								.addRow(new Object[] { unit, quantity, name,
										stockTable.getRowCount() + 1 });

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		load(StockGui.table);
		setTotal();
		setSize(450, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setResizable(false);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);

	}

	int datayearid = DataYearId.getDataYearId();

	private void load(JTable stockTable) {

		int selectedrow = stockTable.getSelectedRow();
		String name = stockTable.getValueAt(selectedrow, 2).toString();
		String quantity;
		String dateAndTime;
		((DefaultTableModel) this.storeTable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Quantity, Date_And_Time FROM store_history where Name = '"
							+ name + "' and datayearid = " + datayearid + " ");
			ResultSet resul = statement.executeQuery();
			while (resul.next()) {
				quantity = resul.getString(1);
				dateAndTime = resul.getString(2);
				((DefaultTableModel) this.storeTable.getModel())
						.addRow(new Object[] { dateAndTime, quantity,
								this.storeTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		title = StockGui.StoreDate + name;

	}

	private void setTotal() {

		int total = 0;
		for (int conter = 0; conter < storeTable.getRowCount(); conter++) {

			if (storeTable.getValueAt(0, 0) == null)
				return;
			total += Integer.parseInt(storeTable.getValueAt(conter, 1)
					.toString());
		}
		label_1.setText(total + "");

	}

	public static void main(String[] args) {
		new StoreDate();
	}

	private void updateMainUiTable(JTable mainTable) {
		((DefaultTableModel) mainTable.getModel()).setRowCount(0);
		String name;
		int quantity;
		String unit;

		try {
			java.sql.PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name, Quantity, Unit FROM  stock where datayearid = "
							+ datayearid);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				quantity = result.getInt(2);
				unit = result.getString(3);

				System.err.println("Stock qty  " + name + "  " + quantity
						+ "---" + unit);
				((DefaultTableModel) mainTable.getModel()).addRow(new Object[] {
						unit, quantity, name, mainTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
