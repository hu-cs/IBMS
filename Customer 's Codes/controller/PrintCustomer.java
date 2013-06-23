package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.JToggleButton;

import view.CustomersList;

public class PrintCustomer implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		print();
		table.setBackground(new Color(52, 55, 59, 255));
		table.setForeground(Color.WHITE);

	}

	JTable table;
	JToggleButton all;
	JToggleButton dept;
	JToggleButton payed;

	public PrintCustomer(JTable table, JToggleButton all, JToggleButton dept,
			JToggleButton payed) {
		this.table = table;
		this.payed = payed;
		this.dept = dept;
		this.all = all;
	}

	private void print() {
		
		String status = "";
		if(all.isSelected()){
			status=CustomersList.All;
		}else if(dept.isSelected()){
			status = CustomersList.DeptCustomer;
		}else{
			status = CustomersList.CustomerPaid;
		}

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

		String headerString = CustomersList.CustomersList+" ("+status+") "
				+ CompanyCharactristics.getName() + CustomersList.AtYear
				+ DataYearId.getDataYearId();
		String footerString = "صفحه {0,number,integer}                                            "
				+ CustomersList.Date + "  "+date + CustomersList.Hour +"   "+ time;
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
