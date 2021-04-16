/**
 * 
 */
package cs145.lab5;

/**
 * StoutStudent creates a students of Stout with the parent StoutPerson class
 * @author MarefkeTyler
 * @version 2020.02.27
 */
public class StoutStudent extends StoutPerson {

	enum Level {freshman, sophomore, junior, senior};
	
	private String major;
	private Level year;
	
	/**
	 * Creates a StoutPerson with the parameter listed
	 * @param id the StoutPerson's ID
	 * @param lastName the StoutPerson's last name
	 * @param firstName the StoutPerson's first name
	 */
	public StoutStudent(int id, String lastName, String firstName) {
		super(id, lastName, firstName);
		major = "Undeclared";
		year = Level.freshman;
	}

	
	
	/**
	 * Creates a StoutStudent with the parameters listed
	 * @param id the StoutStudent's ID
	 * @param lastName the StoutStudent's last name
	 * @param firstName the StoutStudent's first name
	 * @param major the StoutStudent's major
	 * @param year the class year the StoutStudent is
	 */
	public StoutStudent(int id, String lastName, String firstName, String major, Level year) {
		super(id, lastName, firstName);
		this.major = major;
		this.year = year;
	}


	/**
	 * Returns the role of the StoutStudent
	 * @return "takes classes" the role of the StoutStudent
	 */
	@Override
	public String role() {
		return "takes classes";
	}

	/**
	 * Returns the major of the StoutStudent
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Allows the major to be modified
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * Returns the current year of the StoutStudent
	 * @return the year
	 */
	public Level getYear() {
		return year;
	}

	/**
	 * Allows the year to be modified
	 * @param year the year to set
	 */
	public void setYear(Level year) {
		this.year = year;
	}

	/**
	 * Returns the String form of the StoutStudent
	 */
	public String toString() {
		return super.toString() + this.major + " " + this.year + " " + this.role() + "\n";
	}
}
