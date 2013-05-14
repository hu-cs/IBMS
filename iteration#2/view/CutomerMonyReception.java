package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class CutomerMonyReception extends JDialog {
	private JTable table;

	public CutomerMonyReception() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null }, },
				new String[] { "\u0645\u0628\u0644\u063A" }));
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);

		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(240, 104);
		setTitle("افزودن حساب رسید علی رضا احمدی");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CutomerMonyReception();
	}

}
