package view;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class BuyingRawMaterila extends JDialog {
	private JTable table;

	public BuyingRawMaterila() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("HDPE", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null, null }, },
				new String[] {
						"\u0645\u0628\u0644\u063A",
						"\u0641\u06CC \u0648\u0627\u062D\u062F",
						"\u0648\u0627\u062D\u062F",
						"\u0645\u0642\u062F\u0627\u0631",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(43);
		scrollPane.setViewportView(table);
		
				JPanel hdpeTotalPanel = new JPanel();
				panel.add(hdpeTotalPanel, BorderLayout.SOUTH);
				hdpeTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
				hdpeTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("161px"),
						ColumnSpec.decode("143px"),
						FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						ColumnSpec.decode("43px"),},
					new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						RowSpec.decode("5px"),}));
				
						JLabel hdpeArzLabel = new JLabel("افغانی");
						hdpeTotalPanel.add(hdpeArzLabel, "1, 2, center, default");
						
								JLabel hdpeTotalValueLabel = new JLabel("12000");
								hdpeTotalPanel.add(hdpeTotalValueLabel, "2, 2, center, top");
								
										JLabel hepdTotalLanel = new JLabel("کل مبلغ :");
										hdpeTotalPanel.add(hepdTotalLanel, "4, 2, left, top");

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);

		JButton button_3 = new JButton("افزودن نوع جدید مواد");
		panel_3.add(button_3);

		JButton button_1 = new JButton("پاک کردن مورد");
		panel_3.add(button_1);

		JButton button = new JButton("افزودن مورد جدید");
		panel_3.add(button);

		JButton button_2 = new JButton("تائید");
		panel_3.add(button_2);

		setSize(500, 159);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("لیست خرید مواد اولیه");
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new BuyingRawMaterila();
	}

}
