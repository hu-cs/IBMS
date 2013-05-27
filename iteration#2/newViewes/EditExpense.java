package newViewes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class EditExpense extends JDialog {

	private JTable table;

	public EditExpense() {
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);

		JButton button = new JButton("تایید");
		panel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0628\u0644\u063A",
				"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }));
		table.setRowHeight(21);
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

		table.getColumnModel().getColumn(0).setPreferredWidth(127);
		table.getColumnModel().getColumn(1).setPreferredWidth(117);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		scrollPane.setViewportView(table);

		setTitle("ویرایش مورد");
		setResizable(false);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setSize(418, 109);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new EditExpense();
	}

}
