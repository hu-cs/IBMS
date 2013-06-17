package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTable.PrintMode;

import view.CustomersList;

public class PrintDetails implements ActionListener {

	JTable shoppint;
	JTable giving;

	public PrintDetails(JTable shoppint, JTable giving) {
		this.shoppint = shoppint;
		this.giving = giving;
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		print();
		
		giving.setBackground(new Color(52, 55, 59, 255));
		giving.setForeground(Color.WHITE);
		
		shoppint.setBackground(new Color(52, 55, 59, 255));
		shoppint.setForeground(Color.WHITE);
		
	}

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

		String givingHeaderString = "لیست پرداخت " + CustomersList.customerName
				+ " در سال " + DataYearId.getDataYearId();
		String footerString = "صفحه {0,number,integer}                                            "
				+ CustomersList.Date
				+ "  "
				+ date
				+ CustomersList.Hour
				+ "   "
				+ time;
		MessageFormat givingHeader = new MessageFormat(givingHeaderString);
		MessageFormat footer = new MessageFormat(footerString);

		String shoppingheaderString = " لیست خرید " + CustomersList.customerName
				+ " در سال" + DataYearId.getDataYearId();
		
		MessageFormat shoppingHeader = new MessageFormat(shoppingheaderString);
		
		giving.setBackground(Color.WHITE);
		giving.setForeground(Color.black);
		
		shoppint.setBackground(Color.WHITE);
		shoppint.setForeground(Color.black);
		
		try {
			shoppint.print(PrintMode.FIT_WIDTH, shoppingHeader, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			giving.print(PrintMode.FIT_WIDTH, givingHeader, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
