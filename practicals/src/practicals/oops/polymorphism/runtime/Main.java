package practicals.oops.polymorphism.runtime;

/**
 * Implements runtime polymorphism using method overriding. Demonstrates how
 * different animal types respond to the same method.
 * 
 * @author Het Patel
 * @since 15/10/25
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Create Animal object
		 */
		Animal animal = new Animal();
		animal.makeSound();
		/**
		 * Create Dog object with Animal reference
		 */
		Animal dog = new Dog();
		dog.makeSound();
		/**
		 * Create Cat object with Animal reference
		 */
		Animal cat = new Cat();
		cat.makeSound();
	}

}