/**
 * 
 */
package cs145.parking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the ParkingTicket Class
 * 
 * @author MarefkeTyler
 * @version 2020.02.20
 */
class ParkingTicketTest {

	PoliceOfficer Karl;
	ParkedCar myCar;
	ParkingTicket myTicket;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Karl = new PoliceOfficer("Karl", 201920321);
		myCar = new ParkedCar(1999, "Ford", "Mustang", "White", "987-ZXY");
		myTicket = new ParkingTicket(Karl, myCar);
	}

	/**
	 * Test method for {@link cs145.parking.ParkingTicket#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("Officer Name:\tKarl\nBadge Number:\t201920321\n\nLicense\tMake\tModel\tColor\tYear\n"
				+ "987-ZXY\tFord\tMustang\tWhite\t1999\n\nFine:\t$200.00\n", myTicket.toString());
	}

}
