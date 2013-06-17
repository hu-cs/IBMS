package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.awt.ComponentOrientation;
import java.util.EventObject;

import javax.swing.JPanel;
import javax.swing.JButton;

import controller.yearId.YearCreationConfirmation;
import controller.yearId.YearSelectionCancel;

public class DataYearIdCreation extends JDialog {
	private JTable table;
	JTable selectionTable;

	public DataYearIdCreation(JTable selectionTable) {
		getContentPane().setComponentOrientation(
				ComponentOrientation.RIGHT_TO_LEFT);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null }, },
				new String[] { "\u0633\u0627\u0644" }));

		table.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			@Override
			public boolean stopCellEditing() {
				String value = "" + getCellEditorValue();
				try {
					Integer.parseInt(value);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							"Enter a Valid Number ...");
					return false;
				}

				return super.stopCellEditing();
			}
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		scrollPane.setViewportView(table);

		JPanel buttonPanel = new JPanel();
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("انصراف");
		cancelButton.addActionListener(new YearSelectionCancel(this));
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("تائید");
		okButton.addActionListener(new YearCreationConfirmation(table,
				selectionTable, this));
		buttonPanel.add(okButton);

		setTitle("ایجاد حساب سال");
		setSize(150, 103);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// public static void main(String[] args) {
	// new DataYearIdCreation();
	// }

}