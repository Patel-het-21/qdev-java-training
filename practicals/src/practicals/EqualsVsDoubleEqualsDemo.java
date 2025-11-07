package practicals;

import java.math.BigDecimal;

/**
 * Description : Compare == and .equals() behavior on String, Integer, and
 * BigDecimal objects.
 * 
 * @author Het Patel
 * @since 13/10/25
 */
public class EqualsVsDoubleEqualsDemo {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		/**
		 * String Comparison
		 */
		String s1 = new String("hello");
		String s2 = new String("hello");

		System.out.println("String == comparision : " + (s1 == s2));
		System.out.println("String .equals() comparsion : " + s1.equals(s2));

		String s3 = "hello";
		String s4 = "hello";

		System.out.println("String == comparision : " + (s3 == s4));
		System.out.println("String .equals() comparsion : " + s3.equals(s4));

		/**
		 * String Comparison
		 */
		Integer num1 = new Integer(100);
		Integer num2 = new Integer(100);

		System.out.println("Integer == comparison: " + (num1 == num2));
		System.out.println("Integer .equals() comparison: " + num1.equals(num2));
		Integer num3 = 100;
		Integer num4 = 100;

		System.out.println("Integer == comparison: " + (num3 == num4));
		System.out.println("Integer .equals() comparison: " + num3.equals(num4));

		/**
		 * BigDesimal Comparison
		 */
		BigDecimal num5 = new BigDecimal("2.0");
		BigDecimal num6 = new BigDecimal("2.00");

		System.out.println("BigDecimal == comparison : " + (num5 == num6));
		System.out.println("BigDesimal .equals : " + num5.equals(num6));
		System.out.println("BigDecimal compareTo(): " + num5.compareTo(num6));
	}

}