package model;

import java.util.ArrayList;

public class CustomerShoppingAccount {

	// indicates the invoice number
	int invoiceNumber;
	// indicates the invoice total cost
	double invoiceCost;
	// indicate invoice date and time
	String dateAndTime;

	public static ArrayList<CustomerShoppingAccount> shoppingList = new ArrayList<CustomerShoppingAccount>();

	public CustomerShoppingAccount(int invoiceNumber, double invoiceCost,
			String dateAndTime) {
		setInvoiceNumber(invoiceNumber);
		setInvoiceCost(invoiceCost);
		setDateAndTime(dateAndTime);
	}

	public int getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public double getInvoiceCost() {
		return invoiceCost;
	}

	public void setInvoiceCost(double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	/**
	 * Returns the total shopping account of the customer
	 * 
	 * @return total shopping account
	 */
	public double getTotalShoppingAccount() {

		double tempValue = 0;
		for (CustomerShoppingAccount tempAccount : shoppingList) {
			tempValue += tempAccount.getInvoiceCost();
		}

		return tempValue;
	}

}
