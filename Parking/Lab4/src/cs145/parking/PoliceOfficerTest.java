/**
 * 
 */
package cs145.parking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the PoliceOfficer Class
 * @author MarefkeTyler
 * @version 2020.02.20
 */
class PoliceOfficerTest {

	PoliceOfficer Karl;
	PoliceOfficer KarlJr;
	ParkingMeter timeUp;
	ParkingMeter timeFine;
	ParkedCar myCar;
	ParkingTicket myTicket;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		Karl = new PoliceOfficer("Karl", 201820321);
		KarlJr = new PoliceOfficer(Karl);
		myCar = new ParkedCar(1999, "Ford", "Mustang", "White", "987-ZXY");
		timeUp = new ParkingMeter(0);
		timeFine = new ParkingMeter(5);
		myTicket = Karl.patrol(timeUp, myCar);
	}

	/**
	 * Test method for {@link cs145.parking.PoliceOfficer#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("Officer Name:\tKarl\nBadge Number:\t201820321\n", Karl.toString());
		assertEquals("Officer Name:\tKarl\nBadge Number:\t201820321\n", KarlJr.toString());
	}

	/**
	 * Test method for
	 * {@link cs145.parking.PoliceOfficer#patrol(cs145.parking.ParkingMeter, cs145.parking.ParkedCar)}.
	 */
	@Test
	void testPatrol() {
		myTicket = Karl.patrol(timeUp, myCar);
		assertEquals(null, Karl.patrol(timeFine, myCar));
		assertEquals("Officer Name:\tKarl\nBadge Number:\t201820321\n\nLicense\tMake\tModel\tColor\tYear\n"
				+ "987-ZXY\tFord\tMustang\tWhite\t1999\n\nFine:\t$200.00\n", myTicket.toString());
	}

}
