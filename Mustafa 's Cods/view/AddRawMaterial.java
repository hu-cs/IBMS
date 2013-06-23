package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.AddRawItem;

import sun.misc.Cache;
import ui.renderers.TableEditor;

public class AddRawMaterial extends JDialog {
	private JTable table;

	public AddRawMaterial() {

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(CompanyUsed.cancel);
		cancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/cancel.png")));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(cancelButton);

		JButton okButton = new JButton(CompanyUsed.okText);
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));
		panel.add(okButton);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null, null }, },
				new String[] { "\u062A\u0627\u0631\u06CC\u062E",
						"\u0645\u0642\u062F\u0627\u0631",
						"\u0641\u06CC \u0648\u0627\u062D\u062F",
						"\u0648\u0627\u062D\u062F",
						"\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(132);
		table.getColumnModel().getColumn(1).setPreferredWidth(89);
		table.getColumnModel().getColumn(2).setPreferredWidth(92);
		table.getColumnModel().getColumn(4).setPreferredWidth(89);
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditor());
		scrollPane.setViewportView(table);
		table.getTableHeader().setReorderingAllowed(false);

		okButton.addActionListener(new AddRawItem(table, this));
		setModalityType(ModalityType.APPLICATION_MODAL);

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		
		setTitle("افزودن مورد");
		setSize(626, 115);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new AddRawMaterial();
	}

}
