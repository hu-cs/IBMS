package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import controller.CompanyCharactristics;
import controller.DataYearId;

public class PrintRawList implements ActionListener {

	JTable table;
	JComboBox<String> consumptionType;

	public PrintRawList(JTable table, JComboBox<String> consumptionType) {

		this.table = table;
		this.consumptionType = consumptionType;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		print();
		table.setForeground(Color.white);
		table.setBackground(new Color(52, 55, 59, 255));

	}

	private void print() {

		String companyName = CompanyCharactristics.getName();
		String dataYearId = DataYearId.getDataYearId() + "";

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int year = calendar.get(Calendar.YEAR);
		int mounth = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String date = day + "/" + mounth + "/" + year;

		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String time = hour + ":" + minute + ":" + second;

		float totalValue = 0;

		for (int counter = 0; counter < table.getRowCount(); counter++) {
			totalValue += Float.parseFloat(table.getValueAt(counter, 1)
					.toString());
		}

		String total = totalValue + "  افغانی ";

		String head = "  لیست خریداری مواد اولیه ( "
				+ consumptionType.getSelectedItem().toString() + ") شرکت "
				+ companyName + " در سال " + dataYearId;

		MessageFormat header = new MessageFormat(head);

		String foot = " تاریخ   " + date + "     ساعت     " + time;
		MessageFormat footer = new MessageFormat(
				"صفحه {0,number,integer}                                      "
						+ foot + "                     " + "    کل خرید : "
						+ total);

		table.setBackground(Color.white);
		table.setForeground(Color.black);
		try {
			table.print(PrintMode.FIT_WIDTH, header, footer);

		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
