package practicals.oops.polymorphism.runtime;

/**
 * Represents the Cat class, a subclass of Animal. Overrides the makeSound
 * method.
 * 
 * @author Het Patel
 * @since 15/10/25
 */
public class Cat extends Animal {

	/**
	 * Overrides the makeSound method for Cat.
	 */
	@Override
	void makeSound() {
		System.out.println("Cat meows");
	}

}