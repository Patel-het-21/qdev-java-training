package practicals.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * FileDataReader is a program to read and process text files. It demonstrates
 * file handling, input handling, loops, string manipulation, and exception
 * handling in Java.
 *
 * Features: Reads a file line by line. Converts each line to uppercase and
 * trims extra spaces. Counts total lines and words in the file. Handles file
 * not found and I/O exceptions gracefully.
 * 
 * @author Het Patel
 * @since 29/10/25
 */
public class FileDataReader {

	/**
	 * The main method executes the program. It reads the file specified by the
	 * user, processes its content, and displays the content along with line and
	 * word counts.
	 *
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {

		try {
			/**
			 * Create FileReader and BufferedReader to read the file
			 */
			FileReader fileReader = new FileReader("myTestFile.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			int lineCount = 0;
			int wordCount = 0;

			/**
			 * Read file line by lines
			 */
			while ((line = bufferedReader.readLine()) != null) {
				lineCount++;

				/**
				 * String handling: trim and convert to uppercase
				 */
				String processLine = line.trim().toUpperCase();
				System.out.println(processLine);

				/**
				 * Count words in the line
				 */
				String[] words = line.trim().split("\\s+");
				wordCount += words.length;
			}

			/**
			 * Close the readers
			 */
			bufferedReader.close();
			fileReader.close();
			/**
			 * Display statistics
			 */
			System.out.println("\n--- File Statistics ---");
			System.out.println("Total Lines : " + lineCount);
			System.out.println("Total Words : " + wordCount);

		} catch (FileNotFoundException e) {
			System.out.println("Error: File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: Problem reading the file!");
			e.printStackTrace();
		}
	}

}