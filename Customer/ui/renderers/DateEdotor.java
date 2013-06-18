package ui.renderers;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

public class DateEdotor extends AbstractCellEditor implements TableCellEditor {

	JCheckBox selection;

	public DateEdotor(JCheckBox selection) {
		this.selection = selection;
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		// editor.setComp((Comp) value);
		return selection;
	}

	@Override
	public Object getCellEditorValue() {
		return selection.getText();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		return false;
	}

}
