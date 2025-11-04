package practicals.oops.interfaceTopic;

/**
 * Represents a Dog which implements the {@link Animal} interface. Provides
 * specific implementation for {@code makeSound()} and its own behavior
 * {@code fetch()}.
 * 
 * @author Het Patel
 * 
 * @since 13/10/25
 */
public class Dog implements Animal {

	/**
	 * Implementation of the makeSound method for Dog. Displays "Dog Barks".
	 */
	@Override
	public void makeSound() {
		System.out.println("Dog Barks");
	}

	/**
	 * Dog-specific method to show its behavior. Displays "Dog fetches the ball".
	 */
	void fetch() {
		System.out.println("Dog fetches the ball");
	}

}