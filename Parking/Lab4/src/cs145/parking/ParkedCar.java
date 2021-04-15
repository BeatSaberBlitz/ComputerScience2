/**
 * 
 */
package cs145.parking;

/**
 * This class tracks parked cars with all of their info that are on the street
 * @author MarefkeTyler
 * @version 2020.02.20
 */
public class ParkedCar {
	private int year;
	private String make;
	private String model;
	private String color;
	private String license;
	
	/**
	 * This constructor is made up of all the different elements to make a ParkedCar
	 * @param year the year the car was made
	 * @param make the make of the car
	 * @param model the model of the car
	 * @param color the color of the car
	 * @param license the car's license plate
	 */
	public ParkedCar(int year, String make, String model, String color, String license) {
		this.year = year;
		this.make = make;
		this.model = model;
		this.color = color;
		this.license = license;
	}
	
	/**
	 * This constructor copies another ParkedCar that is passed into it
	 * @param other the ParkedCar to be copied
	 */
	public ParkedCar(ParkedCar other) {
		this.year = other.year;
		this.make = other.make;
		this.model = other.model;
		this.color = other.color;
		this.license = other.license;
	}
	
	public String toString() {
		return "License\tMake\tModel\tColor\tYear\n" + this.license + 
				"\t" + this.make + "\t" + this.model + "\t" + this.color + "\t" + this.year + "\n";
	}
}
