/**
 * 
 */
package cs145.lab07.socsec;

/**
 * Throws an exception for invalid social security number
 * @author Tyler Marefke
 * @version 2020.03.23
 */
public class SocSecException extends Exception {

	public SocSecException(String error) {
		super("Error: Invalid social security number, " + error);
	}
}
