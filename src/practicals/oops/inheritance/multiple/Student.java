package practicals.oops.inheritance.multiple;

/**
 * Interface representing a Student. Provides a default method for display.
 * 
 * @author Het
 * @since 15/11/25
 */
public interface Student {

	/**
	 * Default method to display student-related information.
	 */
	default void display() {
		System.out.println("This is display method of Student");
	}

}