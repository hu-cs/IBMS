package newViewes;

import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import controler.expenses.AddUsedTypeListener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExpenseTypeUi extends JDialog {
	private JTable table;

	public AddExpenseTypeUi() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null }, },
				new String[] { "\u0646\u0648\u0639 \u0645\u0635\u0631\u0641" }));
		table.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			String value = getCellEditorValue() + "";

			// @Override
			// public boolean stopCellEditing() {
			// if ((value == null) || (value.equals("")))
			// return false;
			//
			// return super.stopCellEditing();
			// }
		});

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		scrollPane.setViewportView(table);

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("انصراف");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("تایید");
		okButton.addActionListener(new AddUsedTypeListener(table, this));
		buttonPanel.add(okButton);

		setSize(214, 105);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AddExpenseTypeUi();
	}

}
