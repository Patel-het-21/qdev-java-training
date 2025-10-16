package practicals;

/**
 * Description : Program to demonstrate the use of while and do-while loops by
 * printing numbers from 1 to 10.
 * 
 * @author Het Patel
 * @since 11/10/25
 */
public class LoopDemoWhileDoWhile {

	/**
	 * Main method to run the loop demonstrations.
	 *
	 * @param args Command line arguments (not used in this program)
	 */
	public static void main(String[] args) {

		// Demonstrate while loop
		System.out.println("Using while loop:");
		printNumbersUsingWhileLoop();
		System.out.println();
		// Demonstrate do-while loop
		System.out.println("Using do-while loop:");
		printNumbersUsingDoWhileLoop();
	}

	/**
	 *  Prints numbers from 1 to 10 using a while loop.
	 */
	private static void printNumbersUsingWhileLoop() {
		int number = 1;
		while (number <= 10) {
			System.out.print(number + " ");
			number++;
		}
	}

	/**
	 *  Prints numbers from 1 to 10 using a do-while loop.
	 */
	private static void printNumbersUsingDoWhileLoop() {
		int number = 1;
		do {
			System.out.print(number + " ");
			number++;
		} while (number <= 10);
	}

}