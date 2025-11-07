package practicals;

import java.util.Scanner;

/**
 * Description: Program to display the month name using switch-case based on
 * user input.
 * 
 * @author Het Patel
 * @since 11/10/25
 */

public class MonthNameSwitch {

	/**
	 * This is the main method, the entry point for the Java application. Main
	 * method to find the month name based on user input.
	 *
	 * @param args Command line arguments (not used in this program).
	 */
	public static void main(String[] args) {

		/**
		 * Create a scanner object for reading input
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number (1 to 12) to get the month name : ");
		int monthNumber = scanner.nextInt();
		/**
		 * Call method to find and print the month name
		 */
		findMonthName(monthNumber);
		scanner.close();
	}

	/**
	 * Finds and prints the month name based on the provided number.
	 *
	 * @param monthNumber An integer between 1 and 12 representing the month
	 */
	private static void findMonthName(int monthNumber) {
		String monthName;
		switch (monthNumber) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
			break;
		default:
			monthName = "Invalid input! Please enter a number from 1 to 12.";
		}
		/**
		 * Display the result
		 */
		System.out.println(monthName);
	}

}