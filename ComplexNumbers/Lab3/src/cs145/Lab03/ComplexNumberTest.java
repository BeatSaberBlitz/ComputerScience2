/**
 * 
 */
package cs145.Lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author MarefkeTyler
 * @version 2020.02.18
 */
class ComplexNumberTest {

	ComplexNumber zero;
	ComplexNumber singleDouble;
	ComplexNumber fullNum;
	ComplexNumber copyNum;
	ComplexNumber conjunctionNum;
	ComplexNumber multiply1;
	ComplexNumber multiply2;
	ComplexNumber divide1;
	ComplexNumber divide2;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		zero = new ComplexNumber();
		singleDouble = new ComplexNumber(5.2);
		fullNum = new ComplexNumber(3.2, 2.2);
		copyNum = new ComplexNumber(fullNum);
		conjunctionNum = new ComplexNumber(fullNum.conjunction());
		multiply1 = new ComplexNumber(0.5, 1.5);
		multiply2 = new ComplexNumber(2.0, -4.5);
		divide1 = new ComplexNumber(0.5, 1.5);
		divide2 = new ComplexNumber(2.0, -4.5);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#ComplexNumber()}. This tests
	 * the default constructor
	 */
	@Test
	void testComplexNumber() {
		assertEquals(0, zero.getRealNumber());
		assertEquals(0, zero.getImaginaryNumber());
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#ComplexNumber(double)}. This
	 * tests the single parameter constructor
	 */
	@Test
	void testComplexNumberDouble() {
		assertEquals(5.2, singleDouble.getRealNumber(), 0.00001);
		assertEquals(0, singleDouble.getImaginaryNumber());
	}

	/**
	 * Test method for
	 * {@link cs145.Lab03.ComplexNumber#ComplexNumber(double, double)}. This tests
	 * the two parameter constructor
	 */
	@Test
	void testComplexNumberDoubleDouble() {
		assertEquals(3.2, fullNum.getRealNumber(), 0.00001);
		assertEquals(2.2, fullNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for
	 * {@link cs145.Lab03.ComplexNumber#ComplexNumber(cs145.Lab03.ComplexNumber)}.
	 * This tests the copy constructor
	 */
	@Test
	void testComplexNumberComplexNumber() {
		assertEquals(3.2, copyNum.getRealNumber(), 0.00001);
		assertEquals(2.2, copyNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#setRealNumber(double)}. This
	 * tests the realNumber setter
	 */
	@Test
	void testSetRealNumber() {
		fullNum.setRealNumber(4.6);
		assertEquals(4.6, fullNum.getRealNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#setImaginaryNumber(double)}.
	 * This tests the imaginaryNumber setter
	 */
	@Test
	void testSetImaginaryNumber() {
		fullNum.setImaginaryNumber(7.8);
		assertEquals(7.8, fullNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#conjunction()}. This tests
	 * the conjunction of the ComplexNumber
	 */
	@Test
	void testconjunctionComplexNumber() {
		assertEquals(3.2, conjunctionNum.getRealNumber(), 0.00001);
		assertEquals(-2.2, conjunctionNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#toString()}. This tests the
	 * various toString results
	 */
	@Test
	void toStringString() {
		assertEquals("3.200 + 2.200i", fullNum.toString());
		assertEquals("Number is 0", zero.toString());
		assertEquals("5.200", singleDouble.toString());
		fullNum.setRealNumber(0);
		assertEquals("2.200i", fullNum.toString());
		zero.setImaginaryNumber(1);
		assertEquals("i", zero.toString());
		zero.setImaginaryNumber(-1);
		assertEquals("-i", zero.toString());
		zero.setRealNumber(2.2);
		assertEquals("2.200 - i", zero.toString());
		zero.setImaginaryNumber(1);
		assertEquals("2.200 + i", zero.toString());
		zero.setImaginaryNumber(-2.2);
		assertEquals("2.200-2.200i", zero.toString());
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#add(ComplexNumber)}. This
	 * tests the add method
	 */
	@Test
	void addComplexNumberComplexNumber() {
		fullNum = new ComplexNumber(fullNum.add(copyNum));
		assertEquals(6.4, fullNum.getRealNumber(), 0.00001);
		assertEquals(4.4, fullNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#subtract(ComplexNumber)}.
	 * This tests the subtract method
	 */
	@Test
	void subtractComplexNumberComplexNumber() {
		fullNum = new ComplexNumber(fullNum.subtract(conjunctionNum));
		assertEquals(0, fullNum.getRealNumber(), 0.00001);
		assertEquals(4.4, fullNum.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#multiply(ComplexNumber)}.
	 * This tests the multiply method
	 */
	@Test
	void multiplyComplexNumberComplexNumber() {
		multiply1 = new ComplexNumber(multiply1.multiply(multiply2));
		assertEquals(7.75, multiply1.getRealNumber(), 0.00001);
		assertEquals(0.75, multiply1.getImaginaryNumber(), 0.00001);
	}

	/**
	 * Test method for {@link cs145.Lab03.ComplexNumber#divide(ComplexNumber)}. This
	 * tests the divide method
	 */
	@Test
	void divideComplexNumberComplexNumber() {
		divide1 = new ComplexNumber(divide1.divide(divide2));
		assertEquals(-0.237, divide1.getRealNumber(), 0.001);
		assertEquals(0.216, divide1.getImaginaryNumber(), 0.001);
	}
}
