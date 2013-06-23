package ui.renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXDatePicker;

public class TableEditor extends AbstractCellEditor implements TableCellEditor {

	JXDatePicker datePicker;
	JXDatePicker renderedDatePicker;

	public TableEditor() {
		datePicker = new JXDatePicker();
		renderedDatePicker = new JXDatePicker();

	}

	@Override
	public Object getCellEditorValue() {

		if (datePicker.getDate() == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());

		datePicker.getDate().setHours(cal.get(Calendar.HOUR));
		datePicker.getDate().setMinutes(cal.get(Calendar.MINUTE));
		datePicker.getDate().setSeconds(cal.get(Calendar.SECOND));

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
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		datePicker.getMonthView().setBackground(Color.black);
		datePicker.getMonthView().setForeground(Color.yellow);

		return datePicker;
	}

	// @Override
	// public Component getTableCellRendererComponent(JTable table, Object
	// value,
	// boolean isSelected, boolean hasFocus, int row, int column) {
	//
	// return datePicker.getDate().toLocaleString();
	// ;
	// }

}
