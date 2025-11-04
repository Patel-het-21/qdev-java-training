package practicals;

/**
 * Description : Program to demonstrate the use of constructors and
 * parameterized constructors with a simple math operation.
 * 
 * @author Het Patel
 * @since 11/10/25
 */
public class ConstructorExample {

	private int number1;
	private int number2;

	/**
	 * Default constructor Sets both numbers to 0
	 */
	public ConstructorExample() {
		number1 = 0;
		number2 = 0;
	}

	/**
	 * Parameterized constructor Assigns the given values to number1 and number2
	 *
	 * @param number1 First number
	 * @param number2 Second number
	 */
	public ConstructorExample(int number1, int number2) {
		this.number1 = number1;
		this.number2 = number2;
	}

	/**
	 * Method to perform and print sum and product
	 */
	public void showResults() {
		System.out.println("Number 1: " + number1);
		System.out.println("Number 2: " + number2);
		System.out.println("Sum      : " + (number1 + number2));
		System.out.println("Product  : " + (number1 * number2));
	}

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		System.out.println("Using default constructor:");
		/**
		 * Creating object using default constructor
		 */
		ConstructorExample defaultOp = new ConstructorExample();
		defaultOp.showResults();
		System.out.println("\nUsing parameterized constructor:");
		/**
		 * Creating object using parameterized constructor
		 */
		ConstructorExample paramOp = new ConstructorExample(5, 3);
		paramOp.showResults();
	}

}