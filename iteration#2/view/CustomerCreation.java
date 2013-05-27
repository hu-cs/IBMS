package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class CustomerCreation extends JDialog {
	private JTable table;

	public CustomerCreation() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u0622\u062F\u0631\u0633", "\u0627\u0633\u0645 \u067E\u062F\u0631", "\u0634\u0647\u0631\u062A", "\u0627\u0633\u0645"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);

		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(485, 104);
		setTitle("افزودن مشتری جدید");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CustomerCreation();
	}

}
