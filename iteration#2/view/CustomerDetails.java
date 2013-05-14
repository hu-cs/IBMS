package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CustomerDetails extends JDialog {
	private JTable table;
	private JTable table_1;

	public CustomerDetails() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel label_3 = new JLabel("رسیدگی");
		label_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(label_3, BorderLayout.NORTH);

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0628\u0644\u063A", "\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(44);
		scrollPane_1.setViewportView(table_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("77px"),
				ColumnSpec.decode("93px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("64px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("7px"),}));

		JLabel label_5 = new JLabel("23000");
		panel_5.add(label_5, "2, 2, center, top");

		JLabel label_4 = new JLabel("کل رسیدگی :");
		panel_5.add(label_4, "4, 2, left, top");

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("بردگی");
		label.setBorder(new LineBorder(new Color(0, 0, 0)));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631",
						"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(95);
		table.getColumnModel().getColumn(2).setPreferredWidth(77);
		table.getColumnModel().getColumn(3).setPreferredWidth(53);
		scrollPane.setViewportView(table);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("85px"),
				ColumnSpec.decode("93px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("69px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("7px"),}));
				
						JLabel label_2 = new JLabel("340000");
						panel_2.add(label_2, "2, 2, center, top");
		
				JLabel label_1 = new JLabel("کل بردگی :");
				panel_2.add(label_1, "4, 2, left, top");

		JPanel panel_6 = new JPanel();
		getContentPane().add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.add(panel_7, BorderLayout.NORTH);
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("278px"), ColumnSpec.decode("87px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("62px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"), }));

		JLabel label_7 = new JLabel("320000");
		panel_7.add(label_7, "2, 2, center, top");

		JLabel label_6 = new JLabel("طلب شرکت :");
		panel_7.add(label_6, "4, 2, center, top");

		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_8.add(panel_4, BorderLayout.EAST);
								
								JButton button_4 = new JButton("حذف پرداخت");
								panel_4.add(button_4);
								
								JButton button = new JButton("ویرایش پرداخت");
								panel_4.add(button);
						
								JButton button_3 = new JButton("افزودن به حساب پرداخت");
								panel_4.add(button_3);
				
						JButton button_2 = new JButton("دیدن فاکتور");
						panel_4.add(button_2);
		
				JButton button_1 = new JButton("تائید");
				panel_4.add(button_1);
				
				JPanel panel_9 = new JPanel();
				panel_8.add(panel_9, BorderLayout.WEST);
				
				JButton button_5 = new JButton("چاپ لیست");
				panel_9.add(button_5);
		
		setSize(709, 179);
		setLocationRelativeTo(null);
		setTitle("حساب علی رضا احمدی");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) {
		new CustomerDetails();
	}

}
