package view;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;

public class CorruptionConsumption extends JDialog {
	private JTable dastgahTable;
	private JTable variousTable;
	private JTable mohavateTable;
	private JTable sakhtemaniTable;

	public CorruptionConsumption() {
		getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getContentPane().add(tabbedPane);

		JPanel dastgahConsumptionPanel = new JPanel();
		tabbedPane.addTab("لوازم فنی و دستگاه", null, dastgahConsumptionPanel,
				null);
		dastgahConsumptionPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane dastgahScrollPane = new JScrollPane();
		dastgahConsumptionPanel.add(dastgahScrollPane);

		dastgahTable = new JTable();
		dastgahTable.setModel(new DefaultTableModel(
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
		dastgahTable.getColumnModel().getColumn(3).setResizable(false);
		dastgahTable.getColumnModel().getColumn(3).setPreferredWidth(43);
		dastgahScrollPane.setViewportView(dastgahTable);
		
				JPanel dastgahToolPanel = new JPanel();
				dastgahConsumptionPanel.add(dastgahToolPanel, BorderLayout.SOUTH);
				dastgahToolPanel.setLayout(new BorderLayout(0, 0));
														
																JPanel dastgahButtonPanel = new JPanel();
																dastgahToolPanel.add(dastgahButtonPanel, BorderLayout.EAST);
														dastgahButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
														
														JButton dastgahEditBtn = new JButton("ویرایش مورد");
														dastgahButtonPanel.add(dastgahEditBtn);
														
																JButton dastgahRemoveBtn = new JButton("پاک کردن مورد ");
																dastgahButtonPanel.add(dastgahRemoveBtn);
														
																JButton dastgahAddBtn = new JButton("افزودن مورد جدید");
																dastgahButtonPanel.add(dastgahAddBtn);
														
																JButton dastgahOkBtn = new JButton("تائید");
																dastgahButtonPanel.add(dastgahOkBtn);
												
														JPanel dastgahTotalPanel = new JPanel();
														dastgahTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
														dastgahToolPanel.add(dastgahTotalPanel, BorderLayout.NORTH);
														dastgahTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
																ColumnSpec.decode("max(162px;default)"),
																FormFactory.RELATED_GAP_COLSPEC,
																ColumnSpec.decode("143px"),
																FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
																FormFactory.DEFAULT_COLSPEC,},
															new RowSpec[] {
																FormFactory.LINE_GAP_ROWSPEC,
																RowSpec.decode("14px"),}));
																				
																				JLabel label_3 = new JLabel("افغانی");
																				dastgahTotalPanel.add(label_3, "1, 2, center, default");
																		
																				JLabel dastgahTotalValueLabel = new JLabel("340000");
																				dastgahTotalPanel.add(dastgahTotalValueLabel, "3, 2, center, top");
																
																		JLabel dastgahTotalLabel = new JLabel("جمع کل :");
																		dastgahTotalPanel.add(dastgahTotalLabel, "5, 2, left, top");
																		
																		JPanel dastgahPrintPanel = new JPanel();
																		dastgahToolPanel.add(dastgahPrintPanel, BorderLayout.WEST);
																		
																		JButton dastgahPringBtn = new JButton("چاپ لیست");
																		dastgahPrintPanel.add(dastgahPringBtn);

		JPanel mohavatePanel = new JPanel();
		tabbedPane.addTab("محوطه", null, mohavatePanel, null);
		mohavatePanel.setLayout(new BorderLayout(0, 0));
		

		

		JScrollPane mohavateScrollPane = new JScrollPane();
		mohavatePanel.add(mohavateScrollPane, BorderLayout.CENTER);

