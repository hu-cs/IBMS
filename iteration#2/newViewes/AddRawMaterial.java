package newViewes;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AddRawMaterial extends JDialog {
	private JTable table;

	public AddRawMaterial() {

		setSize(400, 140);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton cancelButton = new JButton("انصراف");
		panel.add(cancelButton);
		
		JButton okButton = new JButton("تایید");
		panel.add(okButton);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);

	}

	public static void main(String[] args) {
		new AddRawMaterial();
	}

}
