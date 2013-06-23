package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.EditExpence;
import ui.renderers.TableEditor;

public class EditUsedItem extends JDialog {

	// TableModel tableModel = ;

	public static JTable table = new JTable(new DefaultTableModel(
			new Object[][] { { null, null, null }, }, new String[] {
					"\u062A\u0627\u0631\u06CC\u062E",
					"\u0645\u0628\u0644\u063A",
					"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }));
	JTable maintable;
	String title;
	String invoiceLastNumber;
	String dbTable = "corruptionconsumption";

	public EditUsedItem(JTable maintable, String title, String invoiceLastNumber) {
		table.getTableHeader().setReorderingAllowed(false);
		this.maintable = maintable;
		this.title = title;
		this.invoiceLastNumber = invoiceLastNumber;
		System.err.println(title);
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CompanyUsed.cancel);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		panel.add(button_1);

		JButton button = new JButton(CompanyUsed.okText);
		panel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// table.setModel();
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
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditor());
		scrollPane.setViewportView(table);
		openInterface();

		button.addActionListener(new EditExpence(table, title,
				invoiceLastNumber, this, dbTable, CompanyUsed.table,
				CompanyUsed.selectTypeCombo));
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setTitle("ویرایش مورد");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(418, 109);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void openInterface() {

		int selectedRow = maintable.getSelectedRow();
		String invoiceNumber = maintable.getValueAt(selectedRow, 2).toString();
		float invoiceCost = Float.parseFloat(maintable.getValueAt(selectedRow,
				1).toString());
		String date = maintable.getValueAt(selectedRow, 0).toString();
		System.out.println(date);

		table.setValueAt(maintable.getValueAt(selectedRow, 0), 0, 0);
		table.setValueAt(maintable.getValueAt(selectedRow, 1), 0, 1);
		table.setValueAt(maintable.getValueAt(selectedRow, 2), 0, 2);

	}

}
