package cs145.Lab03;

/**
 * Creates a manipulates a complex number
 * 
 * @author MarefkeTyler
 * @version 2020.13.02
 */
public class ComplexNumber {
	private double realNumber;
	private double imaginaryNumber;

	/**
	 * Constructor initializes realNumber and imaginaryNumber to 0
	 */
	public ComplexNumber() {
		realNumber = 0;
		imaginaryNumber = 0;
	}

	/**
	 * Constructor initializes realNumber to input number and imaginaryNumber to 0
	 * 
	 * @param realNumber the input number that realNumber is initialized to
	 */
	public ComplexNumber(double realNumber) {
		this.realNumber = realNumber;
		imaginaryNumber = 0;
	}

	/**
	 * Constructor initializes realNumber input number and imaginaryNumber to input
	 * number
	 * 
	 * @param realNumber      the input number that realNumber is initialized to
	 * @param imaginaryNumber the input number that imaginaryNumber is initialized
	 *                        to
	 */
	public ComplexNumber(double realNumber, double imaginaryNumber) {
		this.realNumber = realNumber;
		this.imaginaryNumber = imaginaryNumber;
	}

	/**
	 * Constructor initializes to ComplexNumber other's realNumber and
	 * imaginaryNumber
	 * 
	 * @param other the copy constructor that is passed in
	 */
	public ComplexNumber(ComplexNumber other) {
		this.realNumber = other.realNumber;
		this.imaginaryNumber = other.imaginaryNumber;
	}

	/**
	 * @return the realNumber
	 */
	public double getRealNumber() {
		return realNumber;
	}

	/**
	 * @param realNumber the realNumber to set
	 */
	public void setRealNumber(double realNumber) {
		this.realNumber = realNumber;
	}

	/**
	 * @return the imaginaryNumber
	 */
	public double getImaginaryNumber() {
		return imaginaryNumber;
	}

	/**
	 * @param imaginaryNumber the imaginaryNumber to set
	 */
	public void setImaginaryNumber(double imaginaryNumber) {
		this.imaginaryNumber = imaginaryNumber;
	}

	/**
	 * Returns a copy of ComplexNumber with the imaginary number being the opposite
	 * of what it is
	 * 
	 * @return negativeNum a value with a opposite value for imaginaryNumber than
	 *         original
	 */
	public ComplexNumber conjunction() {
		ComplexNumber negativeNum = new ComplexNumber(this.realNumber, (-1) * this.imaginaryNumber);
		return negativeNum;
	}

	/**
	 * Turns the ComplexNumber into a string for readability
	 * 
	 * @return the string form of the ComplexNumber
	 */
	public String toString() {
		if (this.realNumber != 0 && this.imaginaryNumber != 0) {
			if(this.imaginaryNumber == 1) {
				return String.format("%.3f", this.realNumber) + " + i";
				
			}else if(this.imaginaryNumber == -1) {
				return String.format("%.3f", this.realNumber) + " - i";
				
			}else if(this.imaginaryNumber < 0) {
				return String.format("%.3f", this.realNumber) + String.format("%.3f", this.imaginaryNumber) + "i"; 
			}
			return String.format("%.3f", this.realNumber) + " + " + String.format("%.3f", this.imaginaryNumber) + "i";
		
		} else if (this.realNumber != 0) {
			return String.format("%.3f", this.realNumber);
			
		} else if (this.imaginaryNumber != 0) {
			if(this.imaginaryNumber == 1) {
				return "i";
				
			}else if(this.imaginaryNumber == -1) {
				return "-i";
				
			}
			return String.format("%.3f", this.imaginaryNumber) + "i";
		}
		return "Number is 0";
	}

	/**
	 * Adds two complex numbers together and returns a new complex number
	 * 
	 * @param other the number to be added to
	 * @return sum the final result of adding the two complex numbers
	 */
	public ComplexNumber add(ComplexNumber other) {
		ComplexNumber sum = new ComplexNumber(this.realNumber + other.realNumber,
				this.imaginaryNumber + other.imaginaryNumber);
		return sum;
	}

	/**
	 * Subtracts two complex numbers from each other and returns a new complex
	 * number
	 * 
	 * @param other the number to be subtracted from
	 * @return sum the final result of subtracting the two complex numbers
	 */
	public ComplexNumber subtract(ComplexNumber other) {
		ComplexNumber sum = new ComplexNumber(this.realNumber - other.realNumber,
				this.imaginaryNumber - other.imaginaryNumber);
		return sum;
	}

	/**
	 * Multiplies two complex number together and returns a new complex number
	 * 
	 * @param other the number to be multiplied to
	 * @return sum the final result of multiplying the two complex numbers
	 */
	public ComplexNumber multiply(ComplexNumber other) {
		ComplexNumber sum = new ComplexNumber();
		sum.setRealNumber((this.realNumber * other.realNumber) - (this.imaginaryNumber * other.imaginaryNumber));
		sum.setImaginaryNumber((this.realNumber * other.imaginaryNumber) + (other.realNumber * this.imaginaryNumber));
		return sum;
	}

	/**
	 * Divides two complex numbers from each other and returns a new complex number
	 * 
	 * @param other the number to be divided by
	 * @return sum the final result of dividing two complex numbers
	 */
	public ComplexNumber divide(ComplexNumber other) {
		ComplexNumber sum = new ComplexNumber();
		double topNum = ((this.realNumber * other.realNumber) + (this.imaginaryNumber * other.imaginaryNumber));
		double bottomNum = (Math.pow(other.realNumber, 2) + Math.pow(other.imaginaryNumber, 2));
		sum.setRealNumber(topNum / bottomNum);

		topNum = (this.imaginaryNumber * other.realNumber) - (this.realNumber * other.imaginaryNumber);
		sum.setImaginaryNumber(topNum / bottomNum);
		return sum;
	}
}
