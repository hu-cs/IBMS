package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.DataYearId;
import controller.DeleteUsedTypeListener;

public class DeleteUsedTypeUi extends JDialog {
	private JTable table;
	String dbTable = "corruptionconsumption";

	public DeleteUsedTypeUi() {

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
				"\u0646\u0648\u0639 \u0645\u0635\u0631\u0641",
				"\u0631\u062F\u06CC\u0641" }) {
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
				dbTable, CompanyUsed.selectTypeCombo, CompanyUsed.typeList));
		setModalityType(ModalityType.APPLICATION_MODAL);

		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setSize(251, 300);
		setTitle("حذف نوع مصرف");
		setLocationRelativeTo(null);
		scrollPane.setViewportView(table);
		setVisible(true);
	}

	private void loadData() {
		String type;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select distinct title from corruptionconsumption where datayearid = "
							+ DataYearId.getDataYearId());

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
		new DeleteUsedTypeUi();
	}

}
