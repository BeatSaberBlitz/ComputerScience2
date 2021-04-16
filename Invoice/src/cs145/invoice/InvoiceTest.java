/**
 * 
 */
package cs145.invoice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the invoice class
 * @author MarefkeTyler
 * @version 2020.03.04
 */
class InvoiceTest {
	Invoice myInvoice;
	Address myAddress;
	Product myProduct;
	Product mySax;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myAddress = new Address("Tyler Marefke", "209 Hickory Ln.", "Marshfield", "Wisconsin", "54449");
		myInvoice = new Invoice(myAddress);
		myProduct = new Product("Flute", 1000.99);
		mySax = new Product("Sax", 2000.99);
	}

	/**
	 * Test method for {@link cs145.invoice.Invoice#Invoice(cs145.invoice.Address)}.
	 */
	@Test
	void testInvoice() {
		System.out.println(myInvoice);
	}

	/**
	 * Test method for {@link cs145.invoice.Invoice#addCharge(cs145.invoice.Product, int)}.
	 */
	@Test
	void testAddCharge() {
		myInvoice.addCharge(myProduct, 20);
		assertEquals(20019.80, myInvoice.getTotalDue(), 0.0001);
		myInvoice.addCharge(mySax, 5);
		assertEquals(30024.75, myInvoice.getTotalDue(), 0.0001);
	}

	/**
	 * Test method for {@link cs145.invoice.Invoice#toString()}.
	 */
	@Test
	void testToString() {
		System.out.println(myInvoice);
	}

}
