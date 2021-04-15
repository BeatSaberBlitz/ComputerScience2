/**
 * 
 */
package cs145.parking;

/**
 * This class creates a parking ticket for those who have passed the time on the
 * parking meter
 * 
 * @author MarefkeTyler
 * @version 2020.02.20
 */
public class ParkingTicket {
	private PoliceOfficer officer;
	private ParkedCar car;
	private final int FINE = 200;

	/**
	 * Creates a Ticket using the officer and car data
	 * 
	 * @param officer the PoliceOfficer's data
	 * @param car     the ParkedCar's data
	 */
	public ParkingTicket(PoliceOfficer officer, ParkedCar car) {
		this.officer = officer;
		this.car = car;
	}

	/**
	 * Returns the final ticket with the officer's name and badge number and the
	 * car's year, make, model, color, and license plate and the fine owed
	 * 
	 * @return Ticket information about officer, car, and fine
	 */
	public String toString() {
		return officer.toString() + "\n" + car.toString() + "\nFine:\t$" + FINE + ".00\n";
	}
}
