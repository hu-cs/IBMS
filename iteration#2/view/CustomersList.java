package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class CustomersList extends JDialog {
	private JTable table;

	public CustomersList() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u0637\u0644\u0628 \u0634\u0631\u06A9\u062A", "\u06A9\u0644 \u0631\u0633\u06CC\u062F", "\u06A9\u0644 \u0628\u0631\u062F\u06AF\u06CC", "\u0622\u062F\u0631\u0633", "\u0627\u0633\u0645 \u067E\u062F\u0631", "\u062A\u062E\u0644\u0635", "\u0627\u0633\u0645", "\u0634\u0645\u0627\u0631\u0647"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(96);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(195);
		table.getColumnModel().getColumn(4).setPreferredWidth(86);
		table.getColumnModel().getColumn(5).setPreferredWidth(96);
		table.getColumnModel().getColumn(6).setPreferredWidth(94);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(46);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
								
										JButton button_3 = new JButton("پاک کردن مشتری");
										panel_1.add(button_3);
						
								JButton button_2 = new JButton("افزودن مشتری");
								panel_1.add(button_2);
				
						JButton button_1 = new JButton("دیدن جزئیات");
						panel_1.add(button_1);
		
				JButton button = new JButton("تائید");
				panel_1.add(button);
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.WEST);
				
				JButton button_4 = new JButton("چاپ لیست");
				panel_2.add(button_4);
		setSize(682, 104);
		setTitle("لیست مشتریان");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CustomersList();
	}

}
