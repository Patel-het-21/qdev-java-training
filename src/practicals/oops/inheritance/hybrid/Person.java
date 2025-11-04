package practicals.oops.inheritance.hybrid;

/**
 * Base class representing a person with basic details. Superclass for Student
 * class in the inheritance hierarchy.
 * 
 * @author Het
 * @since 15/10/25
 */
public class Person {

	protected String name;
	protected int age;

	/**
	 * Constructor to initialize person's name and age.
	 * 
	 * @param name Name of the person
	 * @param age  Age of the person
	 */
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	/**
	 * Displays the person's name and age.
	 */
	public void showPersonDetails() {
		System.out.println("Name : " + name);
		System.out.println("Age : " + age);
	}

}