/**
 * 
 */
package cs145.parking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ParkingMeter class
 * @author MarefkeTyler
 * @version 2020.02.20
 */
class ParkingMeterTest {

	ParkingMeter myMeter;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		myMeter = new ParkingMeter(2);
	}

	/**
	 * Test method for {@link cs145.parking.ParkingMeter#getTime()}.
	 */
	@Test
	void test() {
		assertEquals(2, myMeter.getTime());
		myMeter.setTime(5);
		assertEquals(5, myMeter.getTime());
	}

}
