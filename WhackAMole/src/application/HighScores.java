/**
 * 
 */
package application;

/**
 * Generates high scores for the whack a mole game and maintains those scores
 * @author Tyler Marefke
 * @version 1.0.0
 * @date 4/14/2020
 */
public class HighScores implements Comparable <HighScores>{
	private String intials;
	private int score;
	
	public HighScores(String intials, int score) {
		this.intials = intials;
		this.score = score;
	}

	public String getIntials() {
		return intials;
	}

	public int getScore() {
		return score;
	}

	public int compareTo(HighScores object) {
		if(this.score < object.score) {
			return 1;
			
		}else if(this.score > object.score) {
			return -1;
			
		}
		return 0;
	}
}
