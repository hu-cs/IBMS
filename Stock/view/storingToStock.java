package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
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

import ui.renderers.TableEditor;

import model.Material;
import connection.DBConnection;
import controller.DoneAdd;

public class storingToStock extends JDialog {

	public static JTable storingToStockTable;
	JComboBox<Material> materialList = new JComboBox<Material>();
	public static String cancel = StockGui.bundle.getString("cancel");
	String name;

	public storingToStock(String name) {
		this.name = name;

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		storingToStockTable = new JTable();

		storingToStockTable.setModel(new DefaultTableModel(new Object[][] { {
				null, null, null }, }, new String[] {
				"\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0642\u062F\u0627\u0631",
				"\u0646\u0627\u0645 \u062C\u0646\u0633" }) {
			boolean[] columnEditables = new boolean[] { true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		storingToStockTable.getColumnModel().getColumn(1).setPreferredWidth(65);
		storingToStockTable.getColumnModel().getColumn(2).setPreferredWidth(89);
		storingToStockTable.getColumnModel().getColumn(0)
				.setCellEditor(new TableEditor());

		storingToStockTable.setDefaultEditor(Object.class,
				new DefaultCellEditor(new JTextField()) {
					{
						((JTextField) getComponent())
								.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		storingToStockTable.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		storingToStockTable.setRowHeight(18);
		scrollPane.setViewportView(storingToStockTable);
		storingToStockTable.setValueAt(name, 0, 2);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(cancel);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		JButton button = new JButton(StockGui.ok);
		panel.add(button);
		DoneAdd done = new DoneAdd(storingToStockTable, this);
		button.addActionListener(done);

		// UpdateComboBox();

		setSize(440, 120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(StockGui.storeToStock);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);

	}

	// private void UpdateComboBox() {
	// // materialList = new JComboBox<Material>();
	// String name;
	// String unit;
	// float finishedCost;
	// float sellCost;
	//
	// Material loadedMaterial;
	//
	// try {
	// PreparedStatement statement = (PreparedStatement) DBConnection.connection
	// .prepareStatement("select name,unit,finishedCost,sellCost from materiallist");
	// ResultSet result = statement.executeQuery();
	// while (result.next()) {
	// name = result.getString(1);
	// unit = result.getString(2);
	// finishedCost = result.getFloat(3);
	// sellCost = result.getFloat(4);
	// loadedMaterial = new Material(name, unit, finishedCost,
	// sellCost);
	// Material.materialArray.add(loadedMaterial);
	// materialList.addItem(loadedMaterial);
	//
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	// public static void main(String[] args) {
	// // new storingToStock();
	//
	// }
	// void

}
