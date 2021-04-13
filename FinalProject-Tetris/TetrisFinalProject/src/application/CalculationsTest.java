/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Tyler Marefke
 *
 */
class CalculationsTest {

	Calculations calculationTest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		calculationTest = new Calculations();
	}

	/**
	 * Test method for {@link application.Calculations#Calculations()}.
	 */
	@Test
	void testCalculations() {
		assertEquals(calculationTest.getAmtLines(), 0);
		assertEquals(calculationTest.getCurrentLevel(), 1);
		assertEquals(calculationTest.getScore(), 0);
		assertEquals(calculationTest.getSpeedOfBlocks(), 2);
	}

	/**
	 * Test method for {@link application.Calculations#AddToScore(int)}.
	 */
	@Test
	void testAddToScore() {
		calculationTest.AddToScore(0);
		assertEquals(calculationTest.getAmtLines(), 0);
		assertEquals(calculationTest.getScore(), 20);
		calculationTest.AddToScore(1);
		assertEquals(calculationTest.getAmtLines(), 1);
		assertEquals(calculationTest.getScore(), 120);
		calculationTest.AddToScore(2);
		assertEquals(calculationTest.getAmtLines(), 3);
		assertEquals(calculationTest.getScore(), 420);
		calculationTest.AddToScore(3);
		assertEquals(calculationTest.getAmtLines(), 6);
		assertEquals(calculationTest.getScore(), 1120);
		calculationTest.AddToScore(4);
		assertEquals(calculationTest.getAmtLines(), 10);
		assertEquals(calculationTest.getScore(), 3120);
	}

	/**
	 * Test method for {@link application.Calculations#ChangeLV()}.
	 */
	@Test
	void testChangeLV() {
		calculationTest.ChangeLV();
		assertEquals(calculationTest.getSpeedOfBlocks(), 1);
		assertEquals(calculationTest.getCurrentLevel(), 2);
	}

}
