/**
 * 
 */
package application;

/**
 * @author Tyler Marefke
 *
 */
public class Calculations {
	private double speedOfBlocks;
	private int scoreToNextLV;
	private final int LEVEL_START = 1;
	private int currentLevel;
	private int score;
	private int amtLines;
	
	/**
	 * Sets up the players current score, line count, speed of blocks, and score to next level variables
	 */
	public Calculations() {
		currentLevel = LEVEL_START;
		amtLines = 0;
		score = 0;
		
		speedOfBlocks = ((LEVEL_START * 2)) / Math.pow(LEVEL_START, 2);
		scoreToNextLV = ((LEVEL_START) * 2000) + (10 * amtLines);
	}
	
	public void AddToScore(int lines) {
		
		amtLines += lines;
		
		switch (lines) {
			case 0: {
				score += (10 * (currentLevel + 1));
				break;
			}
			case 1: {
				score += (50 * (currentLevel + 1));
				break;
			}
			case 2: {
				score += (150 * (currentLevel + 1));
				break;
			}
			case 3: {
				score += (350 * (currentLevel + 1));
				break;
			}
			case 4: {
				score += (1000 * (currentLevel + 1));
				break;
			}
		}
		
		if(score >= scoreToNextLV) {
			ChangeLV();
		}
	}
	
	public void ChangeLV() {
		currentLevel += 1;
		
		speedOfBlocks = ((currentLevel * 2)) / Math.pow(currentLevel, 2);
		scoreToNextLV = ((currentLevel) * 2000) + (10 * amtLines);
	}

	/**
	 * Returns the level that the player is currently on
	 * @return the currentLevel
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Returns the score that the player currently has
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns the amount of lines that the player has made
	 * @return the amtLines
	 */
	public int getAmtLines() {
		return amtLines;
	}
	
	/**
	 * Returns the current speed that the blocks should be moving in
	 * @return the speedOfBlocks
	 */
	public double getSpeedOfBlocks() {
		return speedOfBlocks;
	}
}
