package practicals.oops.inheritance.singlelevel;

/**
 * Represents a person with a name. This is the superclass in single-level
 * inheritance.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Person {

	private String name;

	/**
	 * Sets the name of the person.
	 * 
	 * @param name The name to be set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Displays the name of the person.
	 */
	public void displayName() {
		System.out.println("Name: " + name);
	}

}