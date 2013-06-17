package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import controller.AddCustomer;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerCreation extends JDialog {
	private JTable table;

	public CustomerCreation() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"\u0622\u062F\u0631\u0633", "\u0627\u0633\u0645 \u067E\u062F\u0631", "\u0634\u0647\u0631\u062A", "\u0627\u0633\u0645"
			}
		));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(87);
		table.getColumnModel().getColumn(3).setPreferredWidth(103);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CustomersList.CancelButton);
		button_1.setIcon(new ImageIcon(getClass().getResource("/Icon/Cancel.png")));
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			dispose();	
			}
		});
		JButton button = new JButton(CustomersList.ok);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);
		button.addActionListener(new AddCustomer(table,this));
		
		setSize(485, 120);
		setTitle(CustomersList.AddNewCustomer);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CustomerCreation();
	}

}
