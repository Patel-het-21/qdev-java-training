package practicals.files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program creates a text file named "myTestFile.txt" and writes personal
 * details entered by the user into the file. Demonstrates File Handling, Input
 * Handling, and String Handling.
 * 
 * @author Het Patel
 * @since 17/10/25
 */
public class PersonalDetailsFileWriter {

	/**
	 * The main method where the program starts.
	 * 
	 * @param args Command-line arguments (not used in this program)
	 */
	public static void main(String[] args) {
		/**
		 * Create Scanner object to read user input from console
		 */
		Scanner scanner = new Scanner(System.in);

		try {
			/**
			 * Create a FileWriter to write to "myTestFile.txt"
			 */
			FileWriter fileWriter = new FileWriter("myTestFile.txt");
			/**
			 * Prompt the user for input
			 */
			System.out.println("Enter your personal Details : ");
			System.out.print("Name : ");
			String name = scanner.nextLine();
			System.out.print("Age: ");
			int age = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Email: ");
			String email = scanner.nextLine();
			System.out.print("Phone Number: ");
			String phone = scanner.nextLine();

			/**
			 * Write data to the file
			 */
			fileWriter.write("Personal Details:\n");
			fileWriter.write("Name: " + name + "\n");
			fileWriter.write("Age: " + age + "\n");
			fileWriter.write("Email: " + email + "\n");
			fileWriter.write("Phone Number: " + phone + "\n");
			/**
			 * Close the file writer
			 */
			fileWriter.close();

			System.out.println("Details saved successfully to myTestFile.txt");

		} catch (IOException ioException) {
			/**
			 * Handle exceptions related to file writing
			 */
			System.out.println("An error occurred while writing to the file.");
			ioException.printStackTrace();
		} finally {
			scanner.close();
		}
	}

}