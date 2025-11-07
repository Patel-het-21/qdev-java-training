package practicals;

import java.util.Scanner;

/**
 * Search element in array and return its index
 * 
 * @author Het Patel
 * @since 16/10/25
 */
public class FindArrayIndex {

	/**
	 * Finds the index of the given element in the provided array. If the element is
	 * found, it returns the index of its first occurrence. Otherwise, it returns
	 * -1.
	 * 
	 * @param numbers        Array to be search.
	 * @param search_element element to find.
	 * @return Index of element if found, otherwise -1.
	 */
	public static int findIndex(int[] numbers, int search_element) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == search_element) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Main method - entry point of the program.
	 * 
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		/**
		 * create scanner class object and take input from user
		 */
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter size of Array : ");
		int size = scanner.nextInt();
		/**
		 * Declare and initialize the array
		 */
		int numbers[] = new int[size];
		/**
		 * Take input from user in the array
		 */
		for (int i = 0; i < size; i++) {
			System.out.print("Enter the Number " + (i + 1) + " : ");
			numbers[i] = scanner.nextInt();
		}
		/**
		 * take input from user which is search in the array
		 */
		System.out.print("Enter number you want to search : ");
		int search_element = scanner.nextInt();

		/**
		 * Search for the element's index
		 */
		int index = findIndex(numbers, search_element);
		if (index != -1) {
			System.out.println("Element " + search_element + " found at index: " + index);
		} else {
			System.out.println("Element " + search_element + " not found in the array.");
		}
		scanner.close();
	}

}