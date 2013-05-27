package newViewes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class RawMaterial extends JDialog {

	private JTable table;
	private JTextField searchTextField;

	public RawMaterial() {

		JPanel northenPanel = new JPanel();
		getContentPane().add(northenPanel, BorderLayout.NORTH);
		northenPanel.setLayout(new BorderLayout(0, 0));

		JPanel selectTypePanel = new JPanel();
		FlowLayout fl_selectTypePanel = (FlowLayout) selectTypePanel
				.getLayout();
		fl_selectTypePanel.setHgap(20);
		northenPanel.add(selectTypePanel, BorderLayout.EAST);

		JComboBox selectTypeCombo = new JComboBox();
		selectTypePanel.add(selectTypeCombo);

		JLabel selectTypeLabel = new JLabel("انتخاب نوع مواد");
		selectTypePanel.add(selectTypeLabel);

		JPanel addTypePanel = new JPanel();
		FlowLayout fl_addTypePanel = (FlowLayout) addTypePanel.getLayout();
		fl_addTypePanel.setHgap(20);
		northenPanel.add(addTypePanel, BorderLayout.WEST);

		JButton addTypeButton = new JButton("+");
		addTypePanel.add(addTypeButton);

		JLabel addTypeLabel = new JLabel("ایجاد نوع مصرف");
		addTypePanel.add(addTypeLabel);
		
		JPanel searchPanel = new JPanel();
		FlowLayout fl_searchPanel = (FlowLayout) searchPanel.getLayout();
		fl_searchPanel.setHgap(10);
		fl_searchPanel.setAlignment(FlowLayout.RIGHT);
		northenPanel.add(searchPanel, BorderLayout.CENTER);
		
		searchTextField = new JTextField();
		searchPanel.add(searchTextField);
		searchTextField.setColumns(12);
		
		JLabel searchLabel = new JLabel("جستجوی فاکتور");
		searchPanel.add(searchLabel);

		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"\u062A\u0627\u0631\u06CC\u062E", "\u0645\u0628\u0644\u063A ", "\u0645\u0642\u062F\u0627\u0631", "\u0641\u06CC \u0648\u0627\u062D\u062F", "\u0648\u0627\u062D\u062F", "\u0634\u0645\u0627\u0631\u0647 \u0641\u0627\u06A9\u062A\u0648\u0631", "\u0646\u0648\u0639", "\u0631\u062F\u06CC\u0641"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(97);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(81);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(6).setPreferredWidth(122);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(7).setPreferredWidth(29);
		scrollPane.setViewportView(table);

		JPanel southenPanel = new JPanel();
		southenPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0,
				0, 0)));
		getContentPane().add(southenPanel, BorderLayout.SOUTH);
		southenPanel.setLayout(new BorderLayout(0, 0));

		JPanel totalValuePanel = new JPanel();
		totalValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		southenPanel.add(totalValuePanel, BorderLayout.NORTH);
		totalValuePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("68px"), ColumnSpec.decode("162px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("97px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("5px"), }));

		JLabel currencyLabel = new JLabel("افغانی");
		totalValuePanel.add(currencyLabel, "1, 2, right, top");

		JLabel totalValueLabel = new JLabel("0");
		totalValuePanel.add(totalValueLabel, "2, 2, center, fill");

		JLabel totalLabel = new JLabel("کل مبلغ تادیه شده");
		totalValuePanel.add(totalLabel, "4, 2, fill, fill");

		JPanel buttonPanel = new JPanel();
		southenPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new BorderLayout(0, 0));

		JPanel creationPanel = new JPanel();
		buttonPanel.add(creationPanel, BorderLayout.EAST);

		JButton editButton = new JButton("ویرایش مورد");
		creationPanel.add(editButton);

		JButton deleteButton = new JButton("پاک کردن مورد");
		creationPanel.add(deleteButton);

		JButton addButton = new JButton("افزودن مورد");
		creationPanel.add(addButton);

		JButton okButton = new JButton("تایید");
		creationPanel.add(okButton);

		JPanel printPanel = new JPanel();
		FlowLayout fl_printPanel = (FlowLayout) printPanel.getLayout();
		fl_printPanel.setHgap(10);
		buttonPanel.add(printPanel, BorderLayout.WEST);

		JButton printButton = new JButton("چاپ لیست");
		printPanel.add(printButton);

		setMinimumSize(new Dimension(721, 346));
		setSize(857, 346);
		setTitle("خریداری مواد اولیه");
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new RawMaterial();
	}
}
