package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import controller.EditConfirmButton;

import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditingMaterial extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable editTable;
	JTable mainTable;

	 String previouseName;

	public EditingMaterial(JTable mainTable, String previouseName) {
		this.mainTable = mainTable;
		 this.previouseName = previouseName;

		JScrollPane addedScrollPane = new JScrollPane();
		getContentPane().add(addedScrollPane, BorderLayout.CENTER);

		
		editTable = new JTable();
		editTable.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		editTable
				.setModel(new DefaultTableModel(
						new Object[][] { { null, "", null, null }, },
						new String[] {
								"\u0646\u0627\u0645",
								"\u0648\u0627\u062D\u062F \u067E\u06CC\u0645\u0627\u06CC\u0634",
								"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
								"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634" }));
		editTable.getColumnModel().getColumn(0).setPreferredWidth(87);
		editTable.getColumnModel().getColumn(1).setPreferredWidth(86);
		editTable.getColumnModel().getColumn(2).setPreferredWidth(82);
		editTable.getColumnModel().getColumn(3).setPreferredWidth(81);
		
		editTable.getTableHeader().setReorderingAllowed(false);

		/**
		 * Creating table for indicating material details.
		 */

		JTable detailsTable = new JTable(new DefaultTableModel(
				new String[][] {}, new String[] { "com" }));
		addedScrollPane.setViewportView(editTable);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(
				"\u0627\u0646\u0635\u0631\u0627\u0641");
		cancelButton.setIcon(new ImageIcon(getClass().getResource("/Icon/Cancel.png")));
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JButton okButton = new JButton("\u062A\u0627\u0626\u06CC\u062F");
		okButton.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(okButton);
		okButton.addActionListener(new EditConfirmButton(editTable, this,previouseName));

		setValues();
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		setTitle(MaterialList.editMaterial);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(545, 114);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		// new EditingMaterial(null,null);
	}

	private void setValues() {
		int selectedRow = mainTable.getSelectedRow();

		String name = mainTable.getValueAt(selectedRow, 3).toString();
		String unit = mainTable.getValueAt(selectedRow, 2).toString();
		String finishedCost = mainTable.getValueAt(selectedRow, 1).toString();
		String sellCost = mainTable.getValueAt(selectedRow, 0).toString();

		editTable.setValueAt(name, 0, 0);
		editTable.setValueAt(unit, 0, 1);
		editTable.setValueAt(finishedCost, 0, 2);
		editTable.setValueAt(sellCost, 0, 3);

	}

}
