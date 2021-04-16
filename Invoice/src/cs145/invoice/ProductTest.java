/**
 * 
 */
package cs145.invoice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author MarefkeTyler
 *
 */
class ProductTest {

	Product myProduct;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myProduct = new Product("My amazing product!", 1000000.99);
	}

	/**
	 * Test method for {@link cs145.invoice.Product#Product(java.lang.String, double)}.
	 */
	@Test
	void testProduct() {
		assertEquals("My amazing product!", myProduct.getDescription());
		assertEquals(1000000.99, myProduct.getPrice(), 0.001);
	}

}
