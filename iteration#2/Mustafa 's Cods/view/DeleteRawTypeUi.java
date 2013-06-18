package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DeleteUsedTypeListener;

public class DeleteRawTypeUi extends JDialog {

	private JTable table;
	String dbTable = "raw_material";

	public DeleteRawTypeUi() {
		// TODO Auto-generated constructor stub

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton button_1 = new JButton(CompanyUsed.cancel);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});

		panel.add(button_1);

		JButton button = new JButton(CompanyUsed.okText);

		panel.add(button);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"نوع مواد", "ردیف" }) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(68);

		loadData();
		button.addActionListener(new DeleteUsedTypeListener(table, this,
				dbTable, RawMaterial.selectTypeCombo, RawMaterial.typeList));

		setModalityType(ModalityType.DOCUMENT_MODAL);

		setSize(251, 300);
		setTitle("حذف نوع مواد");
		setLocationRelativeTo(null);
		scrollPane.setViewportView(table);
		setVisible(true);
	}

	private void loadData() {
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select distinct Title from " + dbTable);

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				type = result.getString(1);
				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						type, table.getRowCount() + 1 });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DeleteRawTypeUi();

	}

}
