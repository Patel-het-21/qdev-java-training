package practicals.oops.polymorphism.runtime;

/**
 * Represents the Dog class, a subclass of Animal. Overrides the makeSound
 * method.
 * 
 * @author Het Patel
 * @since 15/10/25
 */
public class Dog extends Animal {

	/**
	 * Overrides the makeSound method for Dog.
	 */
	@Override
	void makeSound() {
		System.out.println("Dog barks");
	}

}