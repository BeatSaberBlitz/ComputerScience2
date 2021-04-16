package cs145.invoice;

/**
 * @author Tiernan Meyer
 * @version 03/04/20
 *
 */
public class Address {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	/**
	 * Constructor for the address
	 * @param n the name on the address
	 * @param str the street of the address
	 * @param c the city of the address
	 * @param s the state of the address
	 * @param zip the zip code of the address
	 */
	public Address(String n, String str, String c, String s, String zip) {
		this.name = n;
		this.street = str;
		this.city = c;
		this.state = s;
		this.zip = zip;
	}
	
	/**
	 * Converts the address into a formatted string to be outputted
	 */
	public String toString() {
		return name + "\n" + street + "\n" + city + ", " + state + " " + zip;
	}
}
