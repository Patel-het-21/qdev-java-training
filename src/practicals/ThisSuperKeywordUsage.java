package practicals;

/**
 * Description : Use this and super keywords to access class members and
 * constructors.
 * 
 * @author Het Patel
 * @since 13/10/25
 */

class Animal {

	String name = "Animal";

	/**
	 * Parent class method to make a sound.
	 */
	void makeSound() {
		System.out.println("Animal ---> Make Sound");
	}

}

class Dog extends Animal {

	String name = "Dog";

	/**
	 * Default constructor that calls the parameterized constructor of the same
	 * class.
	 */
	Dog() {
		// call current class parameterized constructor
		this("Default Breed");
		System.out.println("Dog default constructor calls");
	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param breed The breed of the dog
	 */
	Dog(String breed) {
		System.out.println("Dog Parameterized constructor called with breed : " + breed);
	}

	/**
	 * Demonstrates the use of {@code this} and {@code super} to access variables.
	 */
	void displayNames() {
		System.out.println("this.name= " + this.name);

		System.out.println("super.name = " + super.name);
	}

	/**
	 * Overrides the parent class method. Calls the parent method and then adds its
	 * own behavior.
	 */
	void makeSound() {
		// call parent class method
		super.makeSound();
		System.out.println("Dog Barks");
	}

}

public class ThisSuperKeywordUsage {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.displayNames();
		dog.makeSound();
	}

}