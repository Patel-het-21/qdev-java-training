package practicals.oops.inheritance.multiple;

/**
 * Interface representing an Employee. Part of multiple inheritance example
 * using default methods.
 * 
 * @author Het
 * @since 15/11/25
 */
public interface Employee {

	/**
	 * Default method to display employee-related information.
	 */
	default void display() {
		System.out.println("This is display method employee");
	}

}