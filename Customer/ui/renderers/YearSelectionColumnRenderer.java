package ui.renderers;

import java.awt.Component;
import java.lang.ProcessBuilder.Redirect;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class YearSelectionColumnRenderer extends DefaultCellEditor implements
		TableCellRenderer {

	JCheckBox rend;

	public YearSelectionColumnRenderer(JCheckBox selectionYear) {
		super(selectionYear);

		rend = new JCheckBox();
		rend.setSelected(selectionYear.isSelected());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		if (value != null && value instanceof Boolean)
			rend.setSelected((Boolean) value);

		return rend;
	}
}
