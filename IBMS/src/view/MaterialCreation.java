package view;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MaterialCreation extends JDialog {
	private JTable table;

	public MaterialCreation() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, },
				new String[] {
						"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634",
						"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
						"\u0648\u0627\u062D\u062F", "\u0646\u0627\u0645" }));

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
		table.getColumnModel().getColumn(1).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton cancelBottun = new JButton("انصرا�?");
		panel.add(cancelBottun);

		JButton okButton = new JButton("تائید");
		panel.add(okButton);

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setSize(414, 114);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("اضا�?ه کردن جنس جدید");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		new MaterialCreation();
	}
}
