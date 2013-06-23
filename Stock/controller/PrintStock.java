package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import view.StockGui;

public class PrintStock implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		print();
		table.setBackground(new Color(52, 55, 59, 255));
		table.setForeground(Color.WHITE);

	}

	JTable table;

	public PrintStock(JTable table) {
		this.table = table;
	}
	
//	public static String StockListOf = StockGui.bundle.getString("Stock List Of");

	private void print() {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());

		int year = cal.get(Calendar.YEAR);
		int mounth = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);

		String date = day + "/" + mounth + "/" + year;

		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		String time = hour + ":" + minute + ":" + second;

		String headerString = "لیست موجودی انبار شرکت "
				+ CompanyCharactristics.getName() + " در سال "
				+ DataYearId.getDataYearId();
		String footerString = "صفحه {0,number,integer}                                      "
				+ "تاریخ : " + date + "    ساعت: " + time;
		table.setBackground(Color.WHITE);
		table.setForeground(Color.black);
		MessageFormat header = new MessageFormat(headerString);
		MessageFormat footer = new MessageFormat(footerString);

		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);

		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
