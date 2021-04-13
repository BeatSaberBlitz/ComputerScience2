/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests the HighScore class
 * @author Tyler Marefke
 * @date 5/6/2020
 */
class HighScoreTest {
	
	static HighScore highScoreTest;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		highScoreTest = new HighScore("LEM", 400);
	}

	/**
	 * Test method for {@link application.HighScore#HighScore(java.lang.String, int)}.
	 */
	@Test
	void testHighScore() {
		assertEquals(highScoreTest.getInitials(), "LEM");
		assertEquals(highScoreTest.getScore(), 400);
	}

}
