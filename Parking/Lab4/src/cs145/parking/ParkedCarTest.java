/**
 * 
 */
package cs145.parking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ParkedCar class
 * 
 * @author MarefkeTyler
 * @version 2020.02.20
 */
class ParkedCarTest {

	ParkedCar myCar;
	ParkedCar carTheif;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myCar = new ParkedCar(1999, "Ford", "Mustang", "White", "987-ZXY");
		carTheif = new ParkedCar(myCar);
	}

	/**
	 * Test method for {@link cs145.parking.ParkedCar#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("License\tMake\tModel\tColor\tYear\n987-ZXY\tFord\tMustang\tWhite\t1999\n", myCar.toString());
		assertEquals("License\tMake\tModel\tColor\tYear\n987-ZXY\tFord\tMustang\tWhite\t1999\n", carTheif.toString());
	}

}
