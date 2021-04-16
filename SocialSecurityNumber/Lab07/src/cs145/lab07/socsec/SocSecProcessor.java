/**
 * 
 */
package cs145.lab07.socsec;

import java.util.Scanner;

/**
 * @author Tyler Marefke
 * @version 2020.03.23
 */
public class SocSecProcessor {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String name;
		String socSec;
		String userAns;
		
		do {
		System.out.print("Name?\t");
		name = input.nextLine();
		System.out.print("SSN?\t");
		socSec = input.nextLine();
		try {
			
			if(isValid(socSec)) {
				System.out.println(name + " " + socSec + " is valid.");
			}
			
		}catch(SocSecException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		System.out.print("Continue?  ");
		userAns = input.nextLine();
		userAns = userAns.toUpperCase();
		
		}while(userAns.charAt(0) != 'N');
		
		input.close();
	}

	public static boolean isValid(String ssn) throws SocSecException{
		
		if(ssn.length() != 11) {
			throw new SocSecException("wrong number of characters!");
		}
		
		for (int index = 0; index < ssn.length(); index++) {
			if(index == 3 || index == 6) {
				if(ssn.charAt(index) != '-') {
					throw new SocSecException("dashes in the wrong positions!");
				}
			}else {
				if(ssn.charAt(index) == '-') {
					throw new SocSecException("dashes in the wrong position!");
				}
				
				if(!Character.isDigit(ssn.charAt(index))) {
					throw new SocSecException("contains a character that is not a digit!");
				}
			}
		}
		
		
		return true;
	}
}
