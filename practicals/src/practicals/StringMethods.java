package practicals;

/**
 * Description : Demonstrate common String methods such as charAt(), concat(),
 * equals(), etc.
 * 
 * @author Het Patel
 * @since 13/10/25
 */
public class StringMethods {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		final String name1 = "Hello";
		final String name2 = "hello";
		final String name3 = " World!!!  ";
		final String name4 = "Welcome to company. company is good.";
		/**
		 * Get character at 0th index position and concate name1 and name3
		 */
		System.out.println("cahrAt(1) : " + name1.charAt(0));
		System.out.println("concat : " + name1.concat(name3));
		/**
		 * Comparing strings using equals() and equalsIgnoreCase()
		 */
		System.out.println("equals name1&name2 : " + name1.equals(name2));
		System.out.println("equals name1&name3 : " + name1.equals(name3));
		System.out.println("equalsIgnoreCase : " + name1.equalsIgnoreCase(name2));
		/**
		 * Get length of String
		 */
		System.out.println("length : " + name1.length());
		/**
		 * Extracting substring from a String
		 */
		System.out.println("substring : " + name1.substring(2));
		System.out.println("substring : " + name1.substring(1, 3));
		/**
		 * Finding index positions
		 */
		System.out.println("indexOf : " + name1.indexOf("lo"));
		System.out.println("lastIndexOf : " + name4.lastIndexOf("company", 19));
		/**
		 * converting case
		 */
		System.out.println("toLowerCase : " + name1.toLowerCase());
		System.out.println("toUpperCase : " + name2.toUpperCase());
		/**
		 * Removing leading and trailing spaces
		 */
		System.out.println("trim : " + name3.trim());
		System.out.println("replace : " + name1.replace('l', ' '));
		/**
		 * Replacing characters
		 */
		System.out.println("contains : " + name1.contains("Hello"));
		System.out.println("contains : " + name1.contains("hello"));
		/**
		 * Checking if string contains another string
		 */
		System.out.println("startWith : " + name4.startsWith("Welcome"));
		System.out.println("startwith : " + name4.startsWith("hello"));
		/**
		 * Checking end of the string
		 */
		System.out.println("endsWith : " + name4.endsWith("good."));
		System.out.println("endswith : " + name4.endsWith("to"));
	}

}