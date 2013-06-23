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

import controller.AddingConfirmBtn;

import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import controller.AddingConfirmBtn;

public class AddingNewMaterial extends JDialog {

	public static JTable addedMaterialTable;

	public AddingNewMaterial() {

		JScrollPane addedScrollPane = new JScrollPane();
		getContentPane().add(addedScrollPane, BorderLayout.CENTER);

		addedMaterialTable = new JTable();
		// addedMaterialTable
		// .setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addedMaterialTable.setModel(new DefaultTableModel(new Object[][] { {
				null, "", null, null }, }, new String[] { "قیمت فروش",
				"قیمت تمام شد", "واحد", "نام" }));
		addedMaterialTable.getColumnModel().getColumn(0).setPreferredWidth(87);
		addedMaterialTable.getColumnModel().getColumn(1).setPreferredWidth(86);
		addedMaterialTable.getColumnModel().getColumn(2).setPreferredWidth(82);
		addedMaterialTable.getColumnModel().getColumn(3).setPreferredWidth(81);

		addedMaterialTable.getTableHeader().setReorderingAllowed(false);
		addedMaterialTable.setDefaultEditor(Object.class,
				new DefaultCellEditor(new JTextField()) {
					{
						((JTextField) getComponent())
								.setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		addedMaterialTable.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		/**
		 * Creating table for indicating material details.
		 */

		addedScrollPane.setViewportView(addedMaterialTable);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(
				"\u0627\u0646\u0635\u0631\u0627\u0641");
		cancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Cancel.png")));

		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		AddingConfirmBtn addingObject = new AddingConfirmBtn(
				addedMaterialTable, this);
		JButton okButton = new JButton("\u062A\u0627\u0626\u06CC\u062F");
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));
		panel.add(okButton);
		okButton.addActionListener(addingObject);

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		
		setTitle(MaterialList.addMaterial);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(545, 114);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public static void main(String[] args) {
		new AddingNewMaterial();
	}

}
