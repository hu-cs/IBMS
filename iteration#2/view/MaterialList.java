package view;

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
import java.awt.FlowLayout;

public class MaterialList extends JDialog {
	private JTable table;

	public MaterialList() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, },
				new String[] {
						"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634",
						"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
						"\u0648\u0627\u062D\u062F \u067E\u06CC\u0645\u0627\u06CC\u0634",
						"\u0646\u0627\u0645 \u062C\u0646\u0633",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(83);
		table.getColumnModel().getColumn(3).setPreferredWidth(82);
		table.getColumnModel().getColumn(4).setPreferredWidth(52);

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
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_2 = new JButton("ویرایش");
		panel.add(button_2);

		JButton button_1 = new JButton("پاک کردن نوع جنس");
		panel.add(button_1);

		JButton button = new JButton("اضافه نمودن جنس جدید");
		panel.add(button);

		JButton button_3 = new JButton("تائید");
		panel.add(button_3);

		setSize(500, 104);
		setLocationRelativeTo(null);
		setTitle("لیست اجناس شرکت");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MaterialList();
	}

}
