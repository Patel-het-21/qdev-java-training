package practicals;

/**
 * Author      : Het Patel  
 * Date        : 13/10/25  
 * Description : Demonstrate common String methods such as charAt(), concat(), equals(), etc.
 */
public class StringMethods {

	/**
     * Main method to print the star pattern.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		String name1 = "Hello";
		String name2 = "hello";
		String name3 = " World!!!  ";
		String name4 = "Welcome to company. company is good."; 
		
		System.out.println("cahrAt(1) : "+name1.charAt(0));
		System.out.println("concat : " + name1.concat(name3));
		
		System.out.println("equals name1&name2 : " + name1.equals(name2));
		System.out.println("equals name1&name3 : " + name1.equals(name3));
		
		System.out.println("equalsIgnoreCase : " + name1.equalsIgnoreCase(name2));
		
		System.out.println("length : " + name1.length());
		
		System.out.println("substring : " + name1.substring(2));
		System.out.println("substring : " + name1.substring(1,3));
		
		System.out.println("indexOf : " + name1.indexOf("lo"));
		
		System.out.println("lastIndexOf : " + name4.lastIndexOf("company",19));
		
		System.out.println("toLowerCase : " + name1.toLowerCase());
		System.out.println("toUpperCase : " + name2.toUpperCase());
		
		System.out.println("trim : " + name3.trim());
		System.out.println("replace : " + name1.replace('l', ' '));
		
		System.out.println("contains : " + name1.contains("Hello"));
		System.out.println("contains : " + name1.contains("hello"));
		
		System.out.println("startWith : " + name4.startsWith("Welcome"));
		System.out.println("startwith : " + name4.startsWith("hello"));
		
		System.out.println("endsWith : " + name4.endsWith("good."));
		System.out.println("endswith : " + name4.endsWith("to"));
		
	}

}
