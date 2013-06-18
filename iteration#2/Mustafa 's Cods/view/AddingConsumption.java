package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class AddingConsumption extends JDialog {
	private JTable table;

	public AddingConsumption() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, },
				new String[] { "\u0645\u0628\u0644\u063A",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631" }));

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CompanyUsed.cancel);
		button_1.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/cancel.png")));
		panel.add(button_1);

		JButton button = new JButton(CompanyUsed.okText);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);

		setSize(334, 104);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("افزودن مورد جدید");
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new AddingConsumption();
	}

}
