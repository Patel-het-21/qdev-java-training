package practicals.oops.inheritance.multiple;

/**
 * Demonstrates Multiple Inheritance using interfaces in Java. This is a
 * practical example of resolving ambiguity when multiple interfaces define
 * methods with the same signature.
 * 
 * @author Het Patel 
 * @since 15/11/25 
 * Purpose : Multiple inheritance using
 * interfaces with default methods
 */
public class Main implements Person, Employee, Student {

	/**
	 * Overrides the display() method to resolve the conflict between multiple
	 * interfaces. Calls each interface's default display method explicitly using
	 * InterfaceName.super syntax.
	 */
	@Override
	public void display() {
		Person.super.display();
		Employee.super.display();
		Student.super.display();
		System.out.println("This is display method of Main");
	}

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String args[]) {
		Main main = new Main();
		main.display();
	}

}