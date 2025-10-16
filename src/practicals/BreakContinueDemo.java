package practicals;

/**
 * Description : Program to demonstrate the use of break and continue statements
 * using a loop from 1 to 10.
 * 
 * @author Het Patel
 * @since 11/10/25
 */

public class BreakContinueDemo {

	/**
	 * Main method to demonstrate break and continue statements in a loop.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		System.out.println("Printing numbers from 1 to 10:");
		System.out.println("Skipping 5 and stopping after 7.\n");
		for (int number = 1; number <= 10; number++) {
			/*
			 * Skip printing number 5
			 */
			if (number == 5) {
				continue;
			}
			System.out.println("Number: " + number);
			/**
			 * Stop after printing 7 numbers
			 */
			if (number == 7) {
				break;
			}
		}
	}

}