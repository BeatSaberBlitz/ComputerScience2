/**
 * 
 */
package application;

/**
 * Creates high scores for the tetris game
 * @author Tyler Marefke
 * @version 1.0.0
 * @date 4/23/2020
 */
public class HighScore implements Comparable <HighScore>{
	private String initials;
	private int score;
	
	/**
	 * Creates a high score from intials and score
	 * @param initials
	 * @param score
	 */
	public HighScore(String initials, int score) {
		this.initials = initials;
		this.score = score;
	}

	/**
	 * Returns the intials of the HighScore
	 * @return the initials
	 */
	public String getInitials() {
		return initials;
	}

	/**
	 * Returns the score of the HighScore
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Allows for the high scores to be sorted in an array
	 */
	@Override
	public int compareTo(HighScore object) {
		
		if(this.score < object.score) {
			return 1;
			
		}else if(this.score > object.score) {
			return -1;
			
		}
		return 0;
	}
	
	
}
