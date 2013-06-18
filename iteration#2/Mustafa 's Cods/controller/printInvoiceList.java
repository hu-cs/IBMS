package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

public class printInvoiceList implements ActionListener {

	JTable table;

	public printInvoiceList(JTable table) {
		this.table = table;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		print();
		table.setForeground(Color.white);
		table.setBackground(new Color(52, 55, 59, 255));

	}

	private void print() {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// int date = cal.get(Calendar.DATE);
		int houre = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		String date = day + "/" + month + "/" + year;
		String time = houre + ":" + minute + ":" + second;

		String name = CompanyCharactristics.getName();
		String dateYearId = DataYearId.getDataYearId() + "";

		String head = "  لیست فاکتور های فروش شرکت " + name + "  در سال  "
				+ dateYearId;
		String footerString = " تاریخ   " + date + "    ساعت     " + time;

		MessageFormat footer = new MessageFormat(
				"صفحه {0,number,integer}                                               "
						+ footerString);

		MessageFormat header = new MessageFormat(head);

		table.setForeground(Color.black);
		table.setBackground(Color.white);

		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
