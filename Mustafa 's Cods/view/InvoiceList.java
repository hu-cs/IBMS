package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;

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

import ui.renderers.ComAddItemRenderer;

import connection.DBConnection;
import controller.DataYearId;
import controller.printInvoiceList;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class InvoiceList extends JFrame {
	private JTable table;
	private JTextField textField;

	public InvoiceList() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0634\u062A\u0631\u06CC",
						"\u0645\u0628\u0644\u063A",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0631\u062F\u06CC\u0641" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class,
					Object.class, Object.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(165);
		table.getColumnModel().getColumn(0).setMinWidth(165);
		table.getColumnModel().getColumn(0).setMaxWidth(210);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(1).setMinWidth(119);
		table.getColumnModel().getColumn(1).setMaxWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(147);
		table.getColumnModel().getColumn(2).setMinWidth(102);
		table.getColumnModel().getColumn(2).setMaxWidth(147);
		table.getColumnModel().getColumn(3).setPreferredWidth(91);
		table.getColumnModel().getColumn(3).setMaxWidth(91);
		table.getColumnModel().getColumn(4).setPreferredWidth(66);
		table.getColumnModel().getColumn(4).setMaxWidth(80);
		scrollPane.setViewportView(table);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);

		JButton printBtn = new JButton(CompanyUsed.printText);
		printBtn.addActionListener(new printInvoiceList(table));
		printBtn.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/print.png")));
		panel_1.add(printBtn);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.EAST);

		JButton seeInvoice = new JButton(CompanyUsed.seeInvoice);
		seeInvoice.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_info16.png")));
		seeInvoice.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LoadInvoice(Integer.parseInt(table.getValueAt(
						table.getSelectedRow(), 3).toString()));
			}
		});
		panel_2.add(seeInvoice);

		JButton okBtn = new JButton(CompanyUsed.okText);
		okBtn.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				setVisible(false);

			}
		});
		panel_2.add(okBtn);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel_3, BorderLayout.NORTH);

		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(12);

		JLabel lblSearch = new JLabel(CompanyUsed.searchText);
		lblSearch.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/search.png")));
		panel_3.add(lblSearch);
		textField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				rowFiltering();

			}
		});

		loadInvoices();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());

		setTitle("لیست فاکتور های فروش");
		setSize(600, 594);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

	public static void main(String[] args) {
		new InvoiceList();
	}

	private void loadInvoices() {
		String invoiceNumber;
		float cost;
		String customer;
		String date;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT invoiceNumber,cost,customer,DateAndTime FROM `invoice_list` WHERE datayearid ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {

				invoiceNumber = result.getString(1);
				cost = result.getFloat(2);
				customer = result.getString(3);
				date = result.getString(4);

				((DefaultTableModel) table.getModel()).addRow(new String[] {
						date, customer, cost + "", invoiceNumber,
						table.getRowCount() + 1 + "" });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
