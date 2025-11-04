package practicals.oops.inheritance.multilevel;

/**
 * Demonstrates Multilevel Inheritance: Person → Employee → Manager
 * 
 * Creates and initializes a Manager object and displays the details.
 * 
 * @author Het Patel
 * @since 14/10/25
 */
public class Main {

	/**
	 * This is the main method, the entry point for the Java application.
	 *
	 * @param args Command-line arguments (not used)
	 */
	public static void main(String[] args) {

		// Creating Manager object and setting inherited properties
		Manager mgr = new Manager();
		mgr.setName("Bob"); // from Person
		mgr.setEmployeeId(102); // from Employee
		mgr.setDepartment("Developer"); // from Manager
		mgr.displayManager(); // shows all details
	}

}