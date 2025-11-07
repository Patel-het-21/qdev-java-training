package practicals;

/**
 * Description : Program to print a right-angled triangle star pattern.
 * 
 * @author Het Patel
 * @since 11/10/25
 */
public class PatternPrintForLoop {

	/**
	 * Main method to print the star pattern.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Loop for each row
		 */
		for (int row = 1; row <= 5; row++) {
			/**
			 * Print '*' equal to the current row number
			 */
			for (int column = 1; column <= row; column++) {
				System.out.print("* ");
			}
			/**
			 * Move to the next line
			 */
			System.out.println();
		}
	}

}