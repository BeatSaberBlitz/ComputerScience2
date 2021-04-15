/**
 * 
 */
package cs145.parking;

/**
 * @author Diane Christie
 * @version 2020.02.14
 *
 */
public class ParkingPatrol {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int PARKING_SPOTS = 5;
		
		PoliceOfficer cop = new PoliceOfficer("Joe Friday", 714);
		ParkingTicket[] ticket = new ParkingTicket[PARKING_SPOTS];
		
		ParkedCar [] car = new ParkedCar [PARKING_SPOTS];
		car[0] = new ParkedCar(1988, "Chevy", "Camaro", "red", "987ZYX");
		car[1] = new ParkedCar (1968, "Ford", "Mustang", "black", "617JHN");
		car[2] = new ParkedCar(2010, "Honda", "Civic", "white", "123ABC");
		car[3] = new ParkedCar(2013, "Chevy", "Sonic", "orange", "879GAL");
		car[4] = new ParkedCar(2015, "Dodge", "Ram", "black", "555ZZZ");
		
		ParkingMeter [] meter = new ParkingMeter [PARKING_SPOTS];
		int counter = -10;
		for (int i = 0; i < meter.length; i++) 
		{
			meter[i] = new ParkingMeter(counter);
			counter+=10;
		}
		//officer goes on patrol 
		for (int i = 0; i < PARKING_SPOTS; i++)
		{
				ticket[i] = cop.patrol(meter[i], car[i]);
		}
		//print out tickets
		for  (int i = 0; i < PARKING_SPOTS; i++)
		{
			if (ticket[i] != null)
					System.out.println(ticket[i]);
		}
	
		System.out.println();// separate patrols
		
		//time goes by
		car[0] = new ParkedCar(2018, "Toyota", "Highlander", "magenta", "999OOO");
		meter[0].setTime(60);
		car[1] = new ParkedCar(2020, "Porsche", "940", "lightGray", "BIG$$$");
		
		//officer comes by again
		for (int i = 0; i < PARKING_SPOTS; i++)
		{
				ticket[i] = cop.patrol(meter[i], car[i]);
		}
		for  (int i = 0; i < PARKING_SPOTS; i++)
		{
			if (ticket[i] != null)
					System.out.println(ticket[i]);
		}

	}

}
