package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.*;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.jdesktop.swingx.JXDatePicker;

import model.Customer;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PrintBtn implements ActionListener, Printable {

	JTable table;
	JFrame mainFrame;
	JXDatePicker dateString;
	JScrollPane scroll;
	JComboBox<Customer> customerList;
	JLabel invoiceNumber;
	JLabel invoiceTotalValueLabel;
	JFrame frame;
	JPanel mainPanel;

	public PrintBtn(JTable table, JXDatePicker dateString,
			JComboBox<Customer> customerList, JLabel invoiceNumber,
			JLabel invocieTotalValueLabel, JFrame frame) {
		this.table = table;
		// this.mainFrame = mainFrame;
		this.dateString = dateString;
		this.customerList = customerList;
		this.invoiceNumber = invoiceNumber;
		this.invoiceTotalValueLabel = invocieTotalValueLabel;
		this.frame = frame;
	}

	private JFrame interfaceCreation() {

		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		JTable table = new JTable(this.table.getModel());
		table.setBackground(Color.white);
		table.setForeground(Color.black);
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
		scroll = new JScrollPane();
		scroll.setViewportView(table);

		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);

		// JPanel datePanel = new JPanel();
		// datePanel.setAlignmentX(SwingConstants.RIGHT);
		// JLabel date = new JLabel("تاریخ");
		// JLabel dateLabel = new JLabel();
		// dateLabel.setText(dateString.getEditor().getText());

		// datePanel.add(date);
		// datePanel.add(dateLabel);

		// /================================

		JPanel northenPanel = new JPanel(new BorderLayout());
		northenPanel.setOpaque(false);

		JPanel companyDescriptionPanel = new JPanel();
		northenPanel.add(companyDescriptionPanel, BorderLayout.NORTH);
		companyDescriptionPanel.setLayout(new BorderLayout(0, 0));
		companyDescriptionPanel.setOpaque(false);

		JPanel companyIdentification = new JPanel();
		companyIdentification.setOpaque(false);
		companyDescriptionPanel.add(companyIdentification, BorderLayout.EAST);
		companyIdentification.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(221dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("13px"), }, new RowSpec[] {
				RowSpec.decode("max(7dlu;default)"),
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"), RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("14px"), }));

		JLabel addressDescription = new JLabel(
				CompanyCharactristics.getAddress());
		addressDescription.setForeground(Color.black);
		addressDescription.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(addressDescription,
				"1, 2, 3, 1, right, default");

		JPanel theInvoicePanel = new JPanel();
		theInvoicePanel.setOpaque(false);
		companyIdentification.add(theInvoicePanel, "1, 13, right, fill");
		theInvoicePanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						ColumnSpec.decode("max(115dlu;default):grow"),
						ColumnSpec.decode("116px:grow"),
						ColumnSpec.decode("27px:grow"),
						ColumnSpec.decode("63px:grow"), }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("14px"), }));

		try {
			dateString.getDate().setTime(System.currentTimeMillis());
		} catch (NullPointerException e) {
			new JOptionPane().showMessageDialog(null,
					"اطفا تاریخ را انتخاب کنید!");
			return null;
		}

		JLabel theDateValueLabel = new JLabel(dateString.getDate()
				.toLocaleString());
		theDateValueLabel.setForeground(Color.black);
		theInvoicePanel.add(theDateValueLabel, "1, 2, right, default");

		JLabel dateLabel = new JLabel("  تاریخ : ");
		dateLabel.setForeground(Color.black);
		theInvoicePanel.add(dateLabel, "2, 2, left, default");

		JLabel theInvoiceValueNumber = new JLabel(invoiceNumber.getText());
		theInvoiceValueNumber.setForeground(Color.black);
		theInvoicePanel.add(theInvoiceValueNumber, "3, 2, right, default");

		JLabel theInvoiceNumber = new JLabel("شماره فاکتور:");
		theInvoiceNumber.setForeground(Color.black);
		theInvoicePanel.add(theInvoiceNumber, "4, 2, right, top");

		JLabel customerComboBox = new JLabel(customerList.getSelectedItem()
				.toString());
		customerComboBox.setForeground(Color.black);
		companyIdentification.add(customerComboBox, "3, 13, right, center");

		JLabel customerLabel = new JLabel("مشتری محترم:");
		customerLabel.setForeground(Color.black);
		customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(customerLabel, "5, 13, right, center");

		JLabel addressLabel = new JLabel("آدرس:");
		addressLabel.setForeground(Color.black);
		addressLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(addressLabel, "5, 2, right, top");

		JLabel numbersLabel = new JLabel(CompanyCharactristics.getPhone() + " - "
				+ CompanyCharactristics.getTell());
		numbersLabel.setForeground(Color.black);
		numbersLabel.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(numbersLabel, "1, 4, 3, 1, right, default");

		JLabel callNumberLabel = new JLabel("شماره تماس:");
		callNumberLabel.setForeground(Color.black);
		callNumberLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(callNumberLabel, "5, 4, right, default");

		JLabel customerName = new JLabel(customerList.getSelectedItem()
				.toString());
		customerName.setForeground(Color.black);
		companyIdentification.add(customerName, "3, 13, right, center");

		// JLabel customerLabel = new JLabel("مشتری محترم:");
		// customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		// companyIdentification.add(customerLabel, "5, 13, right, center");

		JLabel companyNameLabel = new JLabel(CompanyCharactristics.getName());
		companyNameLabel.setForeground(Color.black);
		companyNameLabel.setPreferredSize(new Dimension(158, 25));
		companyNameLabel.setFont(new Font("2  Farnaz", Font.PLAIN, 16));
		companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyDescriptionPanel.add(companyNameLabel, BorderLayout.NORTH);

		JPanel imageAndDatePanel = new JPanel();
		imageAndDatePanel.setOpaque(false);
		companyDescriptionPanel.add(imageAndDatePanel, BorderLayout.WEST);
		imageAndDatePanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(140dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel logoLabel = new JLabel("");
		imageAndDatePanel.add(logoLabel, "4, 2, left, top");
		logoLabel.setAlignmentY(5.0f);
		logoLabel.setAlignmentX(5.0f);
		logoLabel.setIcon(new ImageIcon(CompanyCharactristics.getLogo()));

		// JLabel theDateValueLabel = new
		// JLabel(dateString.getDate().toLocaleString());
		// imageAndDatePanel.add(theDateValueLabel, "4, 5");

		// JLabel theDate = new JLabel("تاریخ:");
		// imageAndDatePanel.add(theDate, "6, 5");

		JPanel totalAndSigmaturPanel = new JPanel();
		totalAndSigmaturPanel.setOpaque(false);
		totalAndSigmaturPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalAndSigmaturPanel.setAlignmentX(SwingConstants.RIGHT);

		mainFrame.getContentPane().add(totalAndSigmaturPanel,
				BorderLayout.SOUTH);
		totalAndSigmaturPanel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("68px"),
						ColumnSpec.decode("max(94dlu;default)"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("32px"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("20px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"),
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("20px"), }));

		JLabel invoiceCurrencyLabel = new JLabel("افغانی");
		invoiceCurrencyLabel.setForeground(Color.black);
		totalAndSigmaturPanel
				.add(invoiceCurrencyLabel, "1, 2, center, default");

		JLabel invoiceTotalValueLabel = new JLabel(
				this.invoiceTotalValueLabel.getText());
		invoiceTotalValueLabel.setForeground(Color.black);
		totalAndSigmaturPanel.add(invoiceTotalValueLabel,
				"2, 2, center, default");

		JLabel invoiceTotalLabel = new JLabel("جمع کل:");
		invoiceTotalLabel.setForeground(Color.black);
		totalAndSigmaturPanel.add(invoiceTotalLabel, "4, 2");

		JLabel companySignatureLabel = new JLabel("محل مهر شرکت");
		companySignatureLabel.setForeground(Color.black);
		totalAndSigmaturPanel.add(companySignatureLabel,
				"2, 6, center, default");

		JLabel customerSignatureLabel = new JLabel("محل مهر یا امضا مشتری");
		customerSignatureLabel.setForeground(Color.black);
		totalAndSigmaturPanel.add(customerSignatureLabel, "34, 6");

		// mainFrame.add(datePanel, BorderLayout.SOUTH);
		mainPanel.add(northenPanel, BorderLayout.NORTH);
		mainPanel.add(scroll);
		mainPanel.add(totalAndSigmaturPanel, BorderLayout.SOUTH);
		mainPanel.setBorder(new LineBorder(Color.black));

		mainFrame.add(mainPanel);
		mainFrame.setSize(757, (int) (frame.getSize().getHeight() + 55));
		System.out.println(frame.getSize().getHeight()
				+ totalAndSigmaturPanel.getSize().getHeight());
		System.out.println(frame.getSize().getHeight());
		System.out.println(totalAndSigmaturPanel.getHeight());
		// mainFrame.setSize(757, 350);
		// mainFrame.setVisible(true);

		return mainFrame;

	}

	// public static void main(String[] args) {
	//
	// }

	@Override
	public void actionPerformed(ActionEvent e) {

		if (interfaceCreation() != null) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);

			boolean ok = job.printDialog();
			// mainFrame.setContentPane(mainPanel);
			mainFrame.getContentPane().setBackground(Color.WHITE);

			if (ok) {
				try {
					mainFrame.setVisible(true);
					job.print();
					mainFrame.setVisible(false);
				} catch (PrinterException ex) {
					/* The job did not successfully complete */
				}
			}

		}

	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Print the entire visible contents of a java.awt.Frame */
		mainPanel.printAll(g);

		return PAGE_EXISTS;
	}

}
