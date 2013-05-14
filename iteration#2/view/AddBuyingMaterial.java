package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class AddBuyingMaterial extends JDialog {
	private JTable table;

	public AddBuyingMaterial() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"\u062A\u0627\u0631\u06CC\u062E", "\u0645\u0628\u0644\u063A", "\u0641\u06CC \u0648\u0627\u062D\u062F", "\u0648\u0627\u062D\u062F", "\u0645\u0642\u062F\u0627\u0631", "\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);
		
		JButton button = new JButton("تائید");
		panel.add(button);
		
		setSize(500, 104);
		setLocationRelativeTo(null);
		setTitle("افزودن مورد جدید");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new AddBuyingMaterial();
	}

}
