package practicals;

import java.util.Scanner;

/**
 * Description: If/Else condition (age check)
 * 
 * @author Het Patel
 * @since 11/10/25
 */
public class AgeCheckIfElse {

	/**
	 * This is the main method, the entry point for the Java application. whether a
	 * person is eligible for voting based on their age.
	 *
	 * @param args Command line arguments (not used in this program).
	 */
	public static void main(String[] args) {
		/**
		 * create a Scanner object to read input form the user
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your Age : ");
		int age = scanner.nextInt();

		/**
		 * Check if age is greater than or equal to 18
		 */
		if (age >= 18) {
			System.out.println("You are eligible for voting");
		} else {
			System.out.println("You are not eligible for voting");
		}
		scanner.close();
	}

}