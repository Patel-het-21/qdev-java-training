package practicals.oops.interfaceTopic;

/**
 * Represents a Cat which implements the {@link Animal} interface. Provides
 * specific implementation for {@code makeSound()} and its own behavior
 * {@code scratch()}.
 * 
 * @author Het Patel
 * @since 13/10/25
 */
public class Cat implements Animal {

	/**
	 * Implementation of the makeSound method for Cat. Displays "Cat meows".
	 */
	@Override
	public void makeSound() {
		System.out.println("Cat meows");
	}

	/**
	 * Cat-specific method to show its behavior. Displays "Cat scratches the
	 * furniture".
	 */
	void scratch() {
		System.out.println("Cat scratches the furniture");
	}

}