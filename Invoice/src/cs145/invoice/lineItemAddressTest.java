package cs145.invoice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class lineItemAddressTest {
	Address test;
	Product product;
	LineItem item;
	
	@BeforeEach
	void setUp() throws Exception {
		test = new Address("Tiernan Meyer", "401 N Peach Ave", "Marshfield", "WI", "54449");
		product = new Product("Toy", 20);
		item = new LineItem(product, 3);
	}

	@Test
	void testToStringAddress() {
		assertEquals("Tiernan Meyer\n401 N Peach Ave\nMarshfield, WI 54449", test.toString());
	}
	
	@Test
	void testToStringLineItem() {
		assertEquals("Toy\t$20.0\t3\t\t$60.0\n", item.toString());
		//20
	}
	@Test
	void testGetTotalPrice() {
		assertEquals(60, item.getTotalPrice());
	}

}
