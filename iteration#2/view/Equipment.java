package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Equipment extends JDialog {
	private JTable table;

	public Equipment() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(42);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
								
								JButton button_3 = new JButton("ویرایش");
								panel_1.add(button_3);
						
								JButton button_2 = new JButton("پاک کردن مورد");
								panel_1.add(button_2);
				
						JButton button = new JButton("افزودن مورد جدید");
						panel_1.add(button);
		
				JButton button_1 = new JButton("تائید");
				panel_1.add(button_1);
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.WEST);
				
				JButton button_4 = new JButton("چاپ لیست");
				panel_2.add(button_4);

		setSize(507, 104);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setTitle("لوازم مورد نیاز");
		setResizable(false);

	}

	public static void main(String[] args) {
		new Equipment();
	}

}
