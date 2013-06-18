package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.plaf.FontUIResource;

import controller.CompanyCharactristics;
import controller.DataYearId;

public class PrintExpenceList implements ActionListener {

	JTable table;

	String dataYearId;
	String companyName;
	JComboBox<String> typeList;
	String date;
	String time;
	String consumptionClass;

	public PrintExpenceList(JTable table, JComboBox<String> typeList,
			String consumptionClass) {
		this.table = table;
		this.consumptionClass = consumptionClass;
		this.dataYearId = DataYearId.getDataYearId() + "";
		companyName = CompanyCharactristics.getName();
		// companyName = "شکوفه بهار";
		this.typeList = typeList;
		// this.date =
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		// int date = cal.get(Calendar.DATE);
		int houre = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);

		this.date = day + "/" + month + "/" + year;
		this.time = houre + ":" + minute + ":" + second;
	}

	private void print() {

		table.setBackground(Color.white);
		table.setForeground(Color.black);

		float totalValue = 0;

		for (int counter = 0; counter < table.getRowCount(); counter++) {
			totalValue += Float.parseFloat(table.getValueAt(counter, 1)
					.toString());
		}

		String total = totalValue + "  افغانی ";

		String headerString = consumptionClass + "  ( "
				+ typeList.getSelectedItem().toString() + ") شرکت "
				+ companyName + " در سال " + dataYearId;

		String footerString = " تاریخ   " + date + "     ساعت     " + time;

		MessageFormat footer = new MessageFormat(
				"صفحه {0,number,integer}                                      "
						+ footerString + "                     "
						+ "    کل مصارف : " + total);

		MessageFormat header = new MessageFormat(headerString);

		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		print();
		table.setForeground(Color.white);
		table.setBackground(new Color(52, 55, 59, 255));

	}

}
