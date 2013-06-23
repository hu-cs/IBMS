package testCases;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import controller.InvoiceChanges;

public class InvoiceTestCases extends TestCase {

	InvoiceChanges invoiceTestingObject = new InvoiceChanges(null, null, null,
			null, null, null);

	@Test
	public void test() {
		stockSubtractionTest();
		addToSellHistoryTest();
		addToCustomerAccountTest();
		changeSoldMaterialTest();
		saveinvoiceTest();
		changeCashBoxTest();
		changeCustomerShoppingAccountTest();
	}

	public void stockSubtractionTest() {
		assertTrue(invoiceTestingObject.stockSubtraction());
	}

	public void addToSellHistoryTest() {
		assertTrue(invoiceTestingObject.addToSellHistory());
	}

	public void addToCustomerAccountTest() {
		assertTrue(invoiceTestingObject.addToCustomerAccount());
	}

	public void changeSoldMaterialTest() {
		assertTrue(invoiceTestingObject.changeTheSoldMaterialList());
	}

	public void saveinvoiceTest() {
		assertTrue(invoiceTestingObject.saveInvoice());
	}

	public void changeCashBoxTest() {
		assertTrue(invoiceTestingObject.changeTheCachBox());
	}

	public void changeCustomerShoppingAccountTest() {
		assertTrue(invoiceTestingObject.changeCustomerShoppingAccount());
	}

}
