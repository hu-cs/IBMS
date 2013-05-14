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
import java.awt.FlowLayout;

public class ConsumptionList extends JDialog {
	private JTable table;

	public ConsumptionList() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{ null,
								"\u0645\u0635\u0627\u0631\u0641 \u0634\u0631\u06A9\u062A" },
						{ null,
								"\u0645\u0648\u0627\u062F \u0627\u0648\u0644\u06CC\u0647" },
						{ null,
								"\u0644\u0648\u0627\u0632\u0645 \u0627\u0648\u0644\u06CC\u0647" },
						{ null, "\u0627\u0633\u062A\u0647\u0644\u0627\u06A9" },
						{ null,
								"\u0645\u062C\u0645\u0648\u0639 \u0645\u0635\u0627\u0631\u0641" }, },
				new String[] { "\u0645\u0628\u0644\u063A",
						"\u0639\u0646\u0627\u0648\u06CC\u0646" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
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

		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(222, 168);
		setLocationRelativeTo(null);
		setTitle("لیست کل مصارف");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new ConsumptionList();
	}

}
