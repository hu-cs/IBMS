package view;

import java.awt.*;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.plaf.DatePickerUI;
import ui.renderers.TableEditor;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.mysql.jdbc.PreparedStatement;

import connection.DBConnection;
import controller.AddRowButtonListener;
import controller.CancelButtonListener;
import controller.ComboboxListener;
import controller.CompanyCharactristics;
import controller.DataYearId;
import controller.DeleteRow;
import controller.InvoiceChanges;
import controller.PrintBtn;
import controller.QuantityChangeListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import model.Customer;
import model.Invoice;
import model.Material;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import com.jgoodies.forms.layout.Sizes;

public class InvoiceUi extends JFrame {

	private JTable table;
	TableModel tableModel = new DefaultTableModel(new String[][] { {}, {}, {},
			{}, { "اتنخاب حنس" }, { "1" } }, new String[] { "NO.", "Name",
			"Unit", "Quantity" });

	JLabel invocieNumebrLabel;
	JComboBox<Customer> customerComboBox;
	JComboBox<Material> materialList;
	JXDatePicker datePicker;

	public InvoiceUi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/Icon/LPGO.png")));
		updateMaterialCombobox();

		// Date and label

		JPanel buttonsPanel = new JPanel();

		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		buttonsPanel.add(panel, BorderLayout.WEST);
		JButton print = new JButton("چاپ فاکتور");
		print.setIcon(new ImageIcon(getClass().getResource("/Icon/print.png")));

		panel.add(print);

		JPanel panel_1 = new JPanel();
		buttonsPanel.add(panel_1, BorderLayout.EAST);

		JButton deleteRowButton = new JButton("پاک کردن ردیف");
		deleteRowButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_delete.png")));
		panel_1.add(deleteRowButton);

		JButton addRowButton = new JButton("افزودن ردیف");
		addRowButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/File_add.png")));
		panel_1.add(addRowButton);

		JButton cancelButton = new JButton("انصراف");
		cancelButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Cancel.png")));
		panel_1.add(cancelButton);

		JButton okButton = new JButton("تائید");
		okButton.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/Accept.png")));
		panel_1.add(okButton);

