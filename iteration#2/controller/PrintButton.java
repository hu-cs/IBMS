package controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXDatePicker;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import view.mainUi;

import javax.swing.border.LineBorder;

public class PrintButton implements ActionListener, Printable {

	JFrame mainFrame;

	JPanel mainPanel;
	String dateString;
	JScrollPane scroll;

	/**
	 * @wbp.parser.entryPoint
	 */
	public PrintButton(JPanel mainPanel, String dateString, JScrollPane scroll) {
		this.scroll = scroll;
		this.mainPanel = mainPanel;
		this.dateString = dateString;
		
		
		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);

		JPanel totalAndSigmaturPanel = new JPanel();
		totalAndSigmaturPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalAndSigmaturPanel.setAlignmentX(SwingConstants.RIGHT);

		// /================================

		JPanel northenPanel = new JPanel(new BorderLayout());

		JPanel companyDescriptionPanel = new JPanel();
		northenPanel.add(companyDescriptionPanel, BorderLayout.NORTH);
		companyDescriptionPanel.setLayout(new BorderLayout(0, 0));

		JPanel companyIdentification = new JPanel();
		companyDescriptionPanel.add(companyIdentification, BorderLayout.EAST);
		companyIdentification
				.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(221dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("13px"),},
			new RowSpec[] {
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
				RowSpec.decode("max(15dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));

		JLabel addressDescription = new JLabel(
				" هرات، شهرک صنعتی، فاز سوم، خیابان مرسل، مرسل1");
		addressDescription.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(addressDescription,
				"1, 2, 3, 1, right, default");
		

		JLabel addressLabel = new JLabel("آدرس:");
		addressLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(addressLabel, "5, 2, right, top");

		JLabel numbersLabel = new JLabel("0799361421 - 0754012722");
		numbersLabel.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(numbersLabel, "1, 4, 3, 1, right, default");

		JLabel callNumberLabel = new JLabel("شماره تماس:");
		callNumberLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(callNumberLabel, "5, 4, right, default");
		
		JPanel theInvoicePanel = new JPanel();
		companyIdentification.add(theInvoicePanel, "1, 13, right, fill");
		theInvoicePanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(122dlu;default)"),
				ColumnSpec.decode("67px"),
				ColumnSpec.decode("27px"),
				ColumnSpec.decode("63px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("14px"),}));
		
		JLabel theDateValueLabel = new JLabel("the date");
		theInvoicePanel.add(theDateValueLabel, "1, 2, center, default");
		
		JLabel dateLabel = new JLabel("تاریخ:");
		theInvoicePanel.add(dateLabel, "2, 2, left, default");
		
		JLabel theInvoiceValueNumber = new JLabel("1");
		theInvoicePanel.add(theInvoiceValueNumber, "3, 2, center, top");
		
		JLabel theInvoiceNumber = new JLabel("شماره فاکتور:");
		theInvoicePanel.add(theInvoiceNumber, "4, 2, right, top");
		
				JComboBox customerComboBox = new JComboBox();
				companyIdentification.add(customerComboBox, "3, 13, right, center");
		
				JLabel customerLabel = new JLabel("مشتری محترم:");
				customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
				companyIdentification.add(customerLabel, "5, 13, right, center");

		JLabel companyNameLabel = new JLabel("شرکت صنایع پلاستیک شکوفه بهار");
		companyNameLabel.setPreferredSize(new Dimension(158, 25));
		companyNameLabel.setFont(new Font("2  Farnaz", Font.PLAIN, 16));
		companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyDescriptionPanel.add(companyNameLabel, BorderLayout.NORTH);

		JPanel imageAndDatePanel = new JPanel();
		companyDescriptionPanel.add(imageAndDatePanel, BorderLayout.WEST);
		imageAndDatePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(140dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
				JLabel logoLabel = new JLabel("");
				imageAndDatePanel.add(logoLabel, "2, 2, 5, 1, center, top");
				logoLabel.setAlignmentY(5.0f);
				logoLabel.setAlignmentX(5.0f);
				logoLabel.setIcon(new ImageIcon("D:\\Company\\ShokufaBaharLogo.png"));

		mainFrame.getContentPane().add(totalAndSigmaturPanel, BorderLayout.SOUTH);
		totalAndSigmaturPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("68px"),
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
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),}));
		
		JLabel invoiceCurrencyLabel = new JLabel("افغانی");
		totalAndSigmaturPanel.add(invoiceCurrencyLabel, "1, 2, center, default");
		
		JLabel invoiceTotalValueLabel = new JLabel("0");
		totalAndSigmaturPanel.add(invoiceTotalValueLabel, "2, 2, center, default");
		
		JLabel invoiceTotalLabel = new JLabel("جمع کل:");
		totalAndSigmaturPanel.add(invoiceTotalLabel, "4, 2");
		
