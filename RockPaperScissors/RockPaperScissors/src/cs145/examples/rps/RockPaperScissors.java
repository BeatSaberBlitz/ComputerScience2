package cs145.examples.rps;

import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors 
{
	//there are errors in this program
	public static void main(String[] args) 
	{
	      int computer;
	      int user;
	      //assigning the computer a selection
	      Random generator = new Random();
	      
	      Scanner keyboardIn = new Scanner(System.in);
	      
	      do {//Testing
	    	  System.out.println("Welcome to Rock-Paper-Scissors");
	    	  System.out.println("Please enter the number of your selection");
	    	  System.out.println("1.  Rock");
	    	  System.out.println("2.  Paper");
	    	  System.out.println("3.  Scissors");
	    	  System.out.println("4.  To Exit\n");
	      
	    	  //getting the user selection
	    	  user = keyboardIn.nextInt();
	      
	    	  //assign computer selection
	    	  computer = generator.nextInt(3) + 1;
	     	      
	    	  //tell the player what was chosen
	    	  if(user == 1)//player is rock
	    	  {
	    		  System.out.println ("Player is rock");
	    	  }
	    	  else if (user == 2)//player is paper
	    	  {
	    		  System.out.println ("Player is paper");
	    	  }
	    	  else //player is scissors
	    	  {
	    		  System.out.println ("Player is scissors");
	    	  }
	    	  //tell the player what the computer has chosen
	    	  if(computer == 1)//computer is rock
	    	  {
	    		  System.out.println ("Computer is rock");
	    	  }
	    	  else if (computer == 2)//computer is paper
	    	  {
	    		  System.out.println ("Computer is paper");
	    	  }
	    	  else //computer is scissors
	    	  {
	    		  System.out.println ("Computer is scissors");
	    	  }
	      
	    	  //determine winner
	    	  if (user == computer) //tie
	    	  {
	    		  System.out.println("It is a tie");
	    	  }
	    	  else if (user == 1 && computer == 3) //player is rock
	    	  {
	    		  System.out.println("Player wins");
	    	  }
	    	  else if (user == 3 && computer == 1) //computer is rock
	    	  {
	    		  System.out.println("Computer wins");
	    	  }
	    	  else if (user < computer) //player is less than computer
	    	  {
	    		  System.out.println("Computer wins");
	    	  }
	    	  else  //computer is less than player
	    	  {
	    		  System.out.println("Player wins");
	    	  }
	      }while(user != 4);
	      keyboardIn.close();
	}
}
