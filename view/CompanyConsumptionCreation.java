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
import org.jdesktop.swingx.JXDatePicker;

import controller.AddUsedItem;
import ui.renderers.TableEditor;

public class CompanyConsumptionCreation extends JDialog {

	private JTable table;
	JXDatePicker datePicker = new JXDatePicker();
	String dbTable = "companyconsumptions";

	public CompanyConsumptionCreation() {

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(CompanyUsed.cancel);
		cancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/cancel.png")));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton(CompanyUsed.okText);
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/icon/accept.png")));
		buttonPanel.add(okButton);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0628\u0644\u063A",
				"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }));
		table.setRowHeight(21);
		table.getTableHeader().setReorderingAllowed(false);
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
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditor());

		table.getColumnModel().getColumn(0).setPreferredWidth(127);
		table.getColumnModel().getColumn(1).setPreferredWidth(117);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		scrollPane.setViewportView(table);

		okButton.addActionListener(new AddUsedItem(table, this, dbTable,
				CompanyConsumption.selectTypeCombo, CompanyConsumption.table));

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());
		
		setTitle("افزودن مورد جدید");
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(418, 109);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

}
