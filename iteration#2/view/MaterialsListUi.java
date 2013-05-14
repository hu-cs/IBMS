package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.ComponentOrientation;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JPanel;
import javax.swing.JButton;
import model.Material;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialsListUi extends JFrame {
	public static JTable materialTable;
	ArrayList<Material> materialArray = new ArrayList<Material>();
	java.sql.Connection connection;
	String name;
	String unit;
	String details;
	float finishedCost;
	float sellCost;

	public MaterialsListUi() throws ClassNotFoundException, SQLException {
		TableColumn a = materialTable.getColumn("Name");
		int n = a.getModelIndex();
		// ((DefaultTableColumnModel)materialTable.getColumnModel()).getColumn(2);
		JScrollPane materialScroll = new JScrollPane();
		getContentPane().add(materialScroll, BorderLayout.CENTER);

		materialTable = new JTable();
		materialTable
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		materialTable
				.setModel(new DefaultTableModel(
						new Object[][] {},
						new String[] {
								"\u0642\u06CC\u0645\u062A \u0641\u0631\u0648\u0634",
								"\u0642\u06CC\u0645\u062A \u062A\u0645\u0627\u0645 \u0634\u062F",
								"\u0648\u0627\u062D\u062F \u067E\u06CC\u0645\u0627\u06CC\u0634",
								"\u0646\u0627\u0645 \u062C\u0646\u0633",
								"\u0634\u0645\u0627\u0631\u0647" }) {
					boolean[] columnEditables = new boolean[] { false, false,
							false, false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		materialTable.getColumnModel().getColumn(0).setPreferredWidth(81);
		materialTable.getColumnModel().getColumn(1).setPreferredWidth(92);
		materialTable.getColumnModel().getColumn(2).setPreferredWidth(82);
		materialTable.getColumnModel().getColumn(3).setPreferredWidth(97);
		// updateMaterialAndStockLists();

		materialTable.setDefaultRenderer(Object.class,
				new DefaultTableCellRenderer() {
					{
						setHorizontalAlignment(SwingConstants.RIGHT);
					}
				});

		materialScroll.setViewportView(materialTable);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton addingNewMaterial = new JButton("اضافه نمودن جنس جدید");
		panel.add(addingNewMaterial);
		addingNewMaterial.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddingNewMaterial();

			}
		});

		JButton editAndSeeDetails = new JButton("ویرایش و دیدن جزئیات");
		panel.add(editAndSeeDetails);

		JButton cancelButton = new JButton("انصراف");
		panel.add(cancelButton);

//		Material.updateMaterialAndStockLists(materialTable, 2013);

		setSize(545, 435);
		JButton okButton = new JButton("تائید");
		panel.add(okButton);
		setTitle("لیست اجناس شرکت");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		new MaterialsListUi();

	}

	// private void updateMaterialAndStockLists() throws ClassNotFoundException
	// {
	// Class.forName("com.mysql.jdbc.Driver");
	// try {
	// connection = DriverManager.getConnection(
	// "jdbc:mysql://127.0.0.1/test?characterEncoding=UTF-8",
	// "root", "");
	// } catch (SQLException e) {
	//
	// e.printStackTrace();
	// }
	//
	// try {
	// java.sql.PreparedStatement statement = connection
	// .prepareStatement("select Name,Unit,finishedCost,sellCost,Details from "
	// + "material where Year ='2013'");
	// ResultSet result = statement.executeQuery();
	//
	// while (result.next()) {
	// name = result.getString(1);
	// unit = result.getString(2);
	// finishedCost = result.getFloat(3);
	// sellCost = result.getFloat(4);
	// details = result.getString(5);
	// materialArray.add(new Material(name, unit, details,
	// finishedCost, sellCost));
	// }
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// ((DefaultTableModel) materialTable.getModel()).setRowCount(0);
	//
	// for (Material tempMaterial : materialArray) {
	// ((DefaultTableModel) materialTable.getModel()).addRow(new String[] {
	// tempMaterial.getSellCost() + "",
	// tempMaterial.getFinishedCost() + "",
	// tempMaterial.getUnit(), tempMaterial.getName(),
	// materialTable.getRowCount() + 1 + "" });
	// }
	//
	// materialTable.repaint();
	// materialTable.revalidate();
	// }

}
