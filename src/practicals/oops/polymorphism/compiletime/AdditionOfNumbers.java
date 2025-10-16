package practicals.oops.polymorphism.compiletime;

/**
 * Implements compile-time polymorphism using method overloading. Demonstrates
 * multiple versions of the addition method with different parameters.
 * 
 * @author Het Patel
 * @since 15/10/25
 */
public class AdditionOfNumbers {

	/**
	 * Adds two integer numbers.
	 * 
	 * @param number1 First integer
	 * @param number2 Second integer
	 */
	public void addition(int number1, int number2) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " = " + (number1 + number2));
	}

	/**
	 * Adds three integer numbers.
	 * 
	 * @param number1 First integer
	 * @param number2 Second integer
	 * @param number3 Third integer
	 */
	public void addition(int number1, int number2, int number3) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " + " + number3 + " = "
				+ (number1 + number2 + number3));
	}

	/**
	 * Adds four integer numbers.
	 * 
	 * @param number1 First integer
	 * @param number2 Second integer
	 * @param number3 Third integer
	 * @param number4 Fourth integer
	 */
	public void addition(int number1, int number2, int number3, int number4) {
		System.out.println("Addition of int : " + number1 + " + " + number2 + " + " + number3 + " + " + number4 + " = "
				+ (number1 + number2 + number3 + number4));
	}

	/**
	 * Adds two double numbers.
	 * 
	 * @param number1 First double
	 * @param number2 Second double
	 */
	public void addition(double number1, double number2) {
		System.out.println("Addition of double : " + number1 + " + " + number2 + " = " + (number1 + number2));
	}

	/**
	 * Main method to test overloaded addition methods.
	 * 
	 * @param args Command-line arguments (not used)
	 * @since 15/10/25
	 */
	public static void main(String[] args) {
		AdditionOfNumbers additionOfNumbers = new AdditionOfNumbers();
		/**
		 *  Call method with 2 int parameters
		 */
		additionOfNumbers.addition(12, 30);
		/**
		 *  Call method with 2 double parameters
		 */
		additionOfNumbers.addition(12d, 30f);
		/**
		 *  Call method with 3 int parameters
		 */
		additionOfNumbers.addition(12, 30, 8);
		/**
		 *  Call method with 4 int parameters
		 */
		additionOfNumbers.addition(12, 30, 8, 10);
	}

}