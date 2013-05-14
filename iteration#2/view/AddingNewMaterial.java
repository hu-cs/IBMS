package view;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddingNewMaterial extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable addedMaterialTable;

	public AddingNewMaterial() {

		JScrollPane addedScrollPane = new JScrollPane();
		getContentPane().add(addedScrollPane, BorderLayout.CENTER);

		addedMaterialTable = new JTable();
		addedMaterialTable
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addedMaterialTable
				.setModel(new DefaultTableModel(
						new Object[][] { { null, "", null, null }, },
						new String[] {
								"\u0646\u0627\u0645",
								"\u0648\u0627\u062D\u062F \u067E\u06CC\u0645\u0627\u06CC\u0634",
								"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
								"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634" }));
		addedMaterialTable.getColumnModel().getColumn(0).setPreferredWidth(87);
		addedMaterialTable.getColumnModel().getColumn(1).setPreferredWidth(86);
		addedMaterialTable.getColumnModel().getColumn(2).setPreferredWidth(82);
		addedMaterialTable.getColumnModel().getColumn(3).setPreferredWidth(81);

		/**
		 * Creating table for indicating material details.
		 */

		JTable detailsTable = new JTable(new DefaultTableModel(
				new String[][] {}, new String[] { "com" }));
		addedScrollPane.setViewportView(addedMaterialTable);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(
				"\u0627\u0646\u0635\u0631\u0627\u0641");
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JButton okButton = new JButton("\u062A\u0627\u0626\u06CC\u062F");
		panel.add(okButton);

		setTitle("افزودن جنس جدید");
		setSize(545, 114);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new AddingNewMaterial();
	}

}
