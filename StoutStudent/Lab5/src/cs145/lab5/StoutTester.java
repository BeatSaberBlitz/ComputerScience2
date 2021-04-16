/**
 * 
 */
package cs145.lab5;

import java.util.Arrays;

import cs145.lab5.StoutStudent.Level;

/**
 * The main method for testing the StoutPerson and all respectively linked classes
 * @author MarefkeTyler
 * @version 2020.02.27
 */
public class StoutTester {

	/**
	 * The main method for StoutPerson class
	 */
	public static void main(String[] args) {
		StoutPerson[] stouties = new StoutPerson[6];
		
		stouties[0] = new StoutFaculty(1256, "Turner", "Scott", "Associate Professor");
		stouties[1] = new StoutFaculty(615, "Christie", "Diane", "Professor");
		stouties[2] = new StoutFaculty(1073, "Mason", "Terry", "Professor");
		stouties[3] = new StoutStudent(703673, "Marefke", "Tyler", "Computer Science" , Level.freshman);
		stouties[4] = new StoutStudent(703130, "Meyer", "Tiernan");
		stouties[5] = new StoutSI(7007732, "Nelson", "Andrew", "Computer Science", Level.junior, "CS-145");
		
		Arrays.sort(stouties);
		
		for(int index = 0; index < stouties.length; index++) {
			System.out.println(stouties[index].toString());
		}
	}

}
