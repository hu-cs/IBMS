package view;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.imagine.component.calendar.CalendarComponent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import connection.DBConnection;
import controller.DataYearId;

public class MainUi extends JFrame {
	private JTable table;
	public static JLabel consumptionValueLbl;
	public static JLabel saleValuesLbl;
	public static JLabel currentMonyValueLbl;
	public static JLabel debtValesLbl;

	public MainUi() {

		JMenuBar menuBar = new JMenuBar();
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setJMenuBar(menuBar);

		JMenu menu_2 = new JMenu("فاکتور");
		menu_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menu_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menu_2);

		JMenu stockMenu = new JMenu("حساب اجناس و انبار");
		stockMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		stockMenu.setHorizontalTextPosition(SwingConstants.RIGHT);
		stockMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(stockMenu);

		JMenuItem materialListMenuItem = new JMenuItem("لیست اجناس");
		materialListMenuItem
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(materialListMenuItem);

		JSeparator separator_10 = new JSeparator();
		stockMenu.add(separator_10);

		JMenuItem menuItem_4 = new JMenuItem("لیست موجودی انبار");
		menuItem_4.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		stockMenu.add(menuItem_4);
		menuItem_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// new StockGui();
			}
		});

		JMenu menu = new JMenu("حساب مشتریان");
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(menu);

		JMenuItem menuItem_7 = new JMenuItem("لیست مشتریان");
		menu.add(menuItem_7);

		JMenuItem menuItem_8 = new JMenuItem("لیست فاکتور ها");
		menu.add(menuItem_8);

		JMenu compantExpenditureMenu = new JMenu("حساب مصارف");
		compantExpenditureMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		compantExpenditureMenu
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menuBar.add(compantExpenditureMenu);

		JMenuItem menuItem = new JMenuItem("مصارف شرکت");
		menuItem.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("مصارف استهلاک");
		menuItem_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(menuItem_1);

		JMenuItem menuItem_2 = new JMenuItem("مواد اولیه");
		menuItem_2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		compantExpenditureMenu.add(menuItem_2);

		JMenu menu_1 = new JMenu("تنظیمات");
		menu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_1);

		JMenuItem menuItem_3 = new JMenuItem("تنظیمات شرکت");
		menuItem_3.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu_1.add(menuItem_3);

		JMenuItem menuItem_5 = new JMenuItem("تنظیمات حساب سال");
		menuItem_5.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu_1.add(menuItem_5);

		JMenuItem menuItem_6 = new JMenuItem("تنظیمات حساب کاربری");
		menuItem_6.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		menu_1.add(menuItem_6);

		// load();

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("2  Kamran", Font.PLAIN, 11));
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null }, }, new String[] {
				"\u0645\u0642\u062F\u0627\u0631", "\u0648\u0627\u062D\u062F",
				"\u0646\u0627\u0645 \u0645\u062D\u0635\u0648\u0644",
				"\u0631\u062F\u06CC\u0641" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(37);
		scrollPane.setViewportView(table);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel logoLabel = new JLabel("");
		logoLabel.setIcon(new ImageIcon(getClass().getResource("/icon/Backup_of_banner.png")));
		panel_5.add(logoLabel, BorderLayout.CENTER);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("11dlu"), FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("max(4dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		CalendarComponent calendar = new CalendarComponent();
		panel_3.add(calendar, "2, 2");

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(5dlu;default):grow"),
				ColumnSpec.decode("110px:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("32px:grow"),
				ColumnSpec.decode("max(33dlu;default)"),
				ColumnSpec.decode("max(11dlu;default)"), },
				new RowSpec[] { RowSpec.decode("167px:grow"), }));

		JLabel timeLabel = new JLabel("");
		timeLabel.setFont(new Font("2  Kamran", Font.PLAIN, 38));
		timeLabel.setForeground(Color.YELLOW);
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		panel_4.add(timeLabel, "2, 1, left, fill");

		JLabel pmLabel = new JLabel("");
		pmLabel.setForeground(Color.YELLOW);
		pmLabel.setFont(new Font("2  Kamran", Font.PLAIN, 38));
		pmLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(pmLabel, "4, 1, 2, 1, center, fill");

		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(12dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(7dlu;default)"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"), RowSpec.decode("8dlu"),
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(8dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(6dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"), }));

		JLabel label_4 = new JLabel("گــــــا و صــــندوق");
		label_4.setFont(new Font("2  Hamid", Font.BOLD, 18));
		panel_2.add(label_4, "4, 2, 3, 1, center, fill");

		JLabel consumptionIcon = new JLabel("");
		consumptionIcon.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/af.png")));
		panel_2.add(consumptionIcon, "2, 4");

		consumptionValueLbl = new JLabel("0");
		panel_2.add(consumptionValueLbl, "4, 4, center, fill");

		JLabel label = new JLabel("کل مصارف");
		panel_2.add(label, "6, 4, right, fill");

		JLabel saleIcon = new JLabel("");
		saleIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/af.png")));
		panel_2.add(saleIcon, "2, 8");

		saleValuesLbl = new JLabel("0");
		panel_2.add(saleValuesLbl, "4, 8, center, fill");

		JLabel label_1 = new JLabel("کل فروشات");
		panel_2.add(label_1, "6, 8, right, fill");

		JLabel currentIcon = new JLabel("");
		currentIcon.setIcon(new ImageIcon(getClass()
				.getResource("/Icon/af.png")));
		panel_2.add(currentIcon, "2, 12");

		currentMonyValueLbl = new JLabel("0");
		panel_2.add(currentMonyValueLbl, "4, 12, center, fill");

		JLabel label_2 = new JLabel("موجودی شرکت");
		panel_2.add(label_2, "6, 12, right, fill");

		JLabel debtIcon = new JLabel();
		debtIcon.setIcon(new ImageIcon(getClass().getResource("/Icon/af.png")));

		panel_2.add(debtIcon, "2, 16");

		debtValesLbl = new JLabel("0");
		panel_2.add(debtValesLbl, "4, 16, center, fill");

		JLabel label_3 = new JLabel("بدهکاری شرکت");
		panel_2.add(label_3, "6, 16, right, fill");

		setLablesValues();
		loadValues();
		setTime(timeLabel, pmLabel);

		System.out.println("Data year id " + DataYearId.getDataYearId());

		// setSize(1366, 768);
		setSize(1024, 748);
		setMinimumSize(new Dimension(1024, 675));
		setLocationRelativeTo(null);
		setTitle("حسابداری میرزا ");
	}

	private void load() {

		String name;
		int quantity;
		String unit;

		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT Name, Quantity, Unit FROM  stock");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				name = result.getString(1);
				quantity = result.getInt(2);
				unit = result.getString(3);
				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						unit, quantity, name, table.getRowCount() + 1 });

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel(new com.jtattoo.plaf.noire.NoireLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		new MainUi();
	}

	private void setLablesValues() {

		float consumption = 0;
		float sale = 0;
		float currentMony = 0;
		float dept = 0;
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT consumption,sale,current_mony,companyDept FROM `cash_box` WHERE datayearId ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				consumption = result.getFloat(1);
				sale = result.getFloat(2);
				currentMony = result.getFloat(3);
				dept = result.getFloat(4);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		consumptionValueLbl.setText(consumption + "");
		saleValuesLbl.setText(sale + "");
		currentMonyValueLbl.setText(currentMony + "");
		debtValesLbl.setText(dept + "");

	}

	private void loadValues() {
		((DefaultTableModel) table.getModel()).setRowCount(0);

		String name = "";
		String unit = "";
		float qty = 0;
		try {
			PreparedStatement statement = DBConnection.connection
					.prepareStatement("SELECT name,quantity,unit FROM `stock` WHERE datayearid ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				qty = result.getFloat(2);
				unit = result.getString(3);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						qty, unit, name, table.getRowCount() + 1 });

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setTime(final JLabel timeLabel, final JLabel pmLabel) {

		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(System.currentTimeMillis());

				int hour = cal.get(Calendar.HOUR);
				int minute = cal.get(Calendar.MINUTE);
				int second = cal.get(Calendar.SECOND);

				int isPm = cal.get(Calendar.AM_PM);

				String am_pm = "";
				if (isPm == 0) {
					am_pm = "صبح";
				} else {
					am_pm = "بعد از ظهر";

				}
				pmLabel.setText(am_pm);

				String time = hour + ":" + minute + ":" + second;
				timeLabel.setText(time);
			}
		});
		timer.start();

	}

}
