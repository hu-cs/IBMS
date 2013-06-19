package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Material;
import ui.renderers.*;
import connection.DBConnection;
import controller.DataYearId;
import controller.DoneAdd;
import controller.DoneEdit;

public class EditGui extends JDialog {

	public static JTable removeTabel;
	int datayearid = DataYearId.getDataYearId();
	public static String editSelectedStore = StockGui.bundle.getString("edit Selected Store");

	public EditGui() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		removeTabel = new JTable();

		removeTabel.setModel(new DefaultTableModel(new Object[][] { { null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0642\u062F\u0627\u0631" }));
		removeTabel.getColumnModel().getColumn(1).setPreferredWidth(89);

		;

		removeTabel.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		removeTabel.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		removeTabel.setRowHeight(18);
		removeTabel.getColumnModel().getColumn(0)
				.setCellEditor(new TableEditor());
		scrollPane.setViewportView(removeTabel);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(storingToStock.cancel);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Cancel.png")));

		JButton button = new JButton(StockGui.ok);
		panel.add(button);
		DoneEdit done = new DoneEdit(removeTabel, this);
		button.addActionListener(done);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));

		Update(StoreDate.storeTable, StockGui.table);

		setSize(251, 120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(editSelectedStore);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);

	}

	private void Update(JTable storeTable, JTable stockTable) {

		int stockselectedRow = stockTable.getSelectedRow();
		String name = stockTable.getValueAt(stockselectedRow, 2).toString();
		int selecterow = storeTable.getSelectedRow();
		String dateAndTime = storeTable.getValueAt(selecterow, 0).toString();
		int quantity = Integer.parseInt(storeTable.getValueAt(selecterow, 1)
				.toString());

		String editDate;
		int editQuantity;

		((DefaultTableModel) removeTabel.getModel()).setRowCount(0);

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Quantity, Date_And_Time FROM store_history where Quantity = "
							+ quantity
							+ " And Date_And_Time = '"
							+ dateAndTime
							+ "' And Name = '"
							+ name
							+ "' And DataYearId = "
							+ datayearid + "");
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				editQuantity = result.getInt(1);
				editDate = result.getString(2);

				((DefaultTableModel) removeTabel.getModel())
						.addRow(new Object[] { editDate, quantity });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new EditGui();

	}

}
