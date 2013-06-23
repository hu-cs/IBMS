package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import javax.swing.JButton;

import connection.DBConnection;
import controller.DataYearId;
import controller.PrintSoldMateialList;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class SoldMaterilaList extends JDialog {
	private JTable table;
	private JTextField textField;

	public SoldMaterilaList() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"\u0645\u0642\u062F\u0627\u0631", "\u0648\u0627\u062D\u062F",
				"\u0646\u0627\u0645 \u062C\u0646\u0633",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { true, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(81);
		table.getColumnModel().getColumn(2).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(65);
		table.getColumnModel().getColumn(3).setMinWidth(65);
		table.getColumnModel().getColumn(3).setMaxWidth(65);

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

		JButton button = new JButton(CompanyUsed.okText);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel_1.add(button);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.WEST);

		JButton printBtn = new JButton(CompanyUsed.printText);
		printBtn.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));
		printBtn.addActionListener(new PrintSoldMateialList(table));
		panel_2.add(printBtn);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_3, BorderLayout.NORTH);

		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(12);
		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {

				rowFiltering();
			}
		});
		JLabel lblSearch = new JLabel(CompanyUsed.searchText);
		lblSearch.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/search.png")));
		panel_3.add(lblSearch);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		// TODO Auto-generated constructor stub
		load();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setTitle("لیست فروش محصولات");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// setResizable(false);
	}

	public static void main(String[] args) {
		new SoldMaterilaList();
	}

	private void load() {

		String name;
		String unit;
		int qty;
		String date;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT name,unit,quantity FROM `sold_material_list` WHERE dataYearid = 2013");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				qty = result.getInt(3);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						qty, unit, name, table.getRowCount() + 1 });
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

}
