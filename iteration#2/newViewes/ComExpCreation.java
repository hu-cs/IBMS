package newViewes;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.FlowLayout;
import java.util.EventObject;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.JXDatePicker;

import controler.expenses.AddUsedItem;

public class ComExpCreation extends JDialog {
	private JTable table;
	JXDatePicker datePicker = new JXDatePicker();

	public ComExpCreation() {

		// if(datePicker.getDate()!=null)

		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("انصراف");
		buttonPanel.add(cancelButton);

		JButton okButton = new JButton("تایید");
		buttonPanel.add(okButton);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null }, }, new String[] { "\u062A\u0627\u0631\u06CC\u062E",
				"\u0645\u0628\u0644\u063A",
				"\u0634\u0645\u0627\u0631\u0647 \u0642\u0628\u0636" }));
		table.setRowHeight(21);
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

		okButton.addActionListener(new AddUsedItem(table, this));

		setTitle("افزودن مورد جدید");
		setResizable(false);
		setModalityType(ModalityType.DOCUMENT_MODAL);
		setSize(418, 109);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ComExpCreation();
	}

	public class TableEditor extends AbstractCellEditor implements
			TableCellEditor {

		@Override
		public Object getCellEditorValue() {

			datePicker.getDate().setTime(System.currentTimeMillis());
			return datePicker.getDate().toLocaleString();
		}

		@Override
		public boolean isCellEditable(EventObject anEvent) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean shouldSelectCell(EventObject anEvent) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {

			return datePicker;
		}

	}

}
