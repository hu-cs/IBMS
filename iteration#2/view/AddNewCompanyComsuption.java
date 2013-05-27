package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import ui.renderers.ComAddItemRenderer;

import java.awt.FlowLayout;

public class AddNewCompanyComsuption extends JDialog {
	private JTable table;

	public AddNewCompanyComsuption() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null }, },
				new String[] {
						"\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0628\u0644\u063A \u062A\u0627\u062F\u06CC\u0647 \u0634\u062F\u0647",
						"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(117);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		scrollPane.setViewportView(table);

		table.getColumnModel().getColumn(0)
				.setCellEditor(new ComAddItemRenderer());

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);

		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(369, 104);
		setLocationRelativeTo(null);
		setTitle("افزودن مورد جدید");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new AddNewCompanyComsuption();
	}

}
