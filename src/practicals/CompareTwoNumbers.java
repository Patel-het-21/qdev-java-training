package practicals;

/**
 * Author      : Het Patel  
 * Date        : 13/10/25  
 * Description : Compare two variables using Java Wrapper classes like Integer and Double.
 */
public class CompareTwoNumbers {
	
	/**
     * Main method to print the star pattern.
     *
     * @param args Command-line arguments (not used)
    */
	public static void main(String[] args) {
		
		//Integer wrapper class objects
		Integer num1 = 100;
		Integer num2 = 100;
		Integer num3 = 150;
		
		//Double wrapper class objects
		Double d1 = 99.99;
		Double d2 = 99.99;
		Double d3 = 111.11;
		Double d4 = 100d; 
		
		//Using equals method
		System.out.println("num1 equals num2 : " + num1.equals(num2));
		System.out.println("num1 equals num3 : " + num1.equals(num3));

		System.out.println("d1 equals d2 : " + d1.equals(d2));
		System.out.println("d1 equals d3 : " + d1.equals(d3));
		
		System.out.println("num1 compareTo d4 : " + num1.equals(d4));

		//using comparteTo method
		System.out.println("num1 compareTo num2 : " + num1.compareTo(num2));
		System.out.println("num1 compareTo num3 : " + num1.compareTo(num3));
		System.out.println("num3 compareTo num1 : " + num3.compareTo(num1));
		
		System.out.println("d1 compareTo d2 : " + d1.compareTo(d2));
		System.out.println("d1 compareTo d3 : " + d1.compareTo(d3));
		System.out.println("d3 compareTo d1 : " + d3.compareTo(d1));
		
	}

}
