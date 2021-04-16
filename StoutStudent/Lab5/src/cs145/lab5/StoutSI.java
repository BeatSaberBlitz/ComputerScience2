/**
 * 
 */
package cs145.lab5;

/**
 * @author MarefkeTyler
 *
 */
public class StoutSI extends StoutStudent {

	private String course;
	
	/**
	 * Creates a StoutSI with the parameters listed
	 * @param id the StoutSI's ID
	 * @param lastName the StoutSI's last name
	 * @param firstName the StoutSI's first name
	 */
	public StoutSI(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
		course = "None";
	}

	/**
	 * Creates a StoutSI with the parameters listed
	 * @param id the StoutSI's ID
	 * @param lastName the StoutSI's last name
	 * @param firstName the StoutSI's first name
	 * @param major the StoutSI's major 
	 * @param year the current year of the StoutSI
	 */
	public StoutSI(int id, String lastName, String firstName, String major, Level year) {
		super(id, lastName, firstName, major, year);
		course = "None";
	}

	
	
	/**
	 * Creates a StoutSI with the parameters listed
	 * @param id the StoutSI's ID
	 * @param lastName the StoutSI's last name
	 * @param firstName the StoutSI's first name
	 * @param course the StoutSI's course
	 */
	public StoutSI(int id, String lastName, String firstName, String course) {
		super(id, lastName, firstName);
		this.course = course;
	}
	
	/**
	 * Creates a StoutSI with the parameters listed
	 * @param id the StoutSI's ID
	 * @param lastName the StoutSI's last name
	 * @param firstName the StoutSI's first name
	 * @param major the StoutSI's major 
	 * @param year the current year of the StoutSI
	 * @param course the course that the StoutSI is in
	 */
	public StoutSI(int id, String lastName, String firstName, String major, Level year, String course) {
		super(id, lastName, firstName, major, year);
		this.course = course;
	}

	/**
	 * Returns the course of the StoutSI
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * Allows course to be modified
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * Returns the string form of the StoutSI
	 */
	public String toString() {
		return super.toString() + this.course + "\n";
	}
}
