package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class AddingNewBuyingEquipment extends JDialog {
	private JTable table;

	public AddingNewBuyingEquipment() {
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"\u0645\u0628\u0644\u063A", "\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);
		
		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(340,104);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("افزودن مورد جدید");
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new AddingNewBuyingEquipment();
	}

}
