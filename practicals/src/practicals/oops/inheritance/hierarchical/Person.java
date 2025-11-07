package practicals.oops.inheritance.hierarchical;

/**
 * Represents a generic person with a name. This is the parent class in the
 * hierarchical inheritance structure.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Person {

	private String name;

	/**
	 * Set the name of person
	 * 
	 * @param name person name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Display the name of person.
	 */
	public void displayName() {
		System.out.println("Name: " + name);
	}

}