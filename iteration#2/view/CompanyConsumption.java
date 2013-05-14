package view;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class CompanyConsumption extends JDialog {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;

	public CompanyConsumption() {

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel electricityPanel = new JPanel();
		tabbedPane.addTab("برق", null, electricityPanel, null);
		electricityPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		electricityPanel.add(scrollPane_1, BorderLayout.CENTER);

		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(1).setPreferredWidth(94);
		scrollPane_1.setViewportView(table_1);
		
		JPanel electricityTotalPanel = new JPanel();
		electricityPanel.add(electricityTotalPanel, BorderLayout.SOUTH);
		electricityTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		electricityTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("159px"),
				ColumnSpec.decode("143px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("108px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel electricityArzLabel = new JLabel("افغانی");
		electricityTotalPanel.add(electricityArzLabel, "1, 2, center, default");
		
		JLabel electricityTotalValueLabel = new JLabel("34000");
		electricityTotalPanel.add(electricityTotalValueLabel, "2, 2, center, top");
		
		JLabel electricityTotalLabel = new JLabel("کل مبالغ تادیه شده :");
		electricityTotalPanel.add(electricityTotalLabel, "4, 2, left, top");

		JPanel waterPanel = new JPanel();
		tabbedPane.addTab("آب", null, waterPanel, null);
		waterPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_2 = new JScrollPane();
		waterPanel.add(scrollPane_2, BorderLayout.CENTER);

		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(1).setPreferredWidth(92);
		scrollPane_2.setViewportView(table_2);
		
		JPanel waterToolPanel = new JPanel();
		waterToolPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		waterPanel.add(waterToolPanel, BorderLayout.SOUTH);
		waterToolPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("165px"),
				ColumnSpec.decode("max(143px;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("101px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel waterArzLabel = new JLabel("افغانی");
		waterToolPanel.add(waterArzLabel, "1, 2, center, default");
		
		JLabel waterTotalValueLabel = new JLabel("12000");
		waterToolPanel.add(waterTotalValueLabel, "2, 2, center, default");
		
		JLabel waterTotalLabel = new JLabel("کل مبالغ تادیه شده:");
		waterToolPanel.add(waterTotalLabel, "4, 2, left, top");

		JPanel gasPanel = new JPanel();
		tabbedPane.addTab("گاز", null, gasPanel, null);
		gasPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_3 = new JScrollPane();
		gasPanel.add(scrollPane_3, BorderLayout.CENTER);

		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_3.getColumnModel().getColumn(1).setPreferredWidth(88);
		scrollPane_3.setViewportView(table_3);
		
		JPanel gasTotalPanel = new JPanel();
		gasTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		gasPanel.add(gasTotalPanel, BorderLayout.SOUTH);
		gasTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("109dlu"),
				ColumnSpec.decode("max(96dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("122px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel gasArzLabel = new JLabel("افغانی");
		gasTotalPanel.add(gasArzLabel, "1, 2, center, default");
		
		JLabel gasTotalValueLabel = new JLabel("23400");
		gasTotalPanel.add(gasTotalValueLabel, "2, 2, center, default");
		
		JLabel gasTotalLabel = new JLabel("کل مبالغ تادیه شده:");
		gasTotalPanel.add(gasTotalLabel, "4, 2, left, top");

		JPanel phonePanel = new JPanel();
		tabbedPane.addTab("تلفن", null, phonePanel, null);
		phonePanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		phonePanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(101);
		scrollPane.setViewportView(table);
		
		JPanel phoneTotalPanel = new JPanel();
		phoneTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		phonePanel.add(phoneTotalPanel, BorderLayout.SOUTH);
		phoneTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("164px"),
				ColumnSpec.decode("max(96dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("118px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel phoneArzLabel = new JLabel("افغانی");
		phoneTotalPanel.add(phoneArzLabel, "1, 2, center, default");
		
		JLabel phoneTotalValueLabel = new JLabel("342300");
		phoneTotalPanel.add(phoneTotalValueLabel, "2, 2, center, default");
		
		JLabel phoneTotalLabel = new JLabel("کل مبالغ تادیه شده:");
		phoneTotalPanel.add(phoneTotalLabel, "4, 2, left, top");

		JPanel internetPanel = new JPanel();
		tabbedPane.addTab("اینترنت", null, internetPanel, null);
		internetPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_4 = new JScrollPane();
		internetPanel.add(scrollPane_4, BorderLayout.CENTER);

		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_4.getColumnModel().getColumn(1).setPreferredWidth(94);
		scrollPane_4.setViewportView(table_4);
		
		JPanel internetTotalPanel = new JPanel();
		internetTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		internetPanel.add(internetTotalPanel, BorderLayout.SOUTH);
		internetTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("164px"),
				ColumnSpec.decode("max(96dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("118px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel internetArzLabel = new JLabel("افغانی");
		internetTotalPanel.add(internetArzLabel, "1, 2, center, default");
		
		JLabel internetTotalValueLabel = new JLabel("230000");
		internetTotalPanel.add(internetTotalValueLabel, "2, 2, center, default");
		
		JLabel internetTotalLabel = new JLabel("کل مبالغ تادیه شده:");
		internetTotalPanel.add(internetTotalLabel, "4, 2, left, top");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.EAST);
		
		JButton button_3 = new JButton("ویرایش مورد");
		panel_3.add(button_3);
		
		JButton button_2 = new JButton("پاک کردن مورد");
		panel_3.add(button_2);
		
		JButton button_1 = new JButton("افزودن مورد");
		panel_3.add(button_1);
		
		JButton button = new JButton("تائید");
		panel_3.add(button);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.WEST);
		
		JButton button_4 = new JButton("چاپ لیست");
		panel_4.add(button_4);
		setSize(500, 159);
		setLocationRelativeTo(null);
		setTitle("مصارف شرکت");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CompanyConsumption();
	}

}
