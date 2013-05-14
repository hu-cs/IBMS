package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class StockUi extends JDialog {
	private JTable table;

	public StockUi() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] { "\u0648\u0627\u062D\u062F",
				"\u0645\u0642\u062F\u0627\u0631",
				"\u0646\u0627\u0645 \u062C\u0646\u0633",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(3).setPreferredWidth(49);
		scrollPane.setViewportView(table);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.EAST);
								
										JButton button_2 = new JButton("تاریخچه ذخیره");
										panel_1.add(button_2);
						
								JButton button_3 = new JButton("تاریخچه برداشت");
								panel_1.add(button_3);
				
						JButton button_1 = new JButton("افزودن جنس به انبار");
						panel_1.add(button_1);
		
				JButton button = new JButton("تائید");
				panel_1.add(button);
				
				JPanel panel_2 = new JPanel();
				panel.add(panel_2, BorderLayout.WEST);
				
				JButton button_4 = new JButton("چاپ فاکتور");
				panel_2.add(button_4);
		// TODO Auto-generated constructor stub

		setSize(535, 104);
		setLocationRelativeTo(null);
		setTitle("لیست موجودی انبار");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new StockUi();
	}

}
