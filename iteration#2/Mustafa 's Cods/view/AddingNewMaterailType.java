package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class AddingNewMaterailType extends JDialog {
	private JTable table;

	public AddingNewMaterailType() {
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"\u0646\u0648\u0639 \u0645\u0648\u0627\u062F"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton button_1 = new JButton("انصراف");
		button_1.setIcon(new ImageIcon(getClass().getResource("/Icon/cancel.png")));
		panel.add(button_1);
		
		JButton button = new JButton("تائید");
		button.setIcon(new ImageIcon(getClass().getResource("/Icon/Accept.png")));
		panel.add(button);
		
		setSize(205, 104);
		setLocationRelativeTo(null);
		setTitle("افزودن مورد جدید");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new AddingNewMaterailType();
	}

}
