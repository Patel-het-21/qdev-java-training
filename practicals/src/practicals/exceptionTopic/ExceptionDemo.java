package practicals.exceptionTopic;

/**
 * Demonstrates handling of checked and unchecked exceptions using try, catch,
 * finally, throw, and throws keywords.
 * 
 * @author Het Patel
 * @since 16/10/25
 * 
 */
public class ExceptionDemo {

	/**
	 * Checks the given age and throws a checked exception if the age is less than
	 * 18.
	 *
	 * @param age the age to check
	 * @throws Exception if age is less than 18
	 */
	public static void age(int age) throws Exception {
		if (age < 18) {
			throw new Exception("\nChecked Exception: Age must be at least 18 for voting ");
		}
		System.out.println("You are eligible for voting");
	}

	/**
	 * Performs division of two integers. May throw an unchecked ArithmeticException
	 * if division by zero occurs.
	 * 
	 * @param number1 dividend
	 * @param number2 divisor
	 */
	public static void divide(int number1, int number2) {
		int result = number1 / number2;
		System.out.println("Division result : " + result);
	}

	/**
	 * Main method to demonstrate exception handling. Calls methods that throw
	 * checked and unchecked exceptions and handles them with try-catch-finally
	 * blocks.
	 *
	 * @param args command-line arguments (not used)
	 */
	public static void main(String args[]) {
		/**
		 * Handle checked exception using try-catch-finally
		 */
		try {
			age(15);
		} catch (Exception e) {
			System.out.println("Caught checked exception = " + e.getMessage());
		} finally {
			System.out.println("Checked exception handling complete.\n");
		}
		/**
		 * Handle unchecked exception (ArithmeticException) using try-catch-finally
		 */
		try {
			divide(12, 0);
		} catch (ArithmeticException arithmeticException) {
			System.out.println("Caught uncheck exception : " + arithmeticException.getMessage());
		} finally {
			System.out.println("Checked exception handling complete.\n");
		}
	}

}