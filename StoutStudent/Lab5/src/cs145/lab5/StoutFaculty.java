/**
 * 
 */
package cs145.lab5;

/**
 * StoutFacutly creates a faculty of stout with the parent StoutPerson
 * @author MarefkeTyler
 * @version 2020.02.27
 */
public class StoutFaculty extends StoutPerson {

	private String position;
	
	/**
	 * Creates a StoutFaculty with the parameter listed
	 * @param id the StoutFaculty's ID
	 * @param lastName the StoutFaculty's last name
	 * @param firstName the StoutFaculty's first name
	 */
	public StoutFaculty(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
		position = "Instructor";
	}
	
	

	/**
	 * Creates a StoutFaculty with the parameters listed
	 * @param id the StoutFaculty's ID
	 * @param lastName the StoutFaculty's last name
	 * @param firstName the StoutFaculty's first name
	 * @param position the StoutFaculty's position
	 */
	public StoutFaculty(int id, String lastName, String firstName, String position) {
		super(id, lastName, firstName);
		this.position = position;
	}



	/**
	 * Returns the role for StoutFaculty
	 * @return "teaches students" the role of the StoutFaculty
	 */
	@Override
	public String role() {
		return "teaches students";
	}

	/**
	 * Returns the current position of the StoutFaculty
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Allows the position to be modified
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Returns the String form of StoutFaculty
	 */
	public String toString() {
		return super.toString() + this.position + " " + this.role() + "\n";
	}

}
