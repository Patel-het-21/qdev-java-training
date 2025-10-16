package practicals.oops.interfaceTopic;

/**
 * Represents the Main class to implement the interface topic. Demonstrates
 * usage of Animal interface and its implementations.
 * 
 * @author Het Patel
 * @since 13/10/25
 */
public class Main {

	/**
	 * The main method to run the program.
	 * 
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String args[]) {

		/**
		 * Declare interface reference
		 */
		Animal animal;
		/**
		 * Assign Dog object to Animal reference
		 */
		animal = new Dog();
		animal.makeSound();
		/**
		 * Downcast to Dog to call dog-specific method
		 */
		if (animal instanceof Dog) {
			Dog dog = (Dog) animal;
			dog.fetch();
		}
		/**
		 * Assign Cat object to Animal reference
		 */
		animal = new Cat();
		animal.makeSound();
		/**
		 * Downcast to Cat to call cat-specific method
		 */
		if (animal instanceof Cat) {
			Cat cat = (Cat) animal;
			cat.scratch();
		}
	}

}