		JPanel invoiceTotalPanel = new JPanel();
		invoiceTotalPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		buttonsPanel.add(invoiceTotalPanel, BorderLayout.NORTH);
		invoiceTotalPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("73px"),
				ColumnSpec.decode("max(98dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, ColumnSpec.decode("144px"), },
				new RowSpec[] { RowSpec.decode("max(4dlu;default)"),
						RowSpec.decode("max(17dlu;default)"), }));

		JLabel invoiceCurrencyLabel = new JLabel("");
		invoiceCurrencyLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/af.png")));
		invoiceTotalPanel.add(invoiceCurrencyLabel, "1, 2, right, default");

		JLabel invoiceTotalValueLabel = new JLabel("0");
		invoiceTotalPanel.add(invoiceTotalValueLabel, "2, 2, center, center");

		JLabel invoiceTotalLabel = new JLabel("جمع کل:");
		invoiceTotalLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/total_plan_cost.png")));
		invoiceTotalPanel.add(invoiceTotalLabel, "4, 2, left, top");

		JPanel northenPanel = new JPanel();
		getContentPane().add(northenPanel, BorderLayout.NORTH);
		northenPanel.setLayout(new BorderLayout(0, 0));

		JPanel companyDescriptionPanel = new JPanel();
		companyDescriptionPanel.setOpaque(false);
		northenPanel.add(companyDescriptionPanel, BorderLayout.NORTH);
		companyDescriptionPanel.setLayout(new BorderLayout(0, 0));

		JPanel companyIdentification = new JPanel();
		companyDescriptionPanel.add(companyIdentification, BorderLayout.EAST);
		companyIdentification.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(85dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(65dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("13px"), }, new RowSpec[] {
				RowSpec.decode("max(7dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
				RowSpec.decode("max(26dlu;default)"),
				new RowSpec(RowSpec.CENTER, Sizes.bounded(Sizes.DEFAULT,
						Sizes.constant("7dlu", false),
						Sizes.constant("15px", false)), 1), }));

		JLabel addressDescription = new JLabel();
		addressDescription.setText(CompanyCharactristics.getAddress());
		addressDescription.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(addressDescription,
				"1, 2, 3, 1, right, default");

		JLabel addressLabel = new JLabel("آدرس:");
		addressLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(addressLabel, "5, 2, right, top");

		JLabel tellLabel = new JLabel();
		tellLabel.setText(CompanyCharactristics.getTell());
		tellLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyIdentification.add(tellLabel, "1, 4, right, fill");

		JLabel mobileLabel = new JLabel();
		mobileLabel.setText(CompanyCharactristics.getPhone());
		mobileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyIdentification.add(mobileLabel, "3, 4, right, fill");

		JLabel callNumberLabel = new JLabel("شماره تماس:");
		callNumberLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(callNumberLabel, "5, 4, right, default");
		// for (int counter = 0; counter < Customer.customerList.size();
		// counter++) {
		// customerComboBox.addItem(Customer.customerList.get(counter));
		// }

		JLabel label_3 = new JLabel(CompanyCharactristics.getFax());
		companyIdentification.add(label_3, "3, 6, right, default");

		JLabel label_2 = new JLabel("فکس :");
		label_2.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(label_2, "5, 6, right, default");

		JLabel lblWwwshokufabaharcom = new JLabel(
				CompanyCharactristics.getWebSite());
		companyIdentification.add(lblWwwshokufabaharcom,
				"1, 8, 3, 1, right, default");

		JLabel label = new JLabel("وب سایت :");
		label.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(label, "5, 8, right, default");

		JLabel lblInfoshokufabarahcom = new JLabel(
				CompanyCharactristics.getEmail());
		companyIdentification.add(lblInfoshokufabarahcom,
				"1, 10, 3, 1, right, default");

		JLabel label_1 = new JLabel("ایمیل :");
		label_1.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(label_1, "5, 10, right, default");

		JPanel invoiceNumberPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) invoiceNumberPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(10);
		companyIdentification.add(invoiceNumberPanel, "1, 11, fill, default");

		invocieNumebrLabel = new JLabel("");
		invoiceNumberPanel.add(invocieNumebrLabel);

		JLabel invoiceLabel = new JLabel("شماره فاکتور");
		invoiceNumberPanel.add(invoiceLabel);

		customerComboBox = new JComboBox<Customer>();

		// Setting action listener

		// customerComboBox.addInputMethodListener(l)
		// customerComboBox.add
		companyIdentification.add(customerComboBox, "3, 11, center, center");

		JLabel companyNameLabel = new JLabel(CompanyCharactristics.getName());
		companyNameLabel.setPreferredSize(new Dimension(158, 25));
		companyNameLabel.setFont(new Font("2  Farnaz", Font.PLAIN, 16));
		companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyDescriptionPanel.add(companyNameLabel, BorderLayout.NORTH);

		JPanel imageAndDatePanel = new JPanel();
		imageAndDatePanel.setOpaque(false);
		companyDescriptionPanel.add(imageAndDatePanel, BorderLayout.WEST);
		imageAndDatePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("max(9dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;default)"), }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("128px"),
				FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("32px"), }));

		JLabel logoLabel = new JLabel("");
		imageAndDatePanel.add(logoLabel, "4, 2, left, top");
		logoLabel.setAlignmentY(5.0f);
		logoLabel.setAlignmentX(5.0f);
		logoLabel.setIcon(new ImageIcon(CompanyCharactristics.getLogo()));
		JPanel invoiceTablePanel = new JPanel(new BorderLayout());
		// ============================================

		JScrollPane scrollPane = new JScrollPane();
		invoiceTablePanel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null,
				null, null, null, "1" }, }, new String[] {
				"\u0645\u0628\u0644\u063A",
				"\u0641\u06CC \u0648\u0627\u062D\u062F",
				"\u0648\u0627\u062D\u062F", "\u0645\u0642\u062F\u0627\u0631",
				"\u0646\u0627\u0645 \u062C\u0646\u0633",
				"\u0634\u0645\u0627\u0631\u0647" }) {
			boolean[] columnEditables = new boolean[] { false, false, false,
					true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(131);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(48);
		table.setRowHeight(24);
		scrollPane.setViewportView(table);

		TableColumn nameColumn = table.getColumnModel().getColumn(4);
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

		northenPanel.add(invoiceTablePanel, BorderLayout.SOUTH);
		nameColumn.setCellEditor(new DefaultCellEditor(materialList));
		final JComboBox<Material> tempCombobox = new JComboBox<Material>();
		for (int i = 0; i < materialList.getModel().getSize(); i++) {
			tempCombobox.addItem(materialList.getItemAt(i));
		}

		table.addPropertyChangeListener(new QuantityChangeListener(
				invoiceTotalValueLabel, table));

		addRowButton.addActionListener(new AddRowButtonListener(table, this));
		deleteRowButton.addActionListener(new DeleteRow(table, this,
				invoiceTotalValueLabel));
		cancelButton.addActionListener(new CancelButtonListener(this));

		datePicker = new JXDatePicker();
		datePicker.getMonthView().setBackground(Color.black);
		datePicker.getMonthView().setForeground(Color.yellow);

		datePicker.setForeground(Color.yellow);
		datePicker.setBackground(Color.red);
		imageAndDatePanel.add(datePicker, "4, 4");

		JLabel dateLabel = new JLabel(" تاریخ:  ");
		dateLabel.setIconTextGap(0);
		dateLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/date_add.png")));
		imageAndDatePanel.add(dateLabel, "6, 4");

		for (int a = 0; a < materialList.getModel().getSize(); a++) {
			System.out.println(materialList.getItemAt(a));
		}
		materialList
				.addActionListener(new ComboboxListener(table, materialList));

		// =========================================================
		okButton.addActionListener(new InvoiceChanges(invocieNumebrLabel,
				datePicker, invoiceTotalValueLabel, customerComboBox, table,
				this));

		print.addActionListener(new PrintBtn(table, datePicker,
				customerComboBox, invocieNumebrLabel, invoiceTotalValueLabel,
				this));

		JLabel customerLabel = new JLabel("مشتری محترم:");
		customerLabel.setIcon(new ImageIcon(getClass().getResource(
				"/Icon/user_gray.png")));
		customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(customerLabel, "5, 11, right, center");

		updateComboBox();
		setInvoiceNumber();
		setSize(900, 368);
		setIconImage(new ImageIcon(getClass().getResource("/Icon/LPGO.png"))
				.getImage());
		setLocationRelativeTo(null);
		setTitle("Invoice");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void updateComboBox() {
		Customer.customerList = null;
		Customer.customerList = new ArrayList<Customer>();
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT Name,Last_Name,Father_Name,"
							+ "Address,Shopping_Account,Giving_Money_Account"
							+ ",Company_demand FROM customer_list where DataYearId ="
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String name = result.getString(1);
				String lastName = result.getString(2);
				String fatherName = result.getString(3);
				String address = result.getString(4);
				float shopping = result.getFloat(5);
				float payment = result.getFloat(6);
				float companyDemand = result.getFloat(7);

				Customer newCustomer = new Customer(name, lastName, fatherName,
						address, shopping, payment, companyDemand);

				Customer.customerList.add(newCustomer);
				customerComboBox.addItem(newCustomer);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setInvoiceNumber() {
		int invoiceNumber = 1;
		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("SELECT MAX(InvoiceNumber) FROM invoice_list");

			ResultSet result = statement.executeQuery();
			while (result.next()) {
				invoiceNumber = result.getInt(1);
			}

			invocieNumebrLabel.setText(invoiceNumber + 1 + "");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateMaterialCombobox() {
		materialList = new JComboBox<Material>();
		String name;
		String unit;
		float finishedCost;
		float sellCost;
		Material.materialArray = null;
		Material.materialArray = new ArrayList<Material>();
		Material loadedMaterial;

		try {
			PreparedStatement statement = (PreparedStatement) DBConnection.connection
					.prepareStatement("select name,unit,finishedCost,sellCost from materiallist where dataYearid = "
							+ DataYearId.getDataYearId());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				name = result.getString(1);
				unit = result.getString(2);
				finishedCost = result.getFloat(3);
				sellCost = result.getFloat(4);
				loadedMaterial = new Material(name, unit, finishedCost,
						sellCost);
				Material.materialArray.add(loadedMaterial);
				materialList.addItem(loadedMaterial);

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

		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new InvoiceUi();
		// JComboBox<Material> a = new JComboBox<Material>();

	}

}
