package ui.renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.JXDatePicker;

public class ComAddItemRenderer extends AbstractCellEditor implements
		TableCellEditor {

	JXDatePicker date = new JXDatePicker();

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		super.addCellEditorListener(l);

	}

	@Override
	public Object getCellEditorValue() {
		date.getMonthView().setBackground(Color.black);
		date.getMonthView().setForeground(Color.yellow);
		date.getDate().setTime(System.currentTimeMillis());
		return date.getDate().toLocaleString();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// TODO Auto-generated method stub
		return date;
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

}
