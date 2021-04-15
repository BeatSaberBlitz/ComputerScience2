/**
 * 
 */
package cs145.parking;

/**
 * This class makes a parking meter that keeps track of the time left and time
 * added
 * 
 * @author MarefkeTyler
 * @version 2020.02.20
 */
public class ParkingMeter {
	private int time;

	/**
	 * Sets the initial time on the ParkingMeter
	 * 
	 * @param time the initial starting time
	 */
	public ParkingMeter(int time) {
		this.time = time;
	}

	/**
	 * Returns the time left of the ParkingMeter
	 * 
	 * @return the time left
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets a new time remaining on the parking meter
	 * 
	 * @param time the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

}