		JLabel companySignatureLabel = new JLabel("محل مهر شرکت");
		totalAndSigmaturPanel.add(companySignatureLabel, "2, 6, center, default");
		
		JLabel customerSignatureLabel = new JLabel("محل مهر یا امضا مشتری");
		totalAndSigmaturPanel.add(customerSignatureLabel, "34, 6");
		
		JPanel panel = new JPanel();
		totalAndSigmaturPanel.add(panel, "6, 10, left, top");
		mainFrame.getContentPane().add(northenPanel, BorderLayout.NORTH);
		mainFrame.getContentPane().add(scroll);
		mainFrame.setSize(757, 460);
		
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private JFrame interfaceCreation() {

		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);

		JPanel datePanel = new JPanel();
		datePanel.setAlignmentX(SwingConstants.RIGHT);
		JLabel date = new JLabel("تاریخ");
		JLabel dateLabel = new JLabel(dateString);

		datePanel.add(date);
		datePanel.add(dateLabel);

		// /================================

		JPanel northenPanel = new JPanel(new BorderLayout());

		JPanel companyDescriptionPanel = new JPanel();
		northenPanel.add(companyDescriptionPanel, BorderLayout.NORTH);
		companyDescriptionPanel.setLayout(new BorderLayout(0, 0));

		JPanel companyIdentification = new JPanel();
		companyDescriptionPanel.add(companyIdentification, BorderLayout.EAST);
		companyIdentification
				.setLayout(new FormLayout(
						new ColumnSpec[] {
								ColumnSpec.decode("max(221dlu;default):grow"),
								FormFactory.RELATED_GAP_COLSPEC,
								ColumnSpec.decode("max(20dlu;default)"),
								FormFactory.RELATED_GAP_COLSPEC,
								FormFactory.DEFAULT_COLSPEC,
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
								RowSpec.decode("max(13dlu;default)"),
								FormFactory.RELATED_GAP_ROWSPEC,
								FormFactory.DEFAULT_ROWSPEC, }));

		JLabel addressDescription = new JLabel(
				" هرات، شهرک صنعتی، فاز سوم، خیابان مرسل، مرسل1");
		addressDescription.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(addressDescription,
				"1, 2, 3, 1, right, default");
		

		JLabel addressLabel = new JLabel("آدرس:");
		addressLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(addressLabel, "5, 2, right, top");

		JLabel numbersLabel = new JLabel("0799361421 - 0754012722");
		numbersLabel.setFont(new Font("2  Nazanin", Font.PLAIN, 13));
		companyIdentification.add(numbersLabel, "1, 4, 3, 1, right, default");

		JLabel callNumberLabel = new JLabel("شماره تماس:");
		callNumberLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(callNumberLabel, "5, 4, right, default");

		JComboBox customerComboBox = new JComboBox();
		companyIdentification.add(customerComboBox, "3, 13, right, center");

		JLabel customerLabel = new JLabel("مشتری محترم:");
		customerLabel.setFont(new Font("2  Narenj", Font.PLAIN, 16));
		companyIdentification.add(customerLabel, "5, 13, right, center");

		JLabel companyNameLabel = new JLabel("شرکت صنایع پلاستیک شکوفه بهار");
		companyNameLabel.setPreferredSize(new Dimension(158, 25));
		companyNameLabel.setFont(new Font("2  Farnaz", Font.PLAIN, 16));
		companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyDescriptionPanel.add(companyNameLabel, BorderLayout.NORTH);

		JPanel imageAndDatePanel = new JPanel();
		companyDescriptionPanel.add(imageAndDatePanel, BorderLayout.WEST);
		imageAndDatePanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				RowSpec.decode("8dlu"), RowSpec.decode("max(11dlu;default)"),
				RowSpec.decode("14px"), }));

		JLabel logoLabel = new JLabel("");
		imageAndDatePanel.add(logoLabel, "4, 2, left, top");
		logoLabel.setAlignmentY(5.0f);
		logoLabel.setAlignmentX(5.0f);
		logoLabel.setIcon(new ImageIcon("D:\\Company\\LOGO.png"));

		mainFrame.getContentPane().add(datePanel, BorderLayout.SOUTH);
		mainFrame.getContentPane().add(northenPanel, BorderLayout.NORTH);
		mainFrame.getContentPane().add(scroll);
		mainFrame.setSize(757, 324);
		// mainFrame.setVisible(true);

		return mainFrame;

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
		mainFrame.printAll(g);

		return PAGE_EXISTS;
	}

	public void actionPerformed(ActionEvent e) {


		interfaceCreation();

		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(this);

		boolean ok = job.printDialog();
//		 mainFrame.setContentPane(mainPanel);
		 mainFrame.setVisible(true);
		if (ok) {
			try {
				job.print();
				mainFrame.setVisible(false);
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
			}
		}
	}

	// public static void main(String[] args) {
	//
	// }

}
