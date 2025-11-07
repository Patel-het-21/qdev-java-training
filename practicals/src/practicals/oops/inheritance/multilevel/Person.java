package practicals.oops.inheritance.multilevel;

/**
 * Represents a generic person with a name. Acts as the base class in the
 * multilevel inheritance hierarchy.
 * 
 * @author Het
 * @since 14/10/25
 */
public class Person {

	private String name;

	/**
	 * Sets the name of the person.
	 * 
	 * @param name Name to be set
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