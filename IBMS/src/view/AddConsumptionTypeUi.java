package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.AddUsedTypeListener;

public class AddConsumptionTypeUi extends JDialog {

	private JTable table;
	String dbTable = "companyconsumptions";

	public AddConsumptionTypeUi() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null }, },
				new String[] { "\u0646\u0648\u0639 \u0645\u0635\u0631\u0641" }));
		table.setDefaultEditor(Object.class, new DefaultCellEditor(
				new JTextField()) {
			{
				((JTextField) getComponent())
						.setHorizontalAlignment(SwingConstants.RIGHT);
			}

			String value = getCellEditorValue() + "";
		});
		
		table.getTableHeader().setReorderingAllowed(false);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		scrollPane.setViewportView(table);

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton(CompanyUsed.cancel);
		cancelButton.setIcon(new ImageIcon(getClass().getResource("/icon/Cancel.png")));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton(CompanyUsed.okText);
		okButton.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		okButton.addActionListener(new AddUsedTypeListener(table, this,
				dbTable, CompanyConsumption.selectTypeCombo,
				CompanyConsumption.typeList));
		buttonPanel.add(okButton);
		
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png")).getImage());

		setModalityType(ModalityType.APPLICATION_MODAL);
		setSize(214, 105);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new AddConsumptionTypeUi();
	}

}
