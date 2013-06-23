package model;

import java.util.Calendar;
import java.util.HashMap;

public class Invoice {

	public static HashMap<Integer, InvoiceMaterial> materils = new HashMap<Integer, InvoiceMaterial>();
	Customer customer;
	int invoiceNumber;
	long dateAndTime;

	// public static Invoice newInvoice = new Invoice(0, 4444);// TODO set the
	// factor number

	public Invoice(int invoiceNumber, long dataAndTime) {
		setInvoiceNumber(invoiceNumber);
		setDateAndTime(dataAndTime);
//		materils = new HashMap<Integer, InvoiceMaterial>();
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setDateAndTime(long dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public long getDateAndTime() {
		return dateAndTime;
	}

	public double getTotalValue() {
		double ttl = 0;

		for (int radif : materils.keySet())
			ttl += materils.get(radif).getTotal();

		return ttl;
	}
}
