package view;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
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

import model.Material;

import ui.renderers.FactorFroshRenderer;

import java.awt.FlowLayout;

public class StoringToStock extends JDialog {
	private JTable table;
	JComboBox<Material> materialList = new JComboBox<Material>();

	public StoringToStock() {

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
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
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"\u062A\u0627\u0631\u06CC\u062E", "\u0645\u0642\u062F\u0627\u0631", "\u0646\u0627\u0645 \u062C\u0646\u0633"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(65);
		table.getColumnModel().getColumn(2).setPreferredWidth(89);
		table.getColumnModel().getColumn(1)
				.setCellEditor(new FactorFroshRenderer(materialList));
		table.getColumnModel().getColumn(1)
				.setCellRenderer(new FactorFroshRenderer(materialList));
		table.setRowHeight(18);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton("انصراف");
		panel.add(button_1);

		JButton button = new JButton("تائید");
		panel.add(button);

		setSize(340, 107);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("افزودن جنس به انبار");
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new StoringToStock();
	}

}
