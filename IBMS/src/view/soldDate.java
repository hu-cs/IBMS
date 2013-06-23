package view;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;

import controller.DataYearId;
import controller.SoldDate;

public class soldDate extends JDialog {

	JLabel label_1;
	public static JTable soldListTable;
	String title;

	public static String TotalSold = StockGui.bundle.getString("TotalSold");
	public static String SoldDate = StockGui.bundle.getString("SoldDate");

	public soldDate() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		soldListTable = new JTable();
		soldListTable.setModel(new DefaultTableModel(new Object[][] { { null,
				null, null, null }, }, new String[] {
				"\u062A\u0627\u0631\u06CC\u062E", "\u062A\u0648\u0633\u0637",
				"\u0645\u0642\u062F\u0627\u0631",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		soldListTable.getColumnModel().getColumn(0).setPreferredWidth(175);
		soldListTable.getColumnModel().getColumn(0).setMinWidth(21);
		soldListTable.getColumnModel().getColumn(0).setMaxWidth(175);
		soldListTable.getColumnModel().getColumn(1).setPreferredWidth(110);
		soldListTable.getColumnModel().getColumn(1).setMaxWidth(110);
		soldListTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		soldListTable.getColumnModel().getColumn(2).setMaxWidth(80);
		scrollPane.setViewportView(soldListTable);
		
		soldListTable.getTableHeader().setReorderingAllowed(false);
		
		soldListTable.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		SoldDate get = new SoldDate(soldListTable);
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("235px"), ColumnSpec.decode("-68px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("48px"), FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("6px"), }));

		label_1 = new JLabel("0");
		panel_1.add(label_1, "4, 2, center, top");

		JLabel label = new JLabel(TotalSold);
		panel_1.add(label, "6, 2, left, top");

		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.add(panel_2, BorderLayout.SOUTH);

		JButton button = new JButton(StockGui.ok);
		panel_2.add(button);

		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		load(StockGui.table);
		setTotal();

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
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
		String buyer;
		String dateAndTime;
		((DefaultTableModel) this.soldListTable.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Quantity, Buyer, Date_And_Time FROM sell_history where Name = '"
							+ name + "'And datayearid = " + datayearid + " ");
			ResultSet resul = statement.executeQuery();

			while (resul.next()) {
				quantity = resul.getString(1);
				buyer = resul.getString(2);
				dateAndTime = resul.getString(3);
				((DefaultTableModel) this.soldListTable.getModel())
						.addRow(new Object[] { dateAndTime, buyer, quantity,
								this.soldListTable.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		title = SoldDate + name;
	}

	private void setTotal() {

		int total = 0;
		for (int conter = 0; conter < soldListTable.getRowCount(); conter++) {

			if (soldListTable.getValueAt(0, 0) == null)
				return;
			total += Integer.parseInt(soldListTable.getValueAt(conter, 2)
					.toString());
		}
		label_1.setText(total + "");

	}

	public static void main(String[] args) {
		new soldDate();
	}

}
