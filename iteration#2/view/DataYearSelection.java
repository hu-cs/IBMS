package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JButton;

import com.mysql.jdbc.PreparedStatement;

import controller.DataYearId;
import controller.yearId.AddYearId;
import controller.yearId.CheckPropertyChangeListener;
import controller.yearId.DeleteYearId;
import controller.yearId.YearIdConfirmation;
import controller.yearId.YearSelectionCancel;
import dataBase.DBConnection;

import ui.renderers.YearSelectionColumnRenderer;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataYearSelection extends JDialog {
	private JTable table;
	JCheckBox selectionCheckBox;
	JPanel selectionPanel;

	public DataYearSelection() {

		JPanel contentPanel = new JPanel(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] {
				"\u0627\u0646\u062A\u062E\u0627\u0628 \u0633\u0627\u0644",
				"\u0633\u0627\u0644", "\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { true, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(41);
		scrollPane.setViewportView(table);

		selectionCheckBox = new JCheckBox();
		selectionPanel = new JPanel();

		selectionPanel.add(selectionCheckBox);

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton deleteYearId = new JButton("پاک کردن حساب سال");
		deleteYearId.addActionListener(new DeleteYearId(table));
		buttonPanel.add(deleteYearId);

		JButton addNewYearId = new JButton("اضافه نمودن حساب جدید");
		addNewYearId.addActionListener(new AddYearId(table));

		buttonPanel.add(addNewYearId);

		JButton cancelButton = new JButton("انصراف");
		cancelButton.addActionListener(new YearSelectionCancel(this));
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("تائید");
		okButton.addActionListener(new YearIdConfirmation(table, this));
		buttonPanel.add(okButton);

		TableColumn column = table.getColumn("انتخاب سال");
		column.setCellEditor(new YearSelectionColumnRenderer(selectionCheckBox));
		column.setCellRenderer(new YearSelectionColumnRenderer(
				selectionCheckBox));

		selectionCheckBox
				.addPropertyChangeListener(new CheckPropertyChangeListener(
						table));

		table.setRowHeight(30);

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

		updateIdList();

		getContentPane().add(contentPanel);
		setTitle("انتخاب حساب سال");
		// setSize(500, 162);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		revalidate();
		repaint();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void updateIdList() {

		((DefaultTableModel) table.getModel()).setRowCount(0);

		int yearId;
		boolean isSelected;
		int rowCounter = 0;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT year_Id,Is_selected FROM `data_year` ");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				rowCounter++;
				yearId = result.getInt(1);
				isSelected = result.getBoolean(2);
				((DefaultTableModel) table.getModel())
						.addRow(new Object[] { isSelected, yearId + "",
								table.getRowCount() + 1 + "" });

				// creationTable
				// .setValueAt(yearId, rowCounter, 1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DataYearSelection();
	}

	// private static class YearIdConfirmation implements ActionListener {
	//
	// JTable yearIdTable;
	//
	// public YearIdConfirmation(JTable yearIdTable) {
	// this.yearIdTable = yearIdTable;
	// }
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// setYearId();
	// }
	//
	// private void setYearId() {
	//
	// for (int counter = 0; counter < yearIdTable.getRowCount(); counter++) {
	// System.err.println(Integer.parseInt(yearIdTable.getValueAt(
	// counter, 1).toString()));
	//
	// if (Boolean.parseBoolean(yearIdTable.getValueAt(counter, 0)
	// .toString())) {
	// System.out.println(Integer.parseInt(yearIdTable.getValueAt(
	// counter, 1).toString()));
	// DataYearId.yearId = Integer.parseInt(yearIdTable
	// .getValueAt(counter, 1).toString());
	// break;
	// }
	// }
	//
	// }
	// }

}