		mohavateTable = new JTable();
		mohavateTable.setModel(new DefaultTableModel(
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
		mohavateTable.getColumnModel().getColumn(3).setResizable(false);
		mohavateTable.getColumnModel().getColumn(3).setPreferredWidth(51);
		mohavateScrollPane.setViewportView(mohavateTable);
		
		JPanel mohavateToolPanel = new JPanel();
		mohavatePanel.add(mohavateToolPanel, BorderLayout.SOUTH);
		mohavateToolPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel mohaveteTotalPanel = new JPanel();
		mohaveteTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mohavateToolPanel.add(mohaveteTotalPanel);
		mohaveteTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("165px"),
				ColumnSpec.decode("142px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel mohavateArzLabel = new JLabel("افغانی");
		mohaveteTotalPanel.add(mohavateArzLabel, "1, 2, center, default");
		
		JLabel mohavateTotalValueLabel = new JLabel("19000");
		mohaveteTotalPanel.add(mohavateTotalValueLabel, "2, 2, center, default");
		
		JLabel mohavateTotalLabel = new JLabel("جمع کل:");
		mohaveteTotalPanel.add(mohavateTotalLabel, "4, 2, center, center");
		
		JPanel panel_2 = new JPanel();
		mohavateToolPanel.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel mohavateButtonPanel = new JPanel();
		panel_2.add(mohavateButtonPanel, BorderLayout.EAST);
		
		JButton mohavateDeleteBtn = new JButton("پاک کردن مورد");
		mohavateButtonPanel.add(mohavateDeleteBtn);
		
		JButton mohavateEditBtn = new JButton("ویرایش مورد");
		mohavateButtonPanel.add(mohavateEditBtn);
		
		JButton mohavateAddItemBtn = new JButton("افزودن مورد جدید");
		mohavateButtonPanel.add(mohavateAddItemBtn);
		
		JButton mohavateOkBtn = new JButton("تائید");
		mohavateButtonPanel.add(mohavateOkBtn);
		
		JPanel mohavatePrintPanel = new JPanel();
		panel_2.add(mohavatePrintPanel, BorderLayout.WEST);
		
		JButton mohavatePrintBtn = new JButton("چاپ لیست");
		mohavatePrintPanel.add(mohavatePrintBtn);

		JPanel sakhtemaniPanel = new JPanel();
		tabbedPane.addTab("ساختمانی", null, sakhtemaniPanel, null);
		sakhtemaniPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane sakhtemaniScrollPane = new JScrollPane();
		sakhtemaniPanel.add(sakhtemaniScrollPane, BorderLayout.CENTER);

		sakhtemaniTable = new JTable();
		sakhtemaniTable.setModel(new DefaultTableModel(
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
		sakhtemaniTable.getColumnModel().getColumn(1).setPreferredWidth(88);
		sakhtemaniTable.getColumnModel().getColumn(2).setPreferredWidth(93);
		sakhtemaniTable.getColumnModel().getColumn(3).setPreferredWidth(74);
		sakhtemaniScrollPane.setViewportView(sakhtemaniTable);
		
		JPanel panel_5 = new JPanel();
		sakhtemaniPanel.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JPanel sakhtemaniButtonPanel = new JPanel();
		panel_5.add(sakhtemaniButtonPanel, BorderLayout.EAST);
		
		JButton sakhtemaniDeleteBtn = new JButton("پاک کردن مورد");
		sakhtemaniButtonPanel.add(sakhtemaniDeleteBtn);
		
		JButton sakhtemaniEditBtn = new JButton("ویرایش مورد");
		sakhtemaniButtonPanel.add(sakhtemaniEditBtn);
		
		JButton sakhtemaniAddBtn = new JButton("افزودن مورد جدید");
		sakhtemaniButtonPanel.add(sakhtemaniAddBtn);
		
		JButton sakhtemaniOkBtn = new JButton("تائید");
		sakhtemaniButtonPanel.add(sakhtemaniOkBtn);
		
		JPanel sakhtemaniPrintPanel = new JPanel();
		panel_5.add(sakhtemaniPrintPanel, BorderLayout.WEST);
		
		JButton sakhtemaniPrintBtn = new JButton("چاپ لیست");
		sakhtemaniPrintPanel.add(sakhtemaniPrintBtn);
		
		JPanel sakhtemaniTotalPanel = new JPanel();
		sakhtemaniTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.add(sakhtemaniTotalPanel, BorderLayout.NORTH);
		sakhtemaniTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("162px"),
				ColumnSpec.decode("max(146px;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel sakhtemaniArzLabel = new JLabel("افغانی");
		sakhtemaniTotalPanel.add(sakhtemaniArzLabel, "1, 2, center, default");
		
		JLabel sakhtemaniTotalValueLabel = new JLabel("1990");
		sakhtemaniTotalPanel.add(sakhtemaniTotalValueLabel, "2, 2, center, default");
		
		JLabel sakhtemaniTotalLabel = new JLabel("جمع کل:");
		sakhtemaniTotalPanel.add(sakhtemaniTotalLabel, "4, 2, center, top");

		JPanel variousPanel = new JPanel();
		tabbedPane.addTab("متفرقه", null, variousPanel, null);
		variousPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane variousScrollPane = new JScrollPane();
		variousPanel.add(variousScrollPane, BorderLayout.CENTER);

		variousTable = new JTable();
		variousTable.setModel(new DefaultTableModel(
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
		variousTable.getColumnModel().getColumn(3).setPreferredWidth(47);
		variousScrollPane.setViewportView(variousTable);
		
		JPanel variousToolPanel = new JPanel();
		variousPanel.add(variousToolPanel, BorderLayout.SOUTH);
		variousToolPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel variousButtonPanel = new JPanel();
		variousToolPanel.add(variousButtonPanel, BorderLayout.EAST);
		
		JButton variousDeleteBtn = new JButton("پاک کردن مورد");
		variousButtonPanel.add(variousDeleteBtn);
		
		JButton variousEditBtn = new JButton("ویرایش مورد");
		variousButtonPanel.add(variousEditBtn);
		
		JButton variousAddBtn = new JButton("افزودن مورد جدید");
		variousButtonPanel.add(variousAddBtn);
		
		JButton variousOKButton = new JButton("تائید");
		variousButtonPanel.add(variousOKButton);
		
		JPanel variousPrintPanel = new JPanel();
		variousToolPanel.add(variousPrintPanel, BorderLayout.WEST);
		
		JButton variousPrintBtn = new JButton("چاپ لیست");
		variousPrintPanel.add(variousPrintBtn);
		
		JPanel variousTotalPanel = new JPanel();
		variousTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		variousToolPanel.add(variousTotalPanel, BorderLayout.NORTH);
		variousTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("162px"),
				ColumnSpec.decode("max(143px;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("40px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel variousArzLabel = new JLabel("افغانی");
		variousTotalPanel.add(variousArzLabel, "1, 2, center, default");
		
		JLabel variousTotalValueLabel = new JLabel("1202");
		variousTotalPanel.add(variousTotalValueLabel, "2, 2, center, default");
		
		JLabel variousTotalLabel = new JLabel("جمع کل:");
		variousTotalPanel.add(variousTotalLabel, "4, 2, left, top");
		// TODO Auto-generated constructor stub

		setSize(563, 152);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("مصارف استهلاک");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CorruptionConsumption();
	}

}
