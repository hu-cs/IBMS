package view;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

import connection.DBConnection;
import controller.CompanyCharactristics;
import controller.DataYearId;
import controller.LoadInvoicePrint;
import model.Material;

public class LoadInvoice extends JDialog {

	private JTable table;
	TableModel tableModel = new DefaultTableModel(new String[][] { {}, {}, {},
			{}, { "اتنخاب حنس" }, { "1" } }, new String[] { "NO.", "Name",
			"Unit", "Quantity" });

	JLabel invocieNumebrLabel;
	JComboBox<Material> materialList;
	JLabel dateValueLabel;
	JLabel invoiceTotalValueLabel;
	JLabel customerNameLabel;
	int invoiceNumber;

	public LoadInvoice(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;

		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\flower (2).png"));
		// updateMaterialCombobox();
		// for()

		// Date and label

		JPanel buttonsPanel = new JPanel();

		// getContentPane().add(mainPanel, BorderLayout.NORTH);
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
		buttonsPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		buttonsPanel.add(panel, BorderLayout.WEST);
		JButton print = new JButton(CompanyUsed.printText);
		print.setIcon(new ImageIcon(
				"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\PNG\\16x16\\print.png"));

		panel.add(print);

		JPanel panel_1 = new JPanel();
		buttonsPanel.add(panel_1, BorderLayout.EAST);

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
		invoiceCurrencyLabel
				.setIcon(new ImageIcon(
						"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\af.png"));
		invoiceTotalPanel.add(invoiceCurrencyLabel, "1, 2, right, default");

		invoiceTotalValueLabel = new JLabel("0");
		invoiceTotalPanel.add(invoiceTotalValueLabel, "2, 2, center, center");

		JLabel invoiceTotalLabel = new JLabel("جمع کل:");
		invoiceTotalLabel
				.setIcon(new ImageIcon(
						"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\total_plan_cost (2).png"));
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

		JLabel addressLabel = new JLabel(CompanyUsed.address);
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

		JLabel callNumberLabel = new JLabel(CompanyUsed.tell);
		callNumberLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(callNumberLabel, "5, 4, right, default");

		JLabel label_3 = new JLabel(CompanyCharactristics.getFax());
		companyIdentification.add(label_3, "3, 6, right, default");

		JLabel label_2 = new JLabel(CompanyUsed.fax);
		label_2.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(label_2, "5, 6, right, default");

		JLabel lblWwwshokufabaharcom = new JLabel(
				CompanyCharactristics.getWebSite());
		companyIdentification.add(lblWwwshokufabaharcom,
				"1, 8, 3, 1, right, default");

		JLabel label = new JLabel(CompanyUsed.webSite);
		label.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(label, "5, 8, right, default");

		JLabel lblInfoshokufabarahcom = new JLabel(
				CompanyCharactristics.getEmail());
		companyIdentification.add(lblInfoshokufabarahcom,
				"1, 10, 3, 1, right, default");

		JLabel label_1 = new JLabel(CompanyUsed.email);
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
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("8dlu"), RowSpec.decode("max(8dlu;default)"),
				RowSpec.decode("32px"), }));

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
					false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(133);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(5).setPreferredWidth(48);
		table.setRowHeight(24);

		scrollPane.setViewportView(table);

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			{
				setHorizontalAlignment(SwingConstants.RIGHT);
			}
		});

		northenPanel.add(invoiceTablePanel, BorderLayout.SOUTH);
		final JComboBox<Material> tempCombobox = new JComboBox<Material>();

		dateValueLabel = new JLabel("Date");
		imageAndDatePanel.add(dateValueLabel, "4, 5");

		JLabel dateLabel = new JLabel(CompanyUsed.date);
		dateLabel.setIconTextGap(0);
		dateLabel
				.setIcon(new ImageIcon(
						"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\date_add.png"));
		imageAndDatePanel.add(dateLabel, "6, 5");

		customerNameLabel = new JLabel();
		companyIdentification.add(customerNameLabel, "3, 11, right, default");

		JLabel customerLabel = new JLabel("مشتری محترم:");
		customerLabel
				.setIcon(new ImageIcon(
						"D:\\Learnly\\University\\Software Engineering\\IBMS\\Selected icons\\user_gray.png"));
		customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(customerLabel, "5, 11, right, center");

		print.addActionListener(new LoadInvoicePrint(table, dateValueLabel,
				customerNameLabel, invocieNumebrLabel, invoiceTotalValueLabel,
				this));

		int bph = buttonsPanel.getY();
		int dph = companyIdentification.getY();
		int panelHeights = bph + dph;
		int height = loadValues(invoiceNumber);
		System.err.println(bph);
		System.err.println(dph);
		System.err.println(height);

		setModalityType(ModalityType.APPLICATION_MODAL);

		setSize(900, (height * 23) + 360);
		setLocationRelativeTo(null);
		setTitle("Invoice");
		setVisible(true);

	}

	public static void main(String[] args) {
		new LoadInvoice(37);
	}

	private int loadValues(int invoiceNumber) {
		System.err.println("load vales");
		((DefaultTableModel) table.getModel()).setRowCount(0);

		int zarib = 0;
		String name;
		String unit;
		float cost;
		float qty;
		String customerName = "";
		String date = "";
		float totalCost;
		float invoiceTotalCost = 0;

		try {
			java.sql.PreparedStatement statmenet = DBConnection.connection
					.prepareStatement("SELECT name,unit,cost,qty,customer,dateandtime FROM `invoice_materials` WHERE `invoice_num` ="
							+ invoiceNumber
							+ " and data_year_id = "
							+ DataYearId.getDataYearId());

			ResultSet result = statmenet.executeQuery();
			while (result.next()) {

				name = result.getString(1);
				unit = result.getString(2);
				cost = result.getFloat(3);
				qty = result.getFloat(4);
				customerName = result.getString(5);
				date = result.getString(6);
				totalCost = qty * cost;

				System.out.println(name);
				System.out.println(unit);
				System.out.println(cost);
				System.out.println(qty);

				((DefaultTableModel) table.getModel()).addRow(new Object[] {
						totalCost, cost, unit, qty, name,
						table.getRowCount() + 1 });

				invoiceTotalCost += totalCost;
				zarib++;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		dateValueLabel.setText(date);
		invocieNumebrLabel.setText(invoiceNumber + "");
		customerNameLabel.setText(customerName);
		invoiceTotalValueLabel.setText(invoiceTotalCost + "");

		return zarib;
	}

}
