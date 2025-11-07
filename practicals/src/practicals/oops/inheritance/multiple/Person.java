package practicals.oops.inheritance.multiple;

/**
 * Interface representing a Person. Used to demonstrate default method conflict
 * in multiple inheritance.
 * 
 * @author Het
 * @since 15/11/25
 */
public interface Person {

	/**
	 * Default method to display person-related information.
	 */
	default void display() {
		System.out.println("This is display method of person");
	}

}