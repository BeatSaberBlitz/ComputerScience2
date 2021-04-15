/**
 * 
 */
package cs145.parking;

/**
 * This class has a police officer that patrols the area for parked cars who
 * have passed their parking meter
 * 
 * @author MarefkeTyler
 * @version 2020.02.20
 */
public class PoliceOfficer {
	private String name;
	private int badgeNumber;

	/**
	 * Creates a PoliceOfficer with their name and badgeNumber
	 * 
	 * @param name        the officer's name
	 * @param badgeNumber the officer's badge number
	 */
	public PoliceOfficer(String name, int badgeNumber) {
		this.name = name;
		this.badgeNumber = badgeNumber;
	}

	/**
	 * Creates a copy of the PoliceOfficer that is passed in
	 * 
	 * @param other the PoliceOfficer to copy
	 */
	public PoliceOfficer(PoliceOfficer other) {
		this.name = other.name;
		this.badgeNumber = other.badgeNumber;
	}

	/**
	 * Returns a string formated with the officer's name and badgeNumber
	 * 
	 * @return formated string of the officer
	 */
	public String toString() {
		return "Officer Name:\t" + this.name + "\nBadge Number:\t" + this.badgeNumber + "\n";
	}

	/**
	 * Returns a parking ticket with the officer's info and car's info and their
	 * fine
	 * 
	 * @param meter the time left for the car
	 * @param car   the car that is there
	 * @return the parking ticket
	 */
	public ParkingTicket patrol(ParkingMeter meter, ParkedCar car) {
		ParkingTicket newTicket = new ParkingTicket(this, car);
		if (meter.getTime() <= 0) {
			return newTicket;
		}
		return null;
	}
}
