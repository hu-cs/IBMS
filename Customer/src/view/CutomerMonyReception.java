package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

import renderer.TableEditor;
import controller.AddToGiveingAccount;

import java.awt.FlowLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CutomerMonyReception extends JDialog {
	private JTable table;

	public CutomerMonyReception() {
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, }, new String[] { CustomersList.Date,
					CustomersList.Cost }));
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
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditor());
		JButton button = new JButton(CustomersList.ok);
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);
		button.addActionListener(new AddToGiveingAccount(table, this));

		setSize(240, 120);
		setTitle("افزودن حساب رسید علی رضا احمدی");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		new CutomerMonyReception();
	}

}
