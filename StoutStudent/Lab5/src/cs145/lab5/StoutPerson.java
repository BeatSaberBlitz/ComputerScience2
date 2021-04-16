/**
 * 
 */
package cs145.lab5;

/**
 * The abstract class for a person at Stout
 * @author MarefkeTyler
 * @version 2020.02.27
 */
public abstract class StoutPerson implements Comparable <StoutPerson>{

	private int id;
	private String lastName;
	private String firstName;
	
	/**
	 * Creates a StoutPerson with the parameters listed
	 * @param id the StoutPerson's ID number
	 * @param lastName the StoutPerson's last name
	 * @param firstName the StoutPerson's first name
	 */
	public StoutPerson(int id, String lastName, String firstName) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	/**
	 * Returns the Stout Person's last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * Allows the last name to be modified
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns the StoutPerson's first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Allows the first name to be modified
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Returns the Person's ID
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	public abstract String role();
	
	/**
	 * Returns the String form of the StoutPerson
	 */
	public String toString() {
		return this.lastName + ", " + this.firstName + "\nID: " + this.id + "\n";
	}
	
	public int compareTo(StoutPerson object) {
		return this.lastName.compareTo(object.lastName);
	}
}
