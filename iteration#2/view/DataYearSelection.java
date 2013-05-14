package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JButton;

import ui.renderers.YearSelectionColumnRenderer;
import java.awt.FlowLayout;

public class DataYearSelection extends JDialog {
	private JTable table;
	JCheckBox selectionCheckBox;
	JPanel selectionPanel;

	public DataYearSelection() {

		JPanel contentPanel = new JPanel(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] {
				"\u0627\u0646\u062A\u062E\u0627\u0628 \u0633\u0627\u0644",
				"\u0633\u0627\u0644", "\u0634\u0645\u0627\u0631\u0647" }));
		scrollPane.setViewportView(table);

		selectionCheckBox = new JCheckBox();
		selectionPanel = new JPanel();

		selectionPanel.add(selectionCheckBox);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_3 = new JButton("انصراف");
		panel.add(button_3);

		JButton button_2 = new JButton("تائید");
		panel.add(button_2);

		JButton button_1 = new JButton("پاک کردن حساب سال");
		panel.add(button_1);

		JButton button = new JButton("اضافه نمودن حساب جدید");
		panel.add(button);

		TableColumn column = table.getColumn("انتخاب سال");
		column.setCellEditor(new YearSelectionColumnRenderer(selectionPanel));
		column.setCellRenderer(new YearSelectionColumnRenderer(selectionPanel));
		// column.setResizable
		table.setRowHeight(30);

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

		getContentPane().add(contentPanel);
		setTitle("انتخاب حساب سال");
		setSize(500, 128);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {

		new DataYearSelection();
	}

}
