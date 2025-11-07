package practicals;

/**
 * Description : Use this and super keywords to access class members and
 * constructors.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class StaticDemo {

	private static int count = 0;

	/**
	 * Static block executed when the class is loaded.
	 */
	static {
		System.out.println("This is static block");
		count = 10;
	}
	/**
	 * Second static block executed when the class is loaded.
	 */
	static {
		System.out.println("This is 2nd static block");
	}
	/**
	 * Non-static block executed each time an instance is created.
	 */
	{
		System.out.println("Non static block");
	}
	/**
	 * Second non-static block executed each time an instance is created.
	 */
	{
		System.out.println("This is 2nd non static block");
	}

	/**
	 * Static method that adds two numbers and prints the result.
	 */
	static void addition() {
		int number1 = 20;
		int number2 = 30;
		System.out.println("Addition of 20 and 30 is " + (number1 + number2));
	}

	/**
	 * Static method that multiply two numbers and prints the result.
	 */
	void multiplication() {
		int number1 = 2;
		int number2 = 4;
		System.out.println("Multiplication of 2 and 4 is " + (number1 * number2));
	}

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		StaticDemo staticDemo = new StaticDemo();
		staticDemo.multiplication();
		/**
		 * call static method
		 */
		StaticDemo.addition();
		System.out.println("Count is " + StaticDemo.count);
		StaticDemo staticDemo2 = new StaticDemo();
		staticDemo2.multiplication();
	}

